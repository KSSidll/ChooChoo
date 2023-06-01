package com.kssidll.choochoo.ui.showconnectiondetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kssidll.choochoo.notification.scheduleDepartureTimeCloseNotification
import com.kssidll.choochoo.notification.sendTicketBoughtNotification
import com.kssidll.choochoo.ui.shared.Loading
import kotlinx.coroutines.launch

@Composable
fun ShowConnectionDetailsRoute(
    connectionId: Int,
    date: Long,
    onBack: () -> Unit,
    onTicketBuy: () -> Unit,
) {
    val showConnectionDetailsViewModel: ShowConnectionDetailsViewModel= hiltViewModel()
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    var loading: Boolean by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            showConnectionDetailsViewModel.fetchData(connectionId)
            loading = false
        }
    }

    if (loading) {
        Loading()
    } else {
        ShowConnectionDetailsScreen(
            connection = showConnectionDetailsViewModel.connectionData,
            date = date,
            onTicketBuy = {
                scope.launch {
                    val ticketId = showConnectionDetailsViewModel.addTicket(it)

                    sendTicketBoughtNotification(
                        context = context,
                        origin = showConnectionDetailsViewModel.connectionData.origin,
                        destination = showConnectionDetailsViewModel.connectionData.destination,
                        price = showConnectionDetailsViewModel.connectionData.price,
                        ticketId = ticketId.toInt()
                    )

                    scheduleDepartureTimeCloseNotification(
                        context = context,
                        origin = showConnectionDetailsViewModel.connectionData.origin,
                        destination = showConnectionDetailsViewModel.connectionData.destination,
                        delay = 5000L,
                        ticketId = ticketId.toInt()
                    )
                }

                onTicketBuy()
            },
            onBack = onBack
        )
    }
}
