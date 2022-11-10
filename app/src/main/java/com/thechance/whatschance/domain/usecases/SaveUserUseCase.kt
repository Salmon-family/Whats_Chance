package com.thechance.whatschance.domain.usecases

import com.thechance.whatschance.data.repository.ChatRepository
import com.thechance.whatschance.domain.models.User
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(id: String, name: String, mobile: String){
        chatRepository.insertUser(
            User(
                id = id,
                name = name,
                mobileNumber = mobile
            )
        )
    }
}