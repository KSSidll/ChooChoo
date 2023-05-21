package com.kssidll.choochoo.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Station(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
) {
    constructor(name: String) : this(0, name)
}