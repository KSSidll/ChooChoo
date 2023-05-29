package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

class TicketRepository(private val ticketDao: TicketDao) : ITicketRepository {

    override fun getAll(): Flow<List<Ticket>> {
        return ticketDao.getAll()
    }

    override fun get(id: Int): Flow<Ticket> {
        return ticketDao.get(id)
    }

    override suspend fun insert(ticket: Ticket) {
        ticketDao.insert(ticket)
    }

    override suspend fun update(ticket: Ticket) {
        ticketDao.update(ticket)
    }

    override suspend fun delete(ticket: Ticket) {
        ticketDao.delete(ticket)
    }
}