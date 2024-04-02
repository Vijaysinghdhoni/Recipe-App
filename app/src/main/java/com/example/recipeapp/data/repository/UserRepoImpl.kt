package com.example.recipeapp.data.repository

import android.database.sqlite.SQLiteException
import com.example.recipeapp.domain.model.User
import com.example.recipeapp.data.local.UserDao
import com.example.recipeapp.domain.repo.UserRepository
import com.example.recipeapp.util.Resource
import javax.inject.Inject

class UserRepoImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun getUserById(userId: String): Resource<User> {

        return try {
            val usr = userDao.getUser(userId)
            Resource.Success(usr)
        } catch (ex: Exception) {
            Resource.Error(ex.localizedMessage ?: "Unknown User")
        } catch (ex: SQLiteException) {
            Resource.Error(ex.localizedMessage ?: "Unknown User")
        }

    }


}