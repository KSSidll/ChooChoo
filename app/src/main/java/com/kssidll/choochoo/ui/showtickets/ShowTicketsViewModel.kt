package com.kssidll.choochoo.ui.showtickets

import androidx.lifecycle.ViewModel
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ShowTicketsViewModel @Inject constructor(ticketsRepository: ITicketRepository, stationRepository: IStationRepository, connectionRepository: IConnectionRepository) : ViewModel() {
    private val ticketsRepository: ITicketRepository
    private val stationRepository: IStationRepository
    private val connectionRepository: IConnectionRepository

    init {
        this.ticketsRepository = ticketsRepository
        this.stationRepository = stationRepository
        this.connectionRepository = connectionRepository
    }

    fun getTickets(): Flow<List<TicketData>> {
        return ticketsRepository.getAllFlow().map {
            it.map { ticket ->
                val connection = connectionRepository.get(ticket.connectionId)
                TicketData(
                    id = ticket.id,
                    origin = stationRepository.get(connection.originId).name,
                    destination = stationRepository.get(connection.destinationId).name,
                    timeDeparture = connection.timeDeparture,
                    date = ticket.date
                )
            }
        }
    }
}