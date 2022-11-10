package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {

    fun authenticate(phone: String)

    fun onVerifyOtp(code: String): Task<AuthResult>

    fun getUserPhone(): String

    fun getUserToken(): String

}