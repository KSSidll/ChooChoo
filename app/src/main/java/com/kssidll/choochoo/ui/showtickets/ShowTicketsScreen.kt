package com.kssidll.choochoo.ui.showtickets

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.data.data.Ticket
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun ShowTicketsScreen(
    tickets: List<Ticket>
) {
    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
        ShowTicketsScreenContent(tickets = tickets)
    }
}

@Composable
fun ShowTicketsScreenContent(
    tickets: List<Ticket>
) {

}

@Preview(group = "SearchConnectionScreen", name = "Search Connection Light", apiLevel = 29,showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(group = "SearchConnectionScreen", name = "Search Connection Dark", apiLevel = 29,showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchConnectionScreenPreview() {
    ChooChooTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ShowTicketsScreen(
                tickets = listOf()
            )
        }
    }
}
