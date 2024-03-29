package net.numa08.rdblogger.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @IODispatcher
    @Provides
    @Singleton
    fun providesCoroutineDispatcher(): CoroutineDispatcher
        = Dispatchers.IO

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher