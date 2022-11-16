package com.thechance.whatschance.ui.home

import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

data class HomeUIState(
    val friends: List<FriendsUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

data class FriendsUIState(
    val name: String = "",
    val uId: String = ""
)

class FriendUIMapper @Inject constructor() : Mapper<User, FriendsUIState> {
    override fun map(input: User): FriendsUIState {
        return FriendsUIState(
            uId = input.userID,
            name = input.name,
//            phoneNumber = input.phoneNumber
        )
    }
}


