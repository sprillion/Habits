package com.sprill.habits

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.graphics.drawable.toBitmap
import com.sprill.habits.databinding.ViewColorPickerBinding

class ColorPicker(context: Context, attrs: AttributeSet): LinearLayout (context, attrs) {

    private var binding: ViewColorPickerBinding

    private var itemCount = 0
    private var itemSize = 0
    private var itemMargin = 0
    private var selectedColor = 0

    init {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.ColorPicker, 0, 0)
        try {
            itemCount = attributes.getInt(R.styleable.ColorPicker_item_count, 1)
            itemSize = attributes.getDimension(R.styleable.ColorPicker_item_size, 80f).toInt()
            itemMargin = attributes.getDimension(R.styleable.ColorPicker_item_margin, 25f).toInt()
        } finally {
            attributes.recycle()
        }

        orientation = VERTICAL
        val params = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        layoutParams = params

        val inflater = LayoutInflater.from(context)
        binding = ViewColorPickerBinding.inflate(inflater, this)

        setGradient()
        fillItems()

        invalidate()
    }

    private fun fillItems(){
        val bitmapWidth = itemCount * (itemSize + itemMargin * 2)
        val bitmapHeight = itemSize + itemMargin * 2
        val bitmap = binding.layoutGradient.background.toBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        for (i in 0 until  itemCount){
            binding.layoutGradient.addView(newItem(bitmap))
        }
        binding.layoutGradient.invalidate()
    }

    private fun newItem(bitmap: Bitmap): View{
        var itemColor= 0
        val square = context.getDrawable(R.drawable.square)?.current as GradientDrawable
        val item = View(context)
        val params = LayoutParams(itemSize, itemSize)

        params.leftMargin = itemMargin
        params.rightMargin = itemMargin
        item.layoutParams = params

        item.viewTreeObserver.addOnGlobalLayoutListener {
            itemColor = bitmap.getPixel(item.x.toInt() + itemSize/2,item.y.toInt() + itemSize/2)
            square.setColor(itemColor)
            if (selectedColor == 0) setColor(itemColor)
        }
        item.background = square
        item.setOnClickListener{
            setColor(itemColor)
        }

        return item
    }

    private fun setGradient()
    {
        val drawable = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(Color.RED, Color.YELLOW, Color.GREEN,  Color.CYAN, Color.BLUE, Color.MAGENTA))
        drawable.gradientType = GradientDrawable.LINEAR_GRADIENT
        binding.layoutGradient.background = drawable
    }

    @SuppressLint("SetTextI18n")
    fun setColor(color: Int){
        val square = context.getDrawable(R.drawable.square)?.current as GradientDrawable
        square.setColor(color)
        binding.currentColor.background = square
        selectedColor = square.color?.defaultColor ?: 0

        val hsv = floatArrayOf(0f, 0f, 0f)
        Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsv)

        binding.textRGB.text = "R: ${Color.red(color)}      G: ${Color.green(color)}     B: ${Color.blue(color)}"
        binding.textHSV.text = "H: ${String.format("%.2f", hsv[0])}    S: ${String.format("%.2f", hsv[1])}    V: ${String.format("%.2f", hsv[2])}"
    }

    fun getColor(): Int{
        return selectedColor
    }
}