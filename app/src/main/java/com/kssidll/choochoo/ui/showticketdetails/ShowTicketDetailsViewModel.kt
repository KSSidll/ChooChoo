package com.kssidll.choochoo.ui.showticketdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowTicketDetailsViewModel @Inject constructor(connectionRepository: IConnectionRepository, ticketRepository: ITicketRepository, stationRepository: IStationRepository) : ViewModel() {
    private val connectionRepository: IConnectionRepository
    private val ticketRepository: ITicketRepository
    private val stationRepository: IStationRepository

    lateinit var ticketData: TicketData

    init {
        this.connectionRepository = connectionRepository
        this.ticketRepository = ticketRepository
        this.stationRepository = stationRepository
    }

    suspend fun fetchData(id: Int) {
        val ticket = ticketRepository.get(id)
        val connection = connectionRepository.get(ticket.connectionId)

        ticketData = TicketData(
            ticketId = ticket.id,
            origin = stationRepository.get(connection.originId).name,
            destination = stationRepository.get(connection.destinationId).name,
            price = connection.price,
            timeDeparture = connection.timeDeparture,
            timeArrival = connection.timeArrival,
            date = ticket.date
        )
    }

    fun cancelTicket(id: Int) = viewModelScope.launch {

    }


}