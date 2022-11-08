package com.thechance.whatschance.domain.usecases

import javax.inject.Inject

class CheckSmsCodeUseCase @Inject constructor() {

    operator fun invoke(userCode: String, serviceCode: String): Boolean {
        return userCode == serviceCode
    }
}