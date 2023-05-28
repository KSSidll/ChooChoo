package com.kssidll.choochoo.ui.showconnections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.kssidll.choochoo.ui.shared.Loading
import kotlinx.coroutines.launch

@Composable
fun ShowConnectionsRoute(
    origin: String,
    destination: String,
    onConnectionSelect: (ConnectionData) -> Unit,
    onBackClick: () -> Unit
) {
    val showConnectionsViewModel: ShowConnectionsViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    var loading: Boolean by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            showConnectionsViewModel.fetchData(origin, destination)
            loading = false
        }
    }

    if (loading) {
        Loading()
    } else {
        ShowConnectionsScreen(
            connectionsData = showConnectionsViewModel.formattedConnections,
            onConnectionSelect = onConnectionSelect,
            onBackClick = onBackClick
        )
    }

}