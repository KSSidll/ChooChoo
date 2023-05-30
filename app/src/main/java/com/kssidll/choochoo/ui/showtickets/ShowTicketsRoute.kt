package com.kssidll.choochoo.ui.showtickets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ShowTicketsRoute(
    onTicketClick: (Int) -> Unit
) {
    val showTicketsViewModel: ShowTicketsViewModel = hiltViewModel()

    ShowTicketsScreen(
        tickets = showTicketsViewModel.getTickets().collectAsState(initial = listOf()).value,
        onTicketClick = {
            onTicketClick(it.id)
        }
    )
}