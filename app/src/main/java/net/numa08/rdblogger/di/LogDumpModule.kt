package net.numa08.rdblogger.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.numa08.rdblogger.dump.*
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class LogDumpModule {

    @Binds
    abstract fun bindsFileLogDumper(
        fileLogDumperImpl: FileLogDumperImpl
    ): FileLogDumper

    @Binds
    @JsonLogFormatter
    abstract fun bindsJsonLogFormatter(
        jsonLogFormatter: net.numa08.rdblogger.dump.JsonLogFormatter
    ): LogFormatter

    @Binds
    abstract fun bindsCloudStorageLogSynchronizer(
        synchronizer: S3LogSynchronizer
    ): CloudStorageLogSynchronizer
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class JsonLogFormatter