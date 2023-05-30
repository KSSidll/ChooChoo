package com.kssidll.choochoo.ui.showticketdetails

data class TicketData(
    val ticketId: Int,
    val origin: String,
    val destination: String,
    val price: Int,
    val timeDeparture: Long,
    val timeArrival: Long,
    val date: Long
)