package com.kssidll.choochoo.ui.searchconnection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchConnectionRoute(
    onSearchConnection: (SearchConnectionState) -> Unit
) {
    val searchConnectionViewModel: SearchConnectionViewModel = hiltViewModel()

    SearchConnectionScreen(
        onSearchConnection = onSearchConnection,
        stations = searchConnectionViewModel.getStations().collectAsState(initial = listOf()).value.map { station -> station.name }
    )
}