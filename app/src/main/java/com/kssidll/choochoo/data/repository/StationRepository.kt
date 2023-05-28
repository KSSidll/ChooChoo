package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.StationDao
import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

class StationRepository(private val stationDao: StationDao) : IStationRepository {

    override suspend fun getAll(): List<Station> {
        return stationDao.getAll()
    }

    override fun getAllFlow(): Flow<List<Station>> {
        return stationDao.getAllFlow()
    }

    override suspend fun get(id: Int): Station {
        return stationDao.get(id)
    }

    override fun getFlow(id: Int): Flow<Station> {
        return stationDao.getFlow(id)
    }

    override suspend fun getByName(name: String): Station {
        return stationDao.getByName(name)
    }

    override fun getByNameFlow(name: String): Flow<Station> {
        return stationDao.getByNameFlow(name)
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