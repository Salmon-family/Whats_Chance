package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.thechance.whatschance.data.PhoneAuthCallBack
import com.thechance.whatschance.ui.main.MainActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val context: MainActivity,
    private val authCallbacks: PhoneAuthCallBack
) : AuthenticationRepository {

    private fun signInWithAuthCredential(credential: PhoneAuthCredential): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    override fun authenticate(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setCallbacks(authCallbacks)
            .setActivity(context)
            .setTimeout(120L, TimeUnit.SECONDS)
            .setPhoneNumber(phone)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    override fun onVerifyOtp(code: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(authCallbacks.getVerificationID(), code)
        return signInWithAuthCredential(credential)
    }

    override fun getUserPhone(): String {
        return auth.currentUser?.phoneNumber.orEmpty()
    }

    override fun getUserToken(): String {
        return authCallbacks.getToken()
    }

}