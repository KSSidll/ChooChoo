package com.kssidll.choochoo.ui.showconnections

data class ConnectionData(
    val origin: String,
    val destination: String,
    val price: Int,
    val timeDeparture: Long,
    val timeArrival: Long
)