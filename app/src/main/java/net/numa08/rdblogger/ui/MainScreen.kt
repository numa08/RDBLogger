package net.numa08.rdblogger.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun MainScreen(
    navigateNext: () -> Unit = {},
    onClickDumpLog: () -> Unit = {},
    dumpedLogPath: String? = null,
    onClickOpenDumpedLogPath: (String) -> Unit = {}
) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { Timber.d("on click debug button") }) {
                Text(text = "Debug button")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = { Timber.w("on click warning button") }) {
                Text(text = "Warning button")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                Timber.e(
                    RuntimeException("raise error"),
                    "on click error button"
                )
            }) {
                Text(text = "Error button")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = navigateNext) {
                Text(text = "check log list")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onClickDumpLog) {
                Text(text = "Dump log")
            }
            Spacer(modifier = Modifier.height(12.dp))
            if (!dumpedLogPath.isNullOrEmpty()) {
                Text(text = "dumped to : $dumpedLogPath")
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = { onClickOpenDumpedLogPath(dumpedLogPath) }) {
                    Text(text = "open dumped log")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen(
        dumpedLogPath = "hogehoge"
    )
}