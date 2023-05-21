package com.kssidll.choochoo.data.fake

import com.kssidll.choochoo.data.data.Station
import com.kssidll.choochoo.data.repository.IStationRepository
import kotlinx.coroutines.flow.Flow

class FakeStationRepository : IStationRepository {
    override fun getAll(): Flow<List<Station>> {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Flow<Station> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(station: Station) {
        TODO("Not yet implemented")
    }

    override suspend fun update(station: Station) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(station: Station) {
        TODO("Not yet implemented")
    }
}