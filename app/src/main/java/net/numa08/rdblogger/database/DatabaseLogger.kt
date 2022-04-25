package net.numa08.rdblogger.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.numa08.rdblogger.di.IODispatcher
import timber.log.Timber
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Inject

class DatabaseLogger @Inject constructor(
    private val logDao: LogDao,
    @IODispatcher dispatcher: CoroutineDispatcher
) : Timber.Tree() {

    private val scope = CoroutineScope(dispatcher + Job())

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logMessage = t?.let {
            "${it.localizedMessage}, $message"
        } ?: message
        val logEntry = LogEntry(
            id = UUID.randomUUID().toString(),
            timestamp = ZonedDateTime.now(),
            logLevel = priority,
            tag = tag,
            text = logMessage,
            isSynced = false
        )

        scope.launch {
            logDao.storeLog(logEntry)
        }
    }
}