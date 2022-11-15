package com.thechance.whatschance.ui.home

import com.thechance.whatschance.ui.contact.UserUi

data class ContactUiState(
    val users: List<UserUi> = emptyList()
)

data class UserUi(
    val uId: String,
    val name: String,
    val phoneNumber: String,
)