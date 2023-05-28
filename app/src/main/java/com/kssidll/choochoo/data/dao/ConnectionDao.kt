package com.kssidll.choochoo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kssidll.choochoo.data.data.Connection
import kotlinx.coroutines.flow.Flow

@Dao
interface ConnectionDao {
    @Query("SELECT * FROM connection ORDER BY id ASC")
    suspend fun getAll(): List<Connection>

    @Query("SELECT * FROM connection ORDER BY id ASC")
    fun getAllFlow(): Flow<List<Connection>>

    @Query("SELECT * FROM Connection WHERE id == :id")
    suspend fun get(id: Int): Connection

    @Query("SELECT * FROM Connection WHERE id == :id")
    fun getFlow(id: Int): Flow<Connection>

    @Query("SELECT * FROM Connection WHERE originId == :originId")
    suspend fun getByOriginId(originId: Int): List<Connection>

    @Query("SELECT * FROM Connection WHERE originId == :originId")
    fun getByOriginIdFlow(originId: Int): Flow<List<Connection>>

    @Query("SELECT * FROM Connection WHERE destinationId == :destinationId")
    suspend fun getByDestinationId(destinationId: Int): List<Connection>

    @Query("SELECT * FROM Connection WHERE destinationId == :destinationId")
    fun getByDestinationIdFlow(destinationId: Int): Flow<List<Connection>>

    @Query("SELECT * FROM Connection WHERE originId == :originId AND destinationId == :destinationId")
    suspend fun getByOriginDestinationId(originId: Int, destinationId: Int): List<Connection>

    @Query("SELECT * FROM Connection WHERE originId == :originId AND destinationId == :destinationId")
    fun getByOriginDestinationIdFlow(originId: Int, destinationId: Int): Flow<List<Connection>>

    @Insert
    suspend fun insert(station: Connection)

    @Insert
    suspend fun insertAll(stations: List<Connection>)

    @Update
    suspend fun update(station: Connection)

    @Delete
    suspend fun delete(station: Connection)
}