package com.kssidll.choochoo.ui.showconnections

import androidx.lifecycle.ViewModel
import com.kssidll.choochoo.data.data.Connection
import com.kssidll.choochoo.data.data.Station
import com.kssidll.choochoo.data.repository.IConnectionRepository
import com.kssidll.choochoo.data.repository.IStationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ShowConnectionsViewModel @Inject constructor(connectionRepository: IConnectionRepository, stationRepository: IStationRepository) : ViewModel() {
    private val connectionRepository: IConnectionRepository
    private val stationRepository: IStationRepository
    private lateinit var origin: Station
    private lateinit var destination: Station

    var formattedConnections: List<ConnectionData> = listOf()

    init {
        this.connectionRepository = connectionRepository
        this.stationRepository = stationRepository
    }

    suspend fun fetchData(origin: String, destination: String) {
        this.origin = stationRepository.getByName(origin)
        this.destination = stationRepository.getByName(destination)

        val connections = getConnections(this.origin.id, this.destination.id)

        if (connections.isEmpty()) {
            formattedConnections = generateConnections(amount = Random.nextInt(2,6))

            val ids = insertAllData(formattedConnections)

            for (i in ids.indices) {
                formattedConnections[i].id = ids[i].toInt()
            }

        } else {
            formattedConnections = connections.map {
                ConnectionData(
                    id = it.id,
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

    suspend fun insertAll(connections: List<Connection>): List<Long> {
        return connectionRepository.insertAll(connections)
    }

    suspend fun insertAllData(connections: List<ConnectionData>): List<Long> {
        return insertAll(connections.map {
            Connection(
                originId = origin.id,
                destinationId = destination.id,
                price = it.price,
                timeArrival = it.timeArrival,
                timeDeparture = it.timeDeparture
            )
        })
    }
}