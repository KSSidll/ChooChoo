package com.kssidll.choochoo.ui.showtickets

import androidx.lifecycle.ViewModel
import com.kssidll.choochoo.data.data.Ticket
import com.kssidll.choochoo.data.repository.ITicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ShowTicketsViewModel @Inject constructor(ticketsRepository: ITicketRepository) : ViewModel() {
    private val ticketsRepository: ITicketRepository

    init {
        this.ticketsRepository = ticketsRepository
    }

    fun getTickets(): Flow<List<Ticket>> {
        return ticketsRepository.getAll()
    }
}