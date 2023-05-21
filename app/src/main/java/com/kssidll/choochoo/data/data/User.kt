package com.kssidll.choochoo.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val surname: String,
) {
    constructor(name: String, surname: String) : this(0, name, surname)
}