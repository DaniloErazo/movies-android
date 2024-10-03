package com.globant.imdb.model.repository

import com.globant.imdb.database.dao.UserDao
import com.globant.imdb.database.entities.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getUserByEmail(email: String): User {
        return userDao.getUserByEmail(email)
    }

    suspend fun addUser(user: User){
        return userDao.addUser(user)
    }
}