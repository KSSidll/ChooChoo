package com.kssidll.choochoo.ui.showconnections

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun ShowConnectionsScreen(
    origin: String,
    destination: String,
    onConnectionSelect: (ConnectionData) -> Unit,
    onBackClick: () -> Unit
) {
    Column {
        ShowConnectionsScreenAppBar(
            origin = origin,
            destination = destination,
            onBackClick = onBackClick
        )
        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
            ShowConnectionsScreenContent(
                origin = origin,
                destination = destination,
                onConnectionSelect = onConnectionSelect
            )
        }
    }
}

@Composable
fun ShowConnectionsScreenAppBar(
    origin: String,
    destination: String,
    onBackClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {

        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                Icons.Rounded.ArrowBack,
                contentDescription = "Go back",
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = origin,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = destination,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun ShowConnectionsScreenContent(
    origin: String,
    destination: String,
    onConnectionSelect: (ConnectionData) -> Unit
) {
    val data = generateConnections(
        amount = 3,
        origin = origin,
        destination = destination
    )
    Column {
        for (item in data) {
            Column {
                Text(text = "${item.origin} - origin")
                Text(text = "${item.destination} - destination")
                Text(text = "${item.timeDeparture.toString().substring(0,5)} - timeDeparture")
                Text(text = "${item.timeArrival.toString().substring(0,5)} - timeArrival")
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview(group = "ShowConnectionsScreen", name = "Show Connections Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "ShowConnectionsScreen", name = "Show Connections Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowConnectionsScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowConnectionsScreen(
                origin = "test",
                destination = "test2",
                onConnectionSelect = {},
                onBackClick = {}
            )
        }
    }
}