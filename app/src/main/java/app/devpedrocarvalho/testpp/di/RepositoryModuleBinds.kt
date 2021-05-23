package app.devpedrocarvalho.testpp.di

import app.devpedrocarvalho.testpp.repository.MainActivityRepository
import app.devpedrocarvalho.testpp.repository.MainActivityRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModuleBinds {

    @Binds
    abstract fun bindMainActivityRepository(mainActivityRepository: MainActivityRepositoryImp): MainActivityRepository

}

