package net.numa08.rdblogger.dump

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import net.numa08.rdblogger.database.LogDao
import net.numa08.rdblogger.di.JsonLogFormatter
import java.nio.file.Path
import java.time.ZonedDateTime
import javax.inject.Inject

interface CloudStorageLogSynchronizer : LogSynchronizer {

    /** クラウドストレージにアップロードされるファイルの内容を保存したパス。
     * キャッシュファイルなので、永久にアクセスできる保証は無い。
     * 本来ならUIやWorkManagerに通知は必要がないが、デバッグのために用意している。*/
    val temporaryFile: Flow<Path?>

}

class S3LogSynchronizer @Inject constructor(
    private val logDao: LogDao,
    private val fileLogDumper: FileLogDumper,
    @JsonLogFormatter private val logFormatter: LogFormatter
) : CloudStorageLogSynchronizer {
    override val temporaryFile = MutableStateFlow<Path?>(null)

    override suspend fun syncTodayLog() {
        val now = ZonedDateTime
            .now()
        val fromTimestamp = now
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
            .withNano(0)
        val toTimestamp = now
            .withHour(23)
            .withMinute(59)
            .withSecond(59)
            .withNano(59)

        val logEntryList = logDao
            .logEntryBetween(fromTimestamp, toTimestamp)
        val formattedString = logFormatter.format(logEntryList)
        val dumpedPath = fileLogDumper.dump(formattedString)
        temporaryFile.emit(dumpedPath)

        // S3へのアップロードをこの後行う
        // s3Uploader.upload(dumpedPath)
        // アップロード完了後、必要ならファイルを削除する

        val markSynced = logEntryList
            .map { it.copy(isSynced = true) }
        logDao.updateLogEntry(*markSynced.toTypedArray())
    }
}