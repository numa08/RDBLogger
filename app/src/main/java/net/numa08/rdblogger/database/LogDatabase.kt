package net.numa08.rdblogger.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Database(entities = [LogEntry::class], version = 1)
@TypeConverters(DateTimeConverter::class)
abstract class LogDatabase : RoomDatabase() {

    abstract fun logDao(): LogDao

}

class DateTimeConverter {
    @TypeConverter
    fun fromTimeStamp(timeStamp: Long?): ZonedDateTime? = timeStamp?.let {
        ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(it),
            ZoneId.systemDefault()
        )
    }

    @TypeConverter
    fun fromDateTime(zonedDateTime: ZonedDateTime?): Long? = zonedDateTime?.toInstant()
        ?.toEpochMilli()
}