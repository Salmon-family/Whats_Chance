package com.thechance.whatschance.domain.usecases

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class CheckSmsCodeUseCase @Inject constructor() {

    operator fun invoke(verificationId: String, userSmsCode: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(verificationId, userSmsCode)
        return Firebase.auth.signInWithCredential(credential)
    }
}