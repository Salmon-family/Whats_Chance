package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.UserEntity
import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : Mapper<UserEntity, User> {
    override fun map(input: UserEntity): User {
        return User(
            uId = input.id,
            name = input.name,
            phoneNumber = input.phoneNumber
        )
    }
}