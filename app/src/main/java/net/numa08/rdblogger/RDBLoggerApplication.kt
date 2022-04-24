package net.numa08.rdblogger

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import net.numa08.rdblogger.database.DatabaseLogger
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class RDBLoggerApplication: Application() {

    @Inject lateinit var databaseLogger: DatabaseLogger

    override fun onCreate() {
        super.onCreate()
        Timber.plant(
            Timber.DebugTree(),
            databaseLogger
        )
    }
}