package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

class TicketRepository(private val ticketDao: TicketDao) {

    fun getAll(): Flow<List<Ticket>> {
        return ticketDao.getAll()
    }

    fun get(id: Int): Flow<Ticket> {
        return ticketDao.get(id)
    }

    fun getByUserId(userId: Int): Flow<List<Ticket>> {
        return ticketDao.getByUserId(userId)
    }

    fun getByUserIdAll(userId: Int): Flow<List<Ticket>> {
        return ticketDao.getByUserIdAll(userId)
    }

    suspend fun insert(ticket: Ticket) {
        ticketDao.insert(ticket)
    }

    suspend fun update(ticket: Ticket) {
        ticketDao.update(ticket)
    }

    suspend fun delete(ticket: Ticket) {
        ticketDao.delete(ticket)
    }
}