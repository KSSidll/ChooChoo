package com.kssidll.choochoo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Query("SELECT * FROM ticket ORDER BY id ASC")
    fun getAllFlow(): Flow<List<Ticket>>

    @Query("SELECT * FROM ticket ORDER BY id ASC")
    suspend fun getAll(): List<Ticket>

    @Query("SELECT * FROM ticket WHERE id == :id")
    fun getFlow(id: Int): Flow<Ticket>

    @Query("SELECT * FROM ticket WHERE id == :id")
    suspend fun get(id: Int): Ticket

    @Insert
    suspend fun insert(ticket: Ticket)

    @Update
    suspend fun update(ticket: Ticket)

    @Delete
    suspend fun delete(ticket: Ticket)
}