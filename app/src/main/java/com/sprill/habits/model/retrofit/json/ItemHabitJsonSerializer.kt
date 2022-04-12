package com.sprill.habits.model.retrofit.json

import com.google.gson.*
import com.sprill.habits.model.retrofit.models.ItemHabit
import java.lang.reflect.Type

class ItemHabitJsonSerializer: JsonSerializer<ItemHabit> {

    override fun serialize(
        src: ItemHabit,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement = JsonObject().apply {
        addProperty("color", src.color)
        addProperty("count", src.countExecution)
        addProperty("date", src.date)
        addProperty("frequency", src.period)
        addProperty("priority", src.priority)
        addProperty("title", src.name)
        addProperty("type", src.type)
        addProperty("uid", src.uid)
    }
}
