package app.devpedrocarvalho.testpp.di

import app.devpedrocarvalho.testpp.network.repository.IMainActivityRepository
import app.devpedrocarvalho.testpp.network.repository.MainActivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModuleBinds {

    @Binds
    abstract fun bindMainActivityRepository(activityRepository: MainActivityRepository): IMainActivityRepository

}

