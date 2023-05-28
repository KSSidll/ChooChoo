package com.kssidll.choochoo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kssidll.choochoo.data.dao.ConnectionDao
import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.dao.UserDao
import com.kssidll.choochoo.data.data.Connection
import com.kssidll.choochoo.data.data.User
import com.kssidll.choochoo.data.data.Ticket
import com.kssidll.choochoo.data.data.Station

@Database(entities = [User::class, Ticket::class, Station::class, Connection::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getTicketDao(): TicketDao
    abstract fun getStationDao(): StationDao
    abstract fun getConnectionDao(): ConnectionDao
}