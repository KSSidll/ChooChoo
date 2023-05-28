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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.shared.SecondaryAppBar
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ShowConnectionsScreen(
    origin: String,
    destination: String,
    connectionsData: List<ConnectionData>,
    onConnectionSelect: (ConnectionData) -> Unit,
    onBack: () -> Unit
) {
    Column {
        SecondaryAppBar(onBack = onBack) {
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

        ShowConnectionsScreenContent(
            connectionsData = connectionsData,
            onConnectionSelect = onConnectionSelect
        )
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun ShowConnectionsScreenContent(
    connectionsData: List<ConnectionData>,
    onConnectionSelect: (ConnectionData) -> Unit
) {
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
                origin = "Origin",
                destination = "Destination",
                connectionsData = generateConnections(3),
                onConnectionSelect = {},
                onBack = {}
            )
        }
    }
}