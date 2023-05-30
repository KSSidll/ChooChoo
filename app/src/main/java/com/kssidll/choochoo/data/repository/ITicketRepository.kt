package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

interface ITicketRepository {
    suspend fun getAll(): List<Ticket>
    fun getAllFlow(): Flow<List<Ticket>>
    suspend fun get(id: Int): Ticket
    fun getFlow(id: Int): Flow<Ticket>
    suspend fun insert(ticket: Ticket)
    suspend fun update(ticket: Ticket)
    suspend fun delete(ticket: Ticket)
}