package net.numa08.rdblogger.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.nio.file.Path
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object FileModule {

    @CacheDir
    @Provides
    fun providesCacheDir(
        @ApplicationContext context: Context
    ): Path = context.cacheDir.toPath()

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CacheDir