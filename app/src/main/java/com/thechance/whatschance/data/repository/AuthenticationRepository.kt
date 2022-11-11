package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.thechance.whatschance.domain.models.Message

interface AuthenticationRepository {

    fun authenticate(phone: String)

    fun onVerifyOtp(code: String): Task<AuthResult>

    fun getUserPhone(): String

    fun getUserToken(): String


    ///////////test /////////////
    suspend fun insertUser()

    suspend fun insertMessage(message: Message)

    suspend fun getMessage(): Message
}