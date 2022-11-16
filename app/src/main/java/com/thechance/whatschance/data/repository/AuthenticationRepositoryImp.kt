package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthenticationRepository {

    override fun sendVerificationCode(options: PhoneAuthOptions) {
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithAuthCredential(credential: PhoneAuthCredential): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    override fun onVerifyOtp(code: String, verificationID: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationID, code)
        return signInWithAuthCredential(credential)
    }


    override fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}