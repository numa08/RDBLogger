package net.numa08.rdblogger.net.numa08.rdblogger.database

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class DatabaseLogger @Inject constructor(
    private val logDao: LogDao,
    dispatcher: CoroutineDispatcher
) : Timber.Tree() {

    private val scope = CoroutineScope(dispatcher + Job())

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val logMessage = t?.let {
            "${it.localizedMessage}, $message"
        } ?: message
        val logEntry = LogEntry(
            id = UUID.randomUUID().toString(),
            timestamp = Date().time,
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