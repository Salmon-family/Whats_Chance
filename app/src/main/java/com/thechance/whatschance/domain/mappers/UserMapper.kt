package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.UserEntity
import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<User, UserEntity> {
    override fun map(input: User): UserEntity {
        return UserEntity(
            id = input.userID ?: "",
            name = input.name ?: "",
            phoneNumber = input.phoneNumber ?: ""
        )
    }
}