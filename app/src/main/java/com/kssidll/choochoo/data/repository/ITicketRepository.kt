package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

interface ITicketRepository {
    fun getAll(): Flow<List<Ticket>>
    fun get(id: Int): Flow<Ticket>
    suspend fun insert(ticket: Ticket)
    suspend fun update(ticket: Ticket)
    suspend fun delete(ticket: Ticket)
}