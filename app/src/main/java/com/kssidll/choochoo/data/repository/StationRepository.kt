package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

class StationRepository(private val stationDao: StationDao) : IStationRepository {

    override fun getAll(): Flow<List<Station>> {
        return stationDao.getAll()
    }

    override fun get(id: Int): Flow<Station> {
        return stationDao.get(id)
    }

    override suspend fun insert(station: Station) {
        stationDao.insert(station)
    }

    override suspend fun update(station: Station) {
        stationDao.update(station)
    }

    override suspend fun delete(station: Station) {
        stationDao.delete(station)
    }
}