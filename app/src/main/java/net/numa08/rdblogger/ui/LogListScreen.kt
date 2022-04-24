package net.numa08.rdblogger.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

data class LogListRowItem(
    val date: String,
    val message: String,
    val id: String,
    val isSynced: Boolean,
    val logLevel: Int,
    val tag: String?
)

@Composable
fun LogListScreen(
    viewModel: LogListScreenViewModel = hiltViewModel()
){
    val items = viewModel.logEntryList
    LogListScreen(items)
}

@Composable
private fun LogListScreen(
    items: List<LogListRowItem> = emptyList()
) {
    LogList(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        items = items
    )
}

@Composable
fun LogList(
    modifier: Modifier = Modifier,
    items: List<LogListRowItem> = emptyList(),
) {
    Column(
        modifier = modifier
    ) {
        for (item in items) {
            LogListRow(item = item)
            Divider()
        }
    }
}

@Composable
fun LogListRow(
    item: LogListRowItem
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 8.dp)
    ) {
        Text(text = item.message)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = item.date)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "level: ${item.logLevel}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "tag: ${item.tag}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "synced: ${item.isSynced}")
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogListRowItem() {
    LogListRow(
        item = LogListRowItem(
            date = "2022-04-24T00:00:00:00",
            message = "this is test message",
            id = "UUID",
            isSynced = false,
            logLevel = Log.DEBUG,
            tag = "test"
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogList() {
    val items = (0 until 10).map {
        LogListRowItem(
            date = "2022-04-24T00:00:00:00",
            message = "this is test message",
            id = "UUID",
            isSynced = false,
            logLevel = Log.DEBUG,
            tag = "test"
        )
    }
    LogList(items = items)
}