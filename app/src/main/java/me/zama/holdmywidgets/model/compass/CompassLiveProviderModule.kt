package me.zama.holdmywidgets.model.compass

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CompassLiveProviderModule {

    @Provides
    @Singleton
    fun provideCompassLiveProvider(
        @ApplicationContext context: Context
    ): CompassLiveProvider = CompassLiveProviderImpl(context)
}