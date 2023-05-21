package com.kssidll.choochoo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kssidll.choochoo.data.data.User
import com.kssidll.choochoo.data.repository.StationRepository
import com.kssidll.choochoo.data.repository.TicketRepository
import com.kssidll.choochoo.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(userRepository: UserRepository, ticketRepository: TicketRepository, stationRepository: StationRepository) : ViewModel() {
    private val userRepository: UserRepository
    private val ticketRepository: TicketRepository
    private val stationRepository: StationRepository

    init {
        this.userRepository = userRepository
        this.ticketRepository = ticketRepository
        this.stationRepository = stationRepository
    }

    fun addUser(user: User) = viewModelScope.launch {
        userRepository.insert(user)
    }
}