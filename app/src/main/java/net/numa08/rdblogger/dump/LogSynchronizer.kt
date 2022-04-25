package net.numa08.rdblogger.dump
/**
 * 何らかの外部システムと連携して、ログを同期するする
 * */
interface LogSynchronizer {
    /** 実行した日付のログを同期する */
    suspend fun syncTodayLog()
}

