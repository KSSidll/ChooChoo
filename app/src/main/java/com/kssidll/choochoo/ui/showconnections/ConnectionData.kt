package com.kssidll.choochoo.ui.showconnections

data class ConnectionData(
    var id: Int,
    val price: Int,
    val timeDeparture: Long,
    val timeArrival: Long
) {
    constructor(price: Int, timeDeparture: Long, timeArrival: Long): this(0, price, timeDeparture, timeArrival)
}