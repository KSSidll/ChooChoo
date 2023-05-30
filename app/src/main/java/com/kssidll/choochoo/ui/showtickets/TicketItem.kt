package com.kssidll.choochoo.ui.showtickets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.kssidll.choochoo.ui.theme.ChooChooTheme
import java.sql.Time
import java.util.Calendar

@Composable
fun TicketItem(ticket: TicketData) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row ( verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = ticket.origin,
                fontSize = 20.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                Modifier.size(25.dp)
            )
            Text(
                text = ticket.destination,
                fontSize = 20.sp
            )
        }

        Text(
            text = "Departure at ${Time(ticket.timeDeparture).toString().substring(0,5)}",
            fontSize = 12.sp
        )
    }
}

@Preview(group = "SearchConnectionScreen", name = "Search Connection Light", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(group = "SearchConnectionScreen", name = "Search Connection Dark", apiLevel = 29, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TicketItemPreview() {
    ChooChooTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TicketItem(
                TicketData(0, "Origin", "Destination", 0, Calendar.getInstance().time.time)
            )
        }
    }
}