package com.kssidll.choochoo.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Connection::class, parentColumns = ["id"], childColumns = ["connectionId"], onDelete = ForeignKey.CASCADE)
    ])
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val userId: Int,
    @ColumnInfo(index = true) val connectionId: Int,
    var active: Boolean
) {
    constructor(userId: Int, connectionId: Int) : this(0, userId, connectionId, true)
}