package com.kssidll.choochoo.ui.showconnections

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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
        ShowConnectionsScreenContent(
            origin = origin,
            destination = destination,
            onConnectionSelect = onConnectionSelect
        )
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

@SuppressLint("SimpleDateFormat")
@Composable
fun ShowConnectionsScreenContent(
    origin: String,
    destination: String,
    onConnectionSelect: (ConnectionData) -> Unit
) {
    val connectionsData = generateConnections(
        amount = 3,
        origin = origin,
        destination = destination
    )

    LazyColumn {
        for (i in 0..30) {
            this.item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        horizontalArrangement = Center,
                        verticalAlignment = CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val currentTime = Calendar.getInstance().time
                        val time = currentTime.time.plus(i*1000*60*60*24)
                        val formatter = SimpleDateFormat("dd MMM", Locale.ENGLISH)
                        Text(text = formatter.format(time).toString())
                    }
                }
            }
            for (connection in connectionsData) {
                this.item {
                    Column {
                        Box(modifier = Modifier.clickable { onConnectionSelect(connection) }) {
                            ConnectionItem(
                                connectionData = connection,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                            )
                        }
                        if (connection != connectionsData.last()) {
                            Divider()
                        }
                    }
                }
            }
        }
    }
}

//@Preview(group = "ShowConnectionsScreen", name = "Show Connections Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "ShowConnectionsScreen", name = "Show Connections Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowConnectionsScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowConnectionsScreen(
                origin = "origin",
                destination = "destination",
                onConnectionSelect = {},
                onBackClick = {}
            )
        }
    }
}