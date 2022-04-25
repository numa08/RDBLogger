package net.numa08.rdblogger.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.time.ZonedDateTime

@Dao
interface LogDao {

    @Insert
    suspend fun storeLog(log: LogEntry)

    @Query("SELECT * FROM LogEntry ORDER BY timestamp DESC")
    fun allLogEntry(): Flow<List<LogEntry>>

    @Query("SELECT * FROM LogEntry WHERE timestamp BETWEEN :fromTimestamp AND :toTimestamp AND isSynced != 1")
    suspend fun logEntryBetween(fromTimestamp: ZonedDateTime, toTimestamp: ZonedDateTime): List<LogEntry>

    @Update
    suspend fun updateLogEntry(vararg logEntry: LogEntry)

//    同期済のログを削除する場合は Delete 用メソッドを使う
//    @Delete
//    suspend fun deleteLogEntry(vararg log: LogEntry)

}