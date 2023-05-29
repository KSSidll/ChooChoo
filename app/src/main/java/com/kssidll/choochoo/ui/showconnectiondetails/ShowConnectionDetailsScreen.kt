package com.kssidll.choochoo.ui.showconnectiondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kssidll.choochoo.ui.shared.SecondaryAppBar
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.sql.Time
import java.util.Locale

@Composable
fun ShowConnectionDetailsScreen(
    connection: ConnectionData,
    onBack: () -> Unit,
    onTicketBuy: () -> Unit
) {
    Column {
        SecondaryAppBar(onBack = onBack) {}

        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
            ShowConnectionDetailsScreenContent(
                connection = connection,
                onTicketBuy = onTicketBuy
            )
        }
    }
}

@Composable
fun ShowConnectionDetailsScreenContent(
    connection: ConnectionData,
    onTicketBuy: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5F)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = connection.origin,
                            fontSize = 32.sp
                        )
                        Text(
                            text = Time(connection.timeDeparture).toString().substring(0, 5),
                            fontSize = 28.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        Modifier.size(48.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = connection.destination,
                            fontSize = 32.sp
                        )
                        Text(
                            text = Time(connection.timeArrival).toString().substring(0, 5),
                            fontSize = 28.sp
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                val price = connection.price.toFloat() / 100
                Text(
                    text = String.format(Locale("en", "US"), "$%.2f", price),
                    fontSize = 28.sp
                )
            }

            // center content vertically but offset it up by 15% of parent height
            Box(
                modifier = Modifier.fillMaxHeight(0.7F),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = onTicketBuy,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Buy ticket for this connection",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Buy ticket",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(group = "ShowConnectionDetailsScreen", name = "Show Connection Details Screen Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "ShowConnectionDetailsScreen", name = "Show Connection Details Screen Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowConnectionDetailsScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowConnectionDetailsScreen(
                connection = ConnectionData(0,"Origin","Destination",0,0,0),
                onBack = {},
                onTicketBuy = {}
            )
        }
    }
}