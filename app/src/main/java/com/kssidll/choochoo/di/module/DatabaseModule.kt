package com.kssidll.choochoo.di.module

import android.content.Context
import androidx.room.Room
import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.dao.UserDao
import com.kssidll.choochoo.data.database.AppDatabase
import com.kssidll.choochoo.data.repository.StationRepository
import com.kssidll.choochoo.data.repository.TicketRepository
import com.kssidll.choochoo.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    @Provides
    fun provideTicketDao(appDatabase: AppDatabase): TicketDao {
        return appDatabase.getTicketDao()
    }

    @Provides
    fun provideTicketRepository(ticketDao: TicketDao): TicketRepository {
        return TicketRepository(ticketDao)
    }

    @Provides
    fun provideStationDao(appDatabase: AppDatabase): StationDao {
        return appDatabase.getStationDao()
    }

    @Provides
    fun provideStationRepository(stationDao: StationDao): StationRepository {
        return StationRepository(stationDao)
    }


}