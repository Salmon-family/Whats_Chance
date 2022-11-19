package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.UserEntity
import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class UserDtoToEntityMapper @Inject constructor() : Mapper<UserDto, UserEntity> {
    override fun map(input: UserDto): UserEntity {
        return UserEntity(
            id = input.userID ?: "",
            name = input.name ?: "",
            phoneNumber = input.phoneNumber ?: ""
        )
    }

}