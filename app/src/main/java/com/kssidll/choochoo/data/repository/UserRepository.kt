package com.kssidll.choochoo.data.repository

import com.kssidll.choochoo.data.dao.UserDao
import com.kssidll.choochoo.data.data.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) : IUserRepository{

    override fun getAll(): Flow<List<User>> {
        return userDao.getAll()
    }

    override fun get(id: Int): Flow<User> {
        return userDao.get(id)
    }

    override suspend fun insert(user: User) {
        userDao.insert(user)
    }

    override suspend fun update(user: User) {
        userDao.update(user)
    }

    override suspend fun delete(user: User) {
        userDao.delete(user)
    }
}