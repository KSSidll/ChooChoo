package com.kssidll.choochoo.ui.searchconnection

import androidx.lifecycle.ViewModel
import com.kssidll.choochoo.data.data.Station
import com.kssidll.choochoo.data.repository.IStationRepository
import com.kssidll.choochoo.data.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchConnectionViewModel @Inject constructor(userRepository: IUserRepository, stationRepository: IStationRepository) : ViewModel() {
    private val userRepository: IUserRepository
    private val stationRepository: IStationRepository

    init {
        this.userRepository = userRepository
        this.stationRepository = stationRepository
    }

    fun getStations(): Flow<List<Station>> {
        return stationRepository.getAll()
    }
}