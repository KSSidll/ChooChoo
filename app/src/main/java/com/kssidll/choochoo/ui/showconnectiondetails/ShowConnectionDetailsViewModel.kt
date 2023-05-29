package com.kssidll.choochoo.ui.showconnectiondetails

import androidx.lifecycle.ViewModel
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowConnectionDetailsViewModel @Inject constructor(connectionRepository: IConnectionRepository, ticketRepository: ITicketRepository, stationRepository: IStationRepository) : ViewModel() {
    private val connectionRepository: IConnectionRepository
    private val ticketRepository: ITicketRepository
    private val stationRepository: IStationRepository

    lateinit var connectionData: ConnectionData

    init {
        this.connectionRepository = connectionRepository
        this.ticketRepository = ticketRepository
        this.stationRepository = stationRepository
    }

    suspend fun fetchData(id: Int) {
        val connection = connectionRepository.get(id)

        connectionData = ConnectionData(
            connectionId = connection.id,
            origin = stationRepository.get(connection.originId).name,
            destination = stationRepository.get(connection.destinationId).name,
            price = connection.price,
            timeDeparture = connection.timeDeparture,
            timeArrival = connection.timeArrival
        )
    }
}