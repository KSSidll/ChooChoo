package com.kssidll.choochoo.ui.searchconnection

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchConnectionRoute(
        onSearchConnection: () -> Unit
) {
    val searchConnectionViewModel: SearchConnectionViewModel = hiltViewModel()

    SearchConnectionScreen(
        onSearchConnection = onSearchConnection,
        searchConnectionViewModel.getStations().collectAsState(initial = listOf()).value
    )
}