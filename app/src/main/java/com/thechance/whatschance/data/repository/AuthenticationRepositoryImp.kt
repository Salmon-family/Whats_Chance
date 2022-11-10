package com.thechance.whatschance.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.thechance.whatschance.ui.main.MainActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val context: MainActivity
) : AuthenticationRepository {

    var verificationOtp: String = ""
    var resentToken: PhoneAuthProvider.ForceResendingToken? = null

    private val authCallbacks = object :
        PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        }

        override fun onVerificationFailed(exception: FirebaseException) {
        }

        override fun onCodeSent(
            code: String, token: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(code, token)
            verificationOtp = code
            resentToken = token
        }

    }

    private val authBuilder: PhoneAuthOptions.Builder = PhoneAuthOptions.newBuilder(auth)
        .setCallbacks(authCallbacks)
        .setActivity(context)
        .setTimeout(120L, TimeUnit.SECONDS)

    private fun signInWithAuthCredential(credential: PhoneAuthCredential): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    override fun authenticate(phone: String) {
        val options = authBuilder
            .setPhoneNumber(phone)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    override fun onVerifyOtp(code: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationOtp, code)
        return signInWithAuthCredential(credential)
    }

    override fun getUserPhone(): String {
        return auth.currentUser?.phoneNumber.orEmpty()
    }

     override fun getUserToken(): String {
        return resentToken.toString()
    }

}