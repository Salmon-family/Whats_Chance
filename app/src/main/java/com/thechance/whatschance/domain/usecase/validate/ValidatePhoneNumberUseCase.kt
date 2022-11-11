package com.thechance.whatschance.domain.usecase.validate

import javax.inject.Inject

class ValidatePhoneNumberUseCase @Inject constructor() {

    operator fun invoke(phone: String): Boolean {
        return if (phone.isNotBlank()) {
            phone.length >= 10
        } else {
            throw Throwable("Error")
        }
    }
}