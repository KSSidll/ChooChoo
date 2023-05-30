package com.kssidll.choochoo.ui.showtickets

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ShowTicketsScreen(
    tickets: List<TicketData>
) {
    ShowTicketsScreenContent(tickets = tickets)
}

@Composable
fun ShowTicketsScreenContent(
    tickets: List<TicketData>
) {
    val groups = tickets.groupBy { it.date }.toSortedMap()
    val dateFormatter = SimpleDateFormat("d MMM, E", Locale.ENGLISH)
    Column {
        for (group in groups) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = dateFormatter.format(Date(group.key)),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp
                )
            }
            Column {
                for ((index, ticket) in group.value.withIndex()) {
                    Box(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)) {
                        TicketItem(ticket = ticket)
                    }
                    if (index < group.value.lastIndex) {
                        Divider(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Preview(group = "ShowTicketsScreen", name = "Show Tickets Screen Light", apiLevel = 29,showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(group = "ShowTicketsScreen", name = "Show Tickets Screen Dark", apiLevel = 29,showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ShowTicketsScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowTicketsScreen(
                tickets = listOf(
                    TicketData(0, "Origin", "Destination", 0, Calendar.getInstance().time.time),
                    TicketData(0, "Origin", "Destination", 0, Calendar.getInstance().time.time),
                    TicketData(0, "Origin", "Destination", 0, Calendar.getInstance().time.time.plus(2*1000*60*60*24)),
                    TicketData(0, "Origin", "Destination", 0, Calendar.getInstance().time.time.plus(6*1000*60*60*24))
                )
            )
        }
    }
}
