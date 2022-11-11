package com.thechance.whatschance.domain.usecase.validate

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthOptions
import com.thechance.whatschance.data.repository.AuthenticationRepository
import javax.inject.Inject

class VerifyPhoneUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {

    operator fun invoke(userSmsCode: String, verificationID: String): Task<AuthResult> {
        return if (userSmsCode.isNotBlank() && userSmsCode.length == 6) {
            authenticationRepository.onVerifyOtp(userSmsCode, verificationID)
        } else {
            throw Throwable("Error")
        }

    }

    fun sendAuthenticationCode(options: PhoneAuthOptions) {
        authenticationRepository.sendVerificationCode(options)
    }
}