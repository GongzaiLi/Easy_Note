package nz.ac.uclive.gli65.seng440_assignment1_gli65

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.MainDB
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.ICategoryRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.IEventRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.impl.CategoryRepositoryImpl
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repository.impl.EventRepositoryImpl
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.UseCases
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.AddUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.DeleteUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.GetUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDB {
        return Room.databaseBuilder(
            app, MainDB::class.java,
            MainDB.DATABASE_NAME
        ).createFromAsset("database/simple.db").build()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: MainDB): ICategoryRepository {
        return CategoryRepositoryImpl(db.categoryDao)
    }

    @Provides
    @Singleton
    fun provideEventRepository(db: MainDB): IEventRepository {
        return EventRepositoryImpl(db.eventDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(categoryRepository: ICategoryRepository): UseCases {
        return UseCases(
            getUseCase = GetUseCase(categoryRepository),
            deleteUseCase = DeleteUseCase(categoryRepository),
            addUseCase = AddUseCase(categoryRepository),
        )
    }

}