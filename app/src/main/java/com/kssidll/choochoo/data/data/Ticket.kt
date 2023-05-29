package com.kssidll.choochoo.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(entity = Connection::class, parentColumns = ["id"], childColumns = ["connectionId"], onDelete = ForeignKey.CASCADE)
    ])
data class Ticket(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val connectionId: Int,
    var active: Boolean
) {
    constructor(connectionId: Int) : this(0, connectionId, true)
}