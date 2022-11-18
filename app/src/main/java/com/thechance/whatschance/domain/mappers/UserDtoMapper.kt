package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class UserDtoMapper @Inject constructor() : Mapper<UserDto, User> {
    override fun map(input: UserDto): User {
        return User(
            userID = input.userID?:"",
            name = input.name ?: "",
            phoneNumber = input.phoneNumber ?: ""
        )
    }
}