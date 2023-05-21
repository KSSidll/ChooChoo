package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.data.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun getAll(): Flow<List<User>>
    fun get(id: Int): Flow<User>
    suspend fun insert(user: User)
    suspend fun update(user: User)
    suspend fun delete(user: User)
}