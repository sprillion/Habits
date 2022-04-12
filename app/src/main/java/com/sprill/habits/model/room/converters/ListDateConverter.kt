package com.sprill.habits.model.room.converters

import androidx.room.TypeConverter

class ListDateConverter {
        @TypeConverter
        fun toDoneDates(value: String?): List<Long> {
            if (value == null || value.isEmpty())
                return mutableListOf()
            val list = value.split(",")
            val longList = mutableListOf<Long>()
            list.forEach { item ->
                if (item.isNotEmpty())
                    longList.add(item.toLong())
            }
            return longList
        }

        @TypeConverter
        fun toString(doneDates: List<Long>?): String {
            var string = ""
            doneDates?.let {
                doneDates.forEach {
                    string += "$it,"
                }
            }
            return string
        }
}