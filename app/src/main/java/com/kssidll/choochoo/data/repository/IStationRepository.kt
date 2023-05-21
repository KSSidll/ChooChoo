package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

interface IStationRepository {
    fun getAll(): Flow<List<Station>>
    fun get(id: Int): Flow<Station>
    suspend fun insert(station: Station)
    suspend fun update(station: Station)
    suspend fun delete(station: Station)
}