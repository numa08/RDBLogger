package net.numa08.rdblogger.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.numa08.rdblogger.database.LogDao
import net.numa08.rdblogger.database.LogEntry
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class LogListScreenViewModel @Inject constructor(
    logDao: LogDao
) : ViewModel() {

    var logEntryList by mutableStateOf<List<LogListRowItem>>(emptyList()).also { state ->
        viewModelScope
            .launch {
                logDao
                    .allLogEntry()
                    .collectLatest {
                        state.value = it.map { entry -> entry.toItemRow }
                    }
            }
    }
        private set

}

val LogEntry.toItemRow: LogListRowItem
    get() {
        val dateTime = ZonedDateTime
            .ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
            ).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        return LogListRowItem(
            id = id,
            date = dateTime,
            message = text,
            isSynced = isSynced,
            logLevel = logLevel,
            tag = tag
        )
    }