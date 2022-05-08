package me.zama.holdmywidgets.utils.permissions

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PermissionsStatusHelperModule {

    @Provides
    @Singleton
    fun providePermissionsStatusHelper(
        @ApplicationContext context: Context
    ): PermissionsStatusHelper = PermissionsStatusHelperImpl(context)
}