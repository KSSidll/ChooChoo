package com.kssidll.choochoo.ui.searchconnection

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kssidll.choochoo.ui.shared.OutlinedTextFieldWithExposedDropdown
import com.kssidll.choochoo.ui.theme.ChooChooTheme

@Composable
fun SearchConnectionScreen(
        onSearchConnection: (SearchConnectionState) -> Unit,
        stations: List<String>
) {
    Box(modifier = Modifier.padding(horizontal = 12.dp)) {
        SearchConnectionScreenContent(
            onSearchConnection = onSearchConnection,
            stations = stations
        )
    }
}

@Composable
fun SearchConnectionScreenContent(
        onSearchConnection: (SearchConnectionState) -> Unit,
        stations: List<String>
) {
    val searchConnectionState by rememberSaveable(stateSaver = SearchConnectionStateSaver) {
        mutableStateOf(SearchConnectionState())
    }

    val originError = remember {
        mutableStateOf(false)
    }

    val destinationError = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5F)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Center
            ) {
                OutlinedTextFieldWithExposedDropdown(
                    placeholder = "Origin",
                    value = searchConnectionState.origin,
                    onValueChange = {searchConnectionState.origin = it},
                    possibleValues = stations,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    isError = originError.value
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Center
            ) {
                OutlinedTextFieldWithExposedDropdown(
                    placeholder = "Destination",
                    value = searchConnectionState.destination,
                    onValueChange = {searchConnectionState.destination = it},
                    possibleValues = stations,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                    isError = destinationError.value
                )
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
                    onClick = {
                        val containsOrigin = stations.contains(searchConnectionState.origin)
                        val containsDestination = stations.contains(searchConnectionState.destination)

                        originError.value = !containsOrigin
                        destinationError.value = !containsDestination

                        if (containsOrigin && containsDestination)
                            onSearchConnection(searchConnectionState)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Icon(
                        Icons.Rounded.Search,
                        modifier = Modifier.size(40.dp),
                        contentDescription = "Search for connections that match set Origin and Destination",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
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
