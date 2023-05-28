package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

interface IStationRepository {
    suspend fun getAll(): List<Station>
    fun getAllFlow(): Flow<List<Station>>
    suspend fun get(id: Int): Station
    fun getFlow(id: Int): Flow<Station>
    suspend fun getByName(name: String): Station
    fun getByNameFlow(name: String): Flow<Station>
    suspend fun insert(station: Station)
    suspend fun update(station: Station)
    suspend fun delete(station: Station)
}