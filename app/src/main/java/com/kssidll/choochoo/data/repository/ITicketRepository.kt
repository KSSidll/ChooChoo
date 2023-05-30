package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

interface ITicketRepository {
    fun getAllFlow(): Flow<List<Ticket>>
    suspend fun getAll(): List<Ticket>
    fun getFlow(id: Int): Flow<Ticket>
    suspend fun get(id: Int): Ticket
    fun getAllActiveFlow(): Flow<List<Ticket>>
    suspend fun getAllActive(): List<Ticket>
    fun getActiveFlow(id: Int): Flow<Ticket>
    suspend fun getActive(id: Int): Ticket
    fun getAllOlderThanFlow(date: Long): Flow<Ticket>
    fun getAllNewerThanFlow(date: Long): Flow<Ticket>
    suspend fun cancel(id: Int)
    suspend fun insert(ticket: Ticket)
    suspend fun update(ticket: Ticket)
    suspend fun delete(ticket: Ticket)
}