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
    fun getAll(): Flow<List<Station>>

    @Query("SELECT * FROM station WHERE id == :id")
    fun get(id: Int): Flow<Station>

    @Insert
    suspend fun insert(station: Station)

    @Update
    suspend fun update(station: Station)

    @Delete
    suspend fun delete(station: Station)
}