package com.kssidll.choochoo.data.fake

import com.kssidll.choochoo.data.data.User
import com.kssidll.choochoo.data.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class FakeUserRepository : IUserRepository {
    override fun getAll(): Flow<List<User>> {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun update(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: User) {
        TODO("Not yet implemented")
    }
}