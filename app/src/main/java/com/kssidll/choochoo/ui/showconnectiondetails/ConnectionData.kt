package com.kssidll.choochoo.ui.showconnectiondetails

data class ConnectionData(
    val connectionId: Int,
    val origin: String,
    val destination: String,
    val price: Int,
    val timeDeparture: Long,
    val timeArrival: Long
)