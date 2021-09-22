package pe.juabsa.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import java.util.*

class DataConverter {


    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}