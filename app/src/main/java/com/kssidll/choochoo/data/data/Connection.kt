package com.kssidll.choochoo.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Station::class, parentColumns = ["id"], childColumns = ["originId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Station::class, parentColumns = ["id"], childColumns = ["destinationId"], onDelete = ForeignKey.CASCADE)
    ])
data class Connection(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val originId: Int,
    @ColumnInfo(index = true) val destinationId: Int,
    val price: Int,
    val timeDeparture: Long,
    val timeArrival: Long,
    var active: Boolean
) {
    constructor(
        originId: Int,
        destinationId: Int,
        price: Int,
        timeDeparture: Long,
        timeArrival: Long,
    ) : this(
        0,
        originId,
        destinationId,
        price,
        timeDeparture,
        timeArrival,
        true)
}