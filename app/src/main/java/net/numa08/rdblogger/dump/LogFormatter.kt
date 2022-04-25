package net.numa08.rdblogger.dump

import net.numa08.rdblogger.database.LogEntry

/**
 * DB に保存されたログリストを出力用文字列に変換する
 * */
interface LogFormatter {
    fun format(logEntryList: List<LogEntry>): String
}