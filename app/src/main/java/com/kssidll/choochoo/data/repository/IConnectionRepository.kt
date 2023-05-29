package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Connection
import kotlinx.coroutines.flow.Flow

interface IConnectionRepository {
    suspend fun getAll(): List<Connection>
    fun getAllFlow(): Flow<List<Connection>>
    suspend fun get(id: Int): Connection
    fun getFlow(id: Int): Flow<Connection>
    suspend fun getByOriginId(originId: Int): List<Connection>
    fun getByOriginIdFlow(originId: Int): Flow<List<Connection>>
    suspend fun getByDestinationId(destinationId: Int): List<Connection>
    fun getByDestinationIdFlow(destinationId: Int): Flow<List<Connection>>
    suspend fun getByOriginDestinationId(originId: Int, destinationId: Int): List<Connection>
    fun getByOriginDestinationIdFlow(originId: Int, destinationId: Int): Flow<List<Connection>>
    suspend fun insert(connection: Connection): Long
    suspend fun insertAll(stations: List<Connection>): List<Long>
    suspend fun update(connection: Connection)
    suspend fun delete(connection: Connection)
}