package com.kssidll.choochoo.ui.searchconnection

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

class SearchConnectionState {
    var origin: String by mutableStateOf(String())
    var destination: String by mutableStateOf(String())
}

val SearchConnectionStateSaver = searchConnectionStateSaver(SearchConnectionState())

fun searchConnectionStateSaver(state: SearchConnectionState) = listSaver<SearchConnectionState, Any>(
    save = { listOf(it.origin, it.destination) },
    restore = {
        state.apply {
            origin = it[0] as String
            destination = it[1] as String
        }
    }
)