package com.kssidll.choochoo.ui.showconnections

import kotlin.random.Random

fun generateConnections(amount: Int): List<ConnectionData> {
    return buildList(capacity = amount) {
        for (i in 1 .. amount) {
            val departureHour: Int = Random.nextInt(0,20)
            val departureMinute: Int = Random.nextInt(0,60)
            val arrivalHour: Int = Random.nextInt(departureHour+1,24)
            val arrivalMinute: Int = Random.nextInt(0,60)

            val price: Int = Random.nextInt(400, 2000)

            this.add(
                ConnectionData(
                    price = price,
                    timeDeparture = time(departureHour, departureMinute),
                    timeArrival = time(arrivalHour, arrivalMinute)
                )
            )
        }
    }.sortedBy { connectionData -> connectionData.timeDeparture }
}

fun time(hour: Int, minute: Int): Long {
    return (1000*60*60*(hour-1)) + (1000*60*minute).toLong()
}