package com.mahnoosh.athentication.data.datasource.remote.signin

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class DefaultAuthUserDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthUserDataSource {
    override suspend fun createUserWithEmailAndPassword(
        email: String, password: String
    ): AuthResult? {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        continuation.resume(task.result)
                    } else {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Something went wrong!")
                        )
                    }
                }

        }
    }

    override suspend fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? {
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        continuation.resume(task.result)
                    } else {
                        continuation.resumeWithException(
                            task.exception ?: Exception("Something went wrong!")
                        )
                    }
                }

        }
    }
}