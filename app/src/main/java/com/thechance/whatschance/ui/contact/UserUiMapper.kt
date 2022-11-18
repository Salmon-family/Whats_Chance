package com.thechance.whatschance.ui.contact

import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class UserUiMapper @Inject constructor() : Mapper<User, UserUi> {
    override fun map(input: User): UserUi {
        return UserUi(
            uId = input.userID,
            name = input.name,
            phoneNumber = input.phoneNumber,
        )
    }
}

class UserMapper @Inject constructor() : Mapper<UserUi, User> {
    override fun map(input: UserUi): User {
        return User(
            userID = input.uId,
            name = input.name,
            phoneNumber = input.phoneNumber,
        )
    }
}