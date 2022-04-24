package net.numa08.rdblogger.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.numa08.rdblogger.net.numa08.rdblogger.database.LogDao
import net.numa08.rdblogger.net.numa08.rdblogger.database.LogDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesLogDatabase(
        @ApplicationContext context: Context
    ): LogDatabase = Room
        .databaseBuilder(
            context, LogDatabase::class.java, "log"
        ).build()

    @Provides
    fun providesLogDao(
        logDatabase: LogDatabase
    ): LogDao = logDatabase.logDao()

}