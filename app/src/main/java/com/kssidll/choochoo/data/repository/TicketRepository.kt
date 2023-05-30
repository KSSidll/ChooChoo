package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.TicketDao
import com.kssidll.choochoo.data.data.Ticket
import kotlinx.coroutines.flow.Flow

class TicketRepository(private val ticketDao: TicketDao) : ITicketRepository {

    override suspend fun getAll(): List<Ticket> {
        return ticketDao.getAll()
    }

    override fun getAllFlow(): Flow<List<Ticket>> {
        return ticketDao.getAllFlow()
    }

    override suspend fun get(id: Int): Ticket {
        return ticketDao.get(id)
    }

    override fun getAllActiveFlow(): Flow<List<Ticket>> {
        return ticketDao.getAllActiveFlow()
    }

    override suspend fun getAllActive(): List<Ticket> {
        return ticketDao.getAllActive()
    }

    override fun getActiveFlow(id: Int): Flow<Ticket> {
        return ticketDao.getActiveFlow(id)
    }

    override suspend fun getActive(id: Int): Ticket {
        return ticketDao.getActive(id)
    }

    override suspend fun cancel(id: Int) {
        ticketDao.cancel(id)
    }

    override fun getFlow(id: Int): Flow<Ticket> {
        return ticketDao.getFlow(id)
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