package com.kssidll.choochoo.ui.showconnections

import androidx.compose.runtime.Composable

@Composable
fun ShowConnectionsRoute(
    origin: String,
    destination: String,
    onConnectionSelect: (ConnectionData) -> Unit,
    onBackClick: () -> Unit
) {
    ShowConnectionsScreen(
        origin = origin,
        destination = destination,
        onConnectionSelect = onConnectionSelect,
        onBackClick = onBackClick
    )
}