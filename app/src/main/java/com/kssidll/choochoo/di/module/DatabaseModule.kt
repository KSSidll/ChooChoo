package com.kssidll.choochoo.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kssidll.choochoo.data.dao.ConnectionDao
import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.database.AppDatabase
import com.kssidll.choochoo.data.database.prepopulateStationData
import com.kssidll.choochoo.data.repository.ConnectionRepository
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import com.kssidll.choochoo.data.repository.StationRepository
import com.kssidll.choochoo.data.repository.TicketRepository
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
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                // prepopulate the database
                // we do that with a function but it is preffered and recommended
                // to prepopulate via prepackaged database file
                for (data in prepopulateStationData()) {
                    db.execSQL("INSERT INTO station (name) VALUES('${data.name}');")
                }
            }
        }).build()
    }

    @Provides
    fun provideTicketDao(appDatabase: AppDatabase): TicketDao {
        return appDatabase.getTicketDao()
    }

    @Provides
    fun provideTicketRepository(ticketDao: TicketDao): ITicketRepository {
        return TicketRepository(ticketDao)
    }

    @Provides
    fun provideStationDao(appDatabase: AppDatabase): StationDao {
        return appDatabase.getStationDao()
    }

    @Provides
    fun provideStationRepository(stationDao: StationDao): IStationRepository {
        return StationRepository(stationDao)
    }

    @Provides
    fun provideConnectionDao(appDatabase: AppDatabase): ConnectionDao {
        return appDatabase.getConnectionDao()
    }

    @Provides
    fun provideConnectionRepository(connectionDao: ConnectionDao): IConnectionRepository {
        return ConnectionRepository(connectionDao)
    }


}