package net.numa08.rdblogger.dump
/**
 * 文字列化されたログを出力する。結果は T で通知される
 * */
interface LogDumper<T> {
    suspend fun dump(log: String): T
}