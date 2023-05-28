package com.kssidll.choochoo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kssidll.choochoo.data.data.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {
    @Query("SELECT * FROM station ORDER BY id ASC")
    suspend fun getAll(): List<Station>

    @Query("SELECT * FROM station ORDER BY id ASC")
    fun getAllFlow(): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE id == :id")
    suspend fun get(id: Int): Station

    @Query("SELECT * FROM station WHERE id == :id")
    fun getFlow(id: Int): Flow<Station>

    @Query("SELECT * FROM station WHERE name == :name")
    suspend fun getByName(name: String): Station

    @Query("SELECT * FROM station WHERE name == :name")
    fun getByNameFlow(name: String): Flow<Station>

    @Insert
    suspend fun insert(station: Station)

    @Update
    suspend fun update(station: Station)

    @Delete
    suspend fun delete(station: Station)
}