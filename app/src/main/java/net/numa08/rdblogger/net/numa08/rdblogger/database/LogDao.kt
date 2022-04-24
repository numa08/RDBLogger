package net.numa08.rdblogger.net.numa08.rdblogger.database

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface LogDao {

    @Insert
    suspend fun storeLog(log: LogEntry)

}