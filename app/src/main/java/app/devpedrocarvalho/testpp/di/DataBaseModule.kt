package app.devpedrocarvalho.testpp.di

import android.content.Context
import androidx.room.Room
import app.devpedrocarvalho.testpp.model.AppDatabase
import app.devpedrocarvalho.testpp.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideYourDatabase(
            @ApplicationContext app: Context
    ) = Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            Utils.TABLE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: AppDatabase) = db.recipeDao()
}