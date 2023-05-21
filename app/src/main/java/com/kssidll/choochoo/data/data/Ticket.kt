package com.kssidll.choochoo.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
                       ForeignKey(entity = Station::class, parentColumns = ["id"], childColumns = ["originId"], onDelete = ForeignKey.CASCADE),
                       ForeignKey(entity = Station::class, parentColumns = ["id"], childColumns = ["destinationId"], onDelete = ForeignKey.CASCADE)])
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val userId: Int,
    @ColumnInfo(index = true) val originId: Int,
    @ColumnInfo(index = true) val destinationId: Int,
    val price: Int,
    var active: Boolean
) {
    constructor(userId: Int, originId: Int, destinationId: Int, price: Int) : this(0, userId, originId, destinationId, price, true)
}