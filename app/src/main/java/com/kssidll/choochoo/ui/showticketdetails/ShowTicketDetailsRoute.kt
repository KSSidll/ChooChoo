package com.kssidll.choochoo.ui.showticketdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kssidll.choochoo.notification.sendTicketCancelledNotification
import com.kssidll.choochoo.ui.shared.Loading
import kotlinx.coroutines.launch

@Composable
fun ShowTicketDetailsRoute(
    ticketId: Int,
    onBack: () -> Unit,
    onTicketCancel: () -> Unit,
) {
    val showTicketDetailsViewModel: ShowTicketDetailsViewModel= hiltViewModel()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var loading: Boolean by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            showTicketDetailsViewModel.fetchData(ticketId)
            loading = false
        }
    }

    if (loading) {
        Loading()
    } else {
        ShowTicketDetailsScreen(
            ticket = showTicketDetailsViewModel.ticketData,
            onTicketCancel = {
                showTicketDetailsViewModel.cancelTicket(it.ticketId)

                sendTicketCancelledNotification(
                    context = context,
                    origin = it.origin,
                    destination = it.destination,
                    price = it.price
                )

                onTicketCancel()
            },
            onBack = onBack
        )
    }
}
