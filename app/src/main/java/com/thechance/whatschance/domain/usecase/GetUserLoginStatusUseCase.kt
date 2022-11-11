package com.thechance.whatschance.domain.usecase

import com.thechance.whatschance.data.repository.AuthenticationRepository
import javax.inject.Inject

class GetUserLoginStatusUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    operator fun invoke(): Boolean {
        return repository.getUser() != null
    }
}