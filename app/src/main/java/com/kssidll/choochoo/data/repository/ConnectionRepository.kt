package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.ConnectionDao
import com.kssidll.choochoo.data.data.Connection
import kotlinx.coroutines.flow.Flow

class ConnectionRepository(private val connectionDao: ConnectionDao) : IConnectionRepository {

    override suspend fun getAll(): List<Connection> {
        return connectionDao.getAll()
    }

    override fun getAllFlow(): Flow<List<Connection>> {
        return connectionDao.getAllFlow()
    }

    override suspend fun get(id: Int): Connection {
        return connectionDao.get(id)
    }

    override fun getFlow(id: Int): Flow<Connection> {
        return connectionDao.getFlow(id)
    }

    override suspend fun getByOriginId(originId: Int): List<Connection> {
        return connectionDao.getByOriginId(originId)
    }

    override fun getByOriginIdFlow(originId: Int): Flow<List<Connection>> {
        return connectionDao.getByOriginIdFlow(originId)
    }

    override suspend fun getByDestinationId(destinationId: Int): List<Connection> {
        return connectionDao.getByDestinationId(destinationId)
    }

    override fun getByDestinationIdFlow(destinationId: Int): Flow<List<Connection>> {
        return connectionDao.getByDestinationIdFlow(destinationId)
    }

    override suspend fun getByOriginDestinationId(originId: Int, destinationId: Int): List<Connection> {
        return connectionDao.getByOriginDestinationId(originId, destinationId)
    }

    override fun getByOriginDestinationIdFlow(originId: Int, destinationId: Int): Flow<List<Connection>> {
        return connectionDao.getByOriginDestinationIdFlow(originId, destinationId)
    }

    override suspend fun insert(connection: Connection) {
        connectionDao.insert(connection)
    }

    override suspend fun insertAll(stations: List<Connection>) {
        connectionDao.insertAll(stations)
    }

    override suspend fun update(connection: Connection) {
        connectionDao.update(connection)
    }

    override suspend fun delete(connection: Connection) {
        connectionDao.delete(connection)
    }
}