package com.thechance.whatschance.usecase

import com.thechance.whatschance.data.repository.WhatsChanceRepository
import javax.inject.Inject

class GetColorThemeUseCase @Inject constructor(
    private val repository: WhatsChanceRepository,
) {
    operator fun invoke(): String {
        return repository.getColorTheme("color")
    }
}