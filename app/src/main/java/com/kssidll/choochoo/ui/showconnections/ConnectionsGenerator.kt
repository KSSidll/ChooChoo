package com.kssidll.choochoo.ui.showconnections

import java.sql.Time
import kotlin.random.Random

fun generateConnections(
    amount: Int,
    origin: String,
    destination: String,
): List<ConnectionData> {
    return buildList(capacity = amount) {
        for (i in 1 .. amount) {
            val departureHour: Int = Random.nextInt(0,20)
            val departureMinute: Int = Random.nextInt(0,60)
            val arrivalHour: Int = Random.nextInt(departureHour+1,24)
            val arrivalMinute: Int = Random.nextInt(0,60)

            val price: Int = Random.nextInt(400, 2000)

            this.add(
                ConnectionData(
                    origin = origin,
                    destination = destination,
                    price = price,
                    timeDeparture = Time(departureHour, departureMinute).time,
                    timeArrival = Time(arrivalHour, arrivalMinute).time
                )
            )
        }
    }.sortedBy { connectionData -> connectionData.timeDeparture }
}

fun Time(hour: Int, minute: Int): Time {
    return Time(((1000*60*60*(hour-1)) + (1000*60*minute)).toLong())
}