package com.kssidll.choochoo.ui.showconnectiondetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
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
                showConnectionDetailsViewModel.addTicket(it)
                onTicketBuy()
            },
            onBack = onBack
        )
    }
}
