package com.kssidll.choochoo.ui.showticketdetails

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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ShowTicketDetailsScreen(
    ticket: TicketData,
    onTicketCancel: (TicketData) -> Unit,
    onBack: () -> Unit
) {
    Column {
        SecondaryAppBar(onBack = onBack) {}

        Box(modifier = Modifier.padding(horizontal = 12.dp)) {
            ShowTicketDetailsScreenContent(
                ticket = ticket,
                onTicketCancel = onTicketCancel
            )
        }
    }
}

@Composable
fun ShowTicketDetailsScreenContent(
    ticket: TicketData,
    onTicketCancel: (TicketData) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5F)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val formatter = SimpleDateFormat("d MMM yyyy, E", Locale.ENGLISH)
                Text(
                    text = formatter.format(ticket.date),
                    fontSize = 32.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = ticket.origin,
                            fontSize = 32.sp
                        )
                        Text(
                            text = Time(ticket.timeDeparture).toString().substring(0, 5),
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
                            text = ticket.destination,
                            fontSize = 32.sp
                        )
                        Text(
                            text = Time(ticket.timeArrival).toString().substring(0, 5),
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
                val price = ticket.price.toFloat() / 100
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
                    onClick = { onTicketCancel(ticket) },
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
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Cancel this ticket",
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Cancel ticket",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(group = "ShowTicketDetailsScreen", name = "Show Ticket Details Screen Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "ShowTicketDetailsScreen", name = "Show Ticket Details Screen Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShowTicketDetailsScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowTicketDetailsScreen(
                ticket = TicketData(0,"Origin","Destination",0,0,0,0),
                onTicketCancel = {},
                onBack = {}
            )
        }
    }
}