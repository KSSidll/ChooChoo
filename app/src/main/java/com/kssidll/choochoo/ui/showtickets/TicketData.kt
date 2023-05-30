package com.kssidll.choochoo.ui.showtickets

data class TicketData (
    val id: Int,
    val origin: String,
    val destination: String,
    val timeDeparture: Long,
    val date: Long
)