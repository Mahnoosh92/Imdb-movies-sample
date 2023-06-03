package com.mahnoosh.athentication.data.datasource.remote.signin

import android.app.Activity
import android.os.Parcel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor
import kotlin.Exception


@RunWith(MockitoJUnitRunner::class)
internal class DefaultAuthUserRepositoryDataSourceTest {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var tempTask: Task<AuthResult>

    @Mock
    private lateinit var tempAuthResult: AuthResult


    private lateinit var defaultSignInDataSource: DefaultAuthUserDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        defaultSignInDataSource = DefaultAuthUserDataSource(firebaseAuth = firebaseAuth)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test createUserWithEmailAndPassword is successful`() = runTest(testDispatcher.scheduler) {
        Mockito.`when`(firebaseAuth.createUserWithEmailAndPassword(anyString(), anyString()))
            .thenReturn(TaskTest(true))
        defaultSignInDataSource.createUserWithEmailAndPassword("", "")
        advanceUntilIdle()
        Mockito.verify(firebaseAuth).createUserWithEmailAndPassword(anyString(), anyString())
    }

    @Test
    fun `test createUserWithEmailAndPassword is unsuccessful`() =
        runTest(testDispatcher.scheduler) {
            try {
                Mockito.`when`(
                    firebaseAuth.createUserWithEmailAndPassword(
                        anyString(),
                        anyString()
                    )
                )
                    .thenReturn(TaskTest(false))
                defaultSignInDataSource.createUserWithEmailAndPassword("", "")
            } catch (e: Exception) {
                /*NO_ACTION*/
            }
            advanceUntilIdle()
            Mockito.verify(firebaseAuth).createUserWithEmailAndPassword(anyString(), anyString())
        }
}

class MyAuthResult : AuthResult {
    override fun describeContents(): Int = 1

    override fun writeToParcel(dest: Parcel, flags: Int) {

    }

    override fun getAdditionalUserInfo(): AdditionalUserInfo? = null

    override fun getCredential(): AuthCredential? = null

    override fun getUser(): FirebaseUser? = null

}

class TaskTest(private val isSuccess: Boolean) : Task<AuthResult>() {

    override fun addOnFailureListener(p0: OnFailureListener): Task<AuthResult> {
        return this
    }

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<AuthResult> {
        return this
    }

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<AuthResult> {
        return this
    }

    override fun getException(): Exception? = null

    override fun getResult(): AuthResult = TestAuthResult()

    override fun <X : Throwable?> getResult(p0: Class<X>): AuthResult = this.result

    override fun isCanceled(): Boolean = false

    override fun isComplete(): Boolean = true

    override fun isSuccessful(): Boolean {
        return this.isSuccess
    }

    override fun addOnSuccessListener(
        p0: Executor,
        p1: OnSuccessListener<in AuthResult>
    ): Task<AuthResult> {
        return this
    }

    override fun addOnSuccessListener(
        p0: Activity,
        p1: OnSuccessListener<in AuthResult>
    ): Task<AuthResult> {
        return this
    }

    override fun addOnSuccessListener(p0: OnSuccessListener<in AuthResult>): Task<AuthResult> {
        return this
    }

    override fun addOnCompleteListener(p0: OnCompleteListener<AuthResult>): Task<AuthResult> {
        p0.onComplete(this)
        return this
    }

}

class TestAuthResult : AuthResult {
    override fun describeContents(): Int {
        return 1
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {

    }

    override fun getAdditionalUserInfo(): AdditionalUserInfo? {
        return null
    }

    override fun getCredential(): AuthCredential? {
        return null
    }

    override fun getUser(): FirebaseUser? {
        return null
    }

}



