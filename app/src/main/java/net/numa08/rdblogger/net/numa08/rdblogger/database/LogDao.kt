package net.numa08.rdblogger.net.numa08.rdblogger.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {

    @Insert
    suspend fun storeLog(log: LogEntry)

    @Query("SELECT * FROM LogEntry ORDER BY timestamp DESC")
    fun allLogEntry(): Flow<List<LogEntry>>

}