package app.devpedrocarvalho.testpp.di

import app.devpedrocarvalho.testpp.model.repository.IMainActivityDatabaseRepository
import app.devpedrocarvalho.testpp.model.repository.MainActivityDatabaseRepository
import app.devpedrocarvalho.testpp.network.repository.IMainActivityNetworkRepository
import app.devpedrocarvalho.testpp.network.repository.MainActivityNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModuleBinds {

    @Binds
    abstract fun bindMainActivityRepository(activityRepository: MainActivityNetworkRepository): IMainActivityNetworkRepository

    @Binds
    abstract fun bindMainActivityDatabaseRepository(activityRepository: MainActivityDatabaseRepository): IMainActivityDatabaseRepository

}

