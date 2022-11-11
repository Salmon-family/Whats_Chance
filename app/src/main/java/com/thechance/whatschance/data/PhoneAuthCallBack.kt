package com.thechance.whatschance.data

import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.thechance.whatschance.ui.login.LoginUIEvent
import javax.inject.Inject


class PhoneAuthCallBack @Inject constructor() : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    private var verificationOtp: String = ""
    private var resentToken: PhoneAuthProvider.ForceResendingToken? = null

    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
    }

    override fun onVerificationFailed(p0: FirebaseException) {
        Log.e("TEST",p0.message.toString())
    }

    override fun onCodeSent(
        code: String, token: PhoneAuthProvider.ForceResendingToken
    ) {
        super.onCodeSent(code, token)
        verificationOtp = code
        resentToken = token
    }

    fun getVerificationID(): String {
        return verificationOtp
    }

    fun getToken(): String {
        return resentToken.toString()
    }
}