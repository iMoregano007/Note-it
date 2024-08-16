package com.im_oregano007.noteit

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun dateToLong( date: Date) : Long {
        return date.time
    }

    @TypeConverter
    fun longToDate( value : Long) : Date {
        return Date(value)
    }
}