package me.zama.holdmywidgets.model.ip_address

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IpAddressLiveProviderModule {

    @Provides
    @Singleton
    fun provideIpAddressLiveProvider(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): IpAddressLiveProvider = IpAddressLiveProviderImpl(context, okHttpClient)
}