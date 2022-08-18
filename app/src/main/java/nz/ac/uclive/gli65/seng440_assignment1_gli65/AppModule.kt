package nz.ac.uclive.gli65.seng440_assignment1_gli65

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.RoomDB
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.CategoryRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.EventRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.ICategoryRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.models.repositories.IEventRepository
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.UseCases
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.AddCategoryUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.DeleteCategoryUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.category.GetCategoriesUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.AddEventUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.DeleteEventUseCase
import nz.ac.uclive.gli65.seng440_assignment1_gli65.viewmodels.use_case.event.GetEventsUseCase
import javax.inject.Singleton


@Module // https://proandroiddev.com/navigating-through-multi-module-jetpack-compose-applications-6c9a31fa12b6
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDB(app: Application): RoomDB {
        return Room.databaseBuilder(
            app,
            RoomDB::class.java,
            RoomDB.DATABASE_NAME
        ).allowMainThreadQueries().createFromAsset("database/simple.db").build() // todo check database
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(db: RoomDB): ICategoryRepository {
        return CategoryRepository(db.categoryDao)
    }

    @Provides
    @Singleton
    fun provideEventRepository(db: RoomDB): IEventRepository {
        return EventRepository(db.eventDao)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        categoryRepository: ICategoryRepository,
        eventRepository: IEventRepository
    ): UseCases {
        return UseCases(
            getCategoriesUseCase = GetCategoriesUseCase(categoryRepository),
            deleteCategoryUseCase = DeleteCategoryUseCase(categoryRepository),
            getEventsUseCase = GetEventsUseCase(eventRepository),
            deleteEventUseCase = DeleteEventUseCase(eventRepository),
            addCategoryUseCase = AddCategoryUseCase(categoryRepository),
            addEventUseCase = AddEventUseCase(eventRepository),
        )
    }
}