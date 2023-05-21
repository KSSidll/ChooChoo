package com.kssidll.choochoo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kssidll.choochoo.data.data.User
import com.kssidll.choochoo.data.repository.IStationRepository
import com.kssidll.choochoo.data.repository.ITicketRepository
import com.kssidll.choochoo.data.repository.IUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(userRepository: IUserRepository, ticketRepository: ITicketRepository, stationRepository: IStationRepository) : ViewModel() {
    private val userRepository: IUserRepository
    private val ticketRepository: ITicketRepository
    private val stationRepository: IStationRepository

    init {
        this.userRepository = userRepository
        this.ticketRepository = ticketRepository
        this.stationRepository = stationRepository
    }

    fun addUser(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }
}