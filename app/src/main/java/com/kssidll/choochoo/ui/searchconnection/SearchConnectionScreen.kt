package com.kssidll.choochoo.ui.searchconnection

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.data.data.User
import com.kssidll.choochoo.ui.shared.OutlinedTextFieldWithExposedDropdown
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun SearchConnectionScreen(
        onSearchConnection: (SearchConnectionState) -> Unit,
        stations: List<String>,
        user: User
) {
    val searchConnectionState by rememberSaveable(stateSaver = SearchConnectionStateSaver) {
        mutableStateOf(SearchConnectionState())
    }

    Column(modifier = Modifier.fillMaxSize()) {

        SearchConnectionScreenHeader(height = 50.dp)

        // screen content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp, 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.3F)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    contentAlignment = Center
                ) {
                    OutlinedTextFieldWithExposedDropdown(
                        placeholder = "Origin",
                        value = searchConnectionState.origin,
                        onValueChange = {searchConnectionState.origin = it},
                        possibleValues = stations,
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    contentAlignment = Center
                ) {
                    OutlinedTextFieldWithExposedDropdown(
                        placeholder = "Destination",
                        value = searchConnectionState.destination,
                        onValueChange = {searchConnectionState.destination = it},
                        possibleValues = stations,
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    )
                }

                // TODO time form

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(0.2F)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Center
                ) {
                    Text(text = user.name)
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Center
                ) {
                    Text(text = user.surname)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {

                // center content vertically but offset it up by 15% of parent height
                Box(
                    modifier = Modifier.fillMaxHeight(0.7F),
                    contentAlignment = Center
                ) {

                    Button(
                        onClick = { onSearchConnection(searchConnectionState) },
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

@Composable
fun SearchConnectionScreenHeader(height: Dp) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
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
            contentAlignment = Center
        ) {
            /* TODO create app logo and call it here*/
            Text(
                text = "ChooChoo",
                color = MaterialTheme.colorScheme.onPrimary)
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
                stations = listOf(),
                user = User("name","surname")
            )
        }
    }
}
