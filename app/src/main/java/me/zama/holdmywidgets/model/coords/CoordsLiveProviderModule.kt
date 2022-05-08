package me.zama.holdmywidgets.model.coords

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoordsLiveProviderModule {

    @Provides
    @Singleton
    fun provideCoordsLiveProvider(
        @ApplicationContext context: Context,
        permissionsStatusHelper: PermissionsStatusHelper
    ): CoordsLiveProvider = CoordsLiveProviderImpl(context, permissionsStatusHelper)
}