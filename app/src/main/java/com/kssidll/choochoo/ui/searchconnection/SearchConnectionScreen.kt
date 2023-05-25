package com.kssidll.choochoo.ui.searchconnection

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
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
import com.kssidll.choochoo.data.data.Station
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun SearchConnectionScreen(
        onSearchConnection: () -> Unit,
        stations: List<Station>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // header
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp, 0.dp)
        ) {

            // button that shows side panel
            IconButton(
                onClick = { /*TODO show side panel*/ }
            ) {
                Icon(
                    Icons.Rounded.Menu,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp),
                    tint = MaterialTheme.colorScheme.onPrimary)
            }

            // app logo
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                /* TODO create app logo and call it here*/
                Text(
                    text = "ChooChoo",
                    color = MaterialTheme.colorScheme.onPrimary)
            }
        }

        // screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5F)
                    .fillMaxWidth()
            ) {
                // TODO origin, departure and time form

                // TODO show current user information?
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                // center content vertically but offset it up by 15% of parent height
                Box(
                    modifier = Modifier.fillMaxHeight(0.7F),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = onSearchConnection,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    ) {
                        Icon(
                            Icons.Rounded.Search,
                            modifier = Modifier.size(40.dp),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
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
            SearchConnectionScreen(
                onSearchConnection = {},
                stations = listOf()
            )
        }
    }
}
