package com.kssidll.choochoo.ui.showconnections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kssidll.choochoo.data.data.Connection
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ShowConnectionsViewModel @Inject constructor(connectionRepository: IConnectionRepository, stationRepository: IStationRepository) : ViewModel() {
    private val connectionRepository: IConnectionRepository
    private val stationRepository: IStationRepository
    private var origin: String = ""
    private var destination: String = ""

    var formattedConnections: List<ConnectionData> = listOf()

    init {
        this.connectionRepository = connectionRepository
        this.stationRepository = stationRepository
    }

    suspend fun fetchData(origin: String, destination: String) {
        this.origin = origin
        this.destination = destination

        val connections = getConnections(origin, destination)

        if (connections.isEmpty()) {
            formattedConnections = generateConnections(amount = Random.nextInt(2,6))

            insertAllData(formattedConnections)
        } else {
            formattedConnections = connections.map {
                ConnectionData(
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
                originId = stationRepository.getByName(origin).id,
                destinationId = stationRepository.getByName(destination).id,
                price = it.price,
                timeArrival = it.timeArrival,
                timeDeparture = it.timeDeparture
            )
        })
    }
}