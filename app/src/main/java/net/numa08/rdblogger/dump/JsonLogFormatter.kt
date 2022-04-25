package net.numa08.rdblogger.dump

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.numa08.rdblogger.database.LogEntry
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class JsonLogFormatter @Inject constructor() : LogFormatter {
    @Serializable
    data class LogJsonEntry(
        val id: String,
        val timestamp: String,
        val logLevel: Int,
        val tag: String?,
        val text: String
    )

    @Serializable
    data class LogJson(
        val log: List<LogJsonEntry>
    )

    override fun format(logEntryList: List<LogEntry>): String {
        val logJson = LogJson(
            logEntryList.map { it.toJsonEntry }
        )
        return Json.encodeToString(logJson)
    }
}

val LogEntry.toJsonEntry: JsonLogFormatter.LogJsonEntry
    get() {
        val dateTime = timestamp.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return JsonLogFormatter.LogJsonEntry(
            id = id,
            timestamp = dateTime,
            logLevel = logLevel,
            tag = tag,
            text = text
        )
    }