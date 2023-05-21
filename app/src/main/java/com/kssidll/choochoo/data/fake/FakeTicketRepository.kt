package com.kssidll.choochoo.data.fake

import com.kssidll.choochoo.data.data.Ticket
import com.kssidll.choochoo.data.repository.ITicketRepository
import kotlinx.coroutines.flow.Flow

class FakeTicketRepository : ITicketRepository {
    override fun getAll(): Flow<List<Ticket>> {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Flow<Ticket> {
        TODO("Not yet implemented")
    }

    override fun getByUserId(userId: Int): Flow<List<Ticket>> {
        TODO("Not yet implemented")
    }

    override fun getByUserIdAll(userId: Int): Flow<List<Ticket>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(ticket: Ticket) {
        TODO("Not yet implemented")
    }

    override suspend fun update(ticket: Ticket) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(ticket: Ticket) {
        TODO("Not yet implemented")
    }
}