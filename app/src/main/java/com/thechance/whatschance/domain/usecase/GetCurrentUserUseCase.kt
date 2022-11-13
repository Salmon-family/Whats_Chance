package com.thechance.whatschance.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.thechance.whatschance.data.repository.AuthenticationRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {

    operator fun invoke() : FirebaseUser?{
        return authenticationRepository.getUser()
    }
}