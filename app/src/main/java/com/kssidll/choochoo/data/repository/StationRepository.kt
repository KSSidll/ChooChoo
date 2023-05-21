package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

class StationRepository(private val stationDao: StationDao) {

    fun getAll(): Flow<List<Station>> {
        return stationDao.getAll()
    }

    fun get(id: Int): Flow<Station> {
        return stationDao.get(id)
    }

    suspend fun insert(user: Station) {
        stationDao.insert(user)
    }

    suspend fun update(user: Station) {
        stationDao.update(user)
    }

    suspend fun delete(user: Station) {
        stationDao.delete(user)
    }
}