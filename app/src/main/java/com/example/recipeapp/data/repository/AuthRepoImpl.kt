package com.example.recipeapp.data.repository

import android.util.Log
import com.example.recipeapp.domain.repo.AuthenticationRepository
import com.example.recipeapp.util.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import java.io.IOException
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticationRepository {

    override suspend fun loginUserWithEmail(
        email: String,
        password: String
    ): Resource<FirebaseUser?> {
        return try {
            val auth = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(auth.user)
        } catch (ex: FirebaseAuthException) {
            Log.d("mytag", ex.message.toString())
            Resource.Error(ex.message ?: "Unknown User")
        } catch (ex: Exception) {
            Log.d("mytag", ex.message.toString())
            Resource.Error(ex.message ?: "Unknown User")
        }
    }


    override suspend fun reigisterUser(email: String, password: String): Resource<FirebaseUser?> {
        return try {
            val auth = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(auth.user)
        } catch (ex: FirebaseAuthException) {
            Resource.Error(ex.localizedMessage ?: "Unknown Error")
        } catch (ex: IOException) {
            Resource.Error(ex.localizedMessage ?: "Unknown Error")
        } catch (ex: Exception) {
            Resource.Error(ex.localizedMessage ?: "Unknown Error")
        }
    }

}