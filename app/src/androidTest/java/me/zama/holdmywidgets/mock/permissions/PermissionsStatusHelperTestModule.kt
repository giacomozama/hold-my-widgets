package me.zama.holdmywidgets.mock.permissions

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelper
import me.zama.holdmywidgets.utils.permissions.PermissionsStatusHelperModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [PermissionsStatusHelperModule::class])
class PermissionsStatusHelperTestModule {

    @Provides
    fun providePermissionsStatusHelper(): PermissionsStatusHelper = PermissionsStatusHelperTestImpl()
}