package net.numa08.rdblogger.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.numa08.rdblogger.dump.CloudStorageLogSynchronizer
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.readText

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val cloudStorageLogSynchronizer: CloudStorageLogSynchronizer
) :
    ViewModel() {

    var dumpedLogPath by mutableStateOf<Path?>(null).also {
        viewModelScope.launch {
            cloudStorageLogSynchronizer
                .temporaryFile
                .collect { path ->
                    it.value=path
                }
        }
    }
        private set


    fun syncTodayLog() {
        viewModelScope.launch {
            cloudStorageLogSynchronizer.syncTodayLog()
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @SuppressLint("LogNotTimber")
    fun openDumpedFile() {
        val dumpedLogPath = this.dumpedLogPath ?: return
        viewModelScope.launch {
            val file = dumpedLogPath.readText()
            Log.d("MainScreen", file)
        }
    }
}