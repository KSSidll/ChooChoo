package com.kssidll.choochoo.ui.showconnections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kssidll.choochoo.data.data.Connection
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowConnectionsViewModel @Inject constructor(connectionRepository: IConnectionRepository, stationRepository: IStationRepository) : ViewModel() {
    private val connectionRepository: IConnectionRepository
    private val stationRepository: IStationRepository

    var formattedConnections: List<ConnectionData> = listOf()

    init {
        this.connectionRepository = connectionRepository
        this.stationRepository = stationRepository
    }

    suspend fun fetchData(origin: String, destination: String) {
        val connections = getConnections(origin, destination)

        if (connections.isEmpty()) {
            formattedConnections = generateConnections(
                amount = 3,
                origin = origin,
                destination = destination
            )

            insertAllData(formattedConnections)
        } else {
            formattedConnections = connections.map {
                ConnectionData(
                    origin = origin,
                    destination = destination,
                    timeDeparture = it.timeDeparture,
                    timeArrival = it.timeArrival,
                    price = it.price
                )
            }
        }
    }

    suspend fun getConnections(originId: Int, destinationId: Int): List<Connection> {
        return connectionRepository.getByOriginDestinationId(originId, destinationId)
    }

    suspend fun getConnections(origin: String, destination: String): List<Connection> {
        return getConnections(
            originId = stationRepository.getByName(origin).id,
            destinationId = stationRepository.getByName(destination).id
        )
    }

    fun insertAll(connections: List<Connection>) = viewModelScope.launch {
        connectionRepository.insertAll(connections)
    }

    fun insertAllData(connections: List<ConnectionData>) = viewModelScope.launch {
        insertAll(connections.map {
            Connection(
                originId = stationRepository.getByName(it.origin).id,
                destinationId = stationRepository.getByName(it.destination).id,
                price = it.price,
                timeArrival = it.timeArrival,
                timeDeparture = it.timeDeparture
            )
        })
    }
}