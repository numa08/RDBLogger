package net.numa08.rdblogger.dump

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import net.numa08.rdblogger.di.CacheDir
import net.numa08.rdblogger.di.IODispatcher
import java.nio.file.Path
import javax.inject.Inject
import kotlin.io.path.createTempFile
import kotlin.io.path.writeText

/**
 * ファイルにログを書き込んで、結果ファイルを通知する
 * */
interface FileLogDumper : LogDumper<Path>

class FileLogDumperImpl @Inject constructor(
    @CacheDir private val cacheDir: Path,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : FileLogDumper {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun dump(log: String): Path {
        return withContext(dispatcher) {
            val cacheFile = createTempFile(
                directory = cacheDir,
                prefix = "log"
            )

            cacheFile.writeText(log)
            cacheFile
        }
    }

}