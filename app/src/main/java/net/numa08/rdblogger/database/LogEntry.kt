package net.numa08.rdblogger.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

/**
 * |UUID|イベント発生時間|ログレベル|タグ|ログ内容テキスト|同期済かどうか|
 * */
@Entity
data class LogEntry(
    /** UUID を想定している */
    @PrimaryKey val id: String,
    /** long type unix-time */
    val timestamp: ZonedDateTime,
    val logLevel: Int,
    val tag: String? = null,
    val text: String,
    val isSynced: Boolean
)
