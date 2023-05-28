package com.kssidll.choochoo.ui.showconnections

import java.sql.Time

data class ConnectionData(
    val origin: String,
    val destination: String,
    val timeDeparture: Time,
    val timeArrival: Time,
    val travelTime: Time
)