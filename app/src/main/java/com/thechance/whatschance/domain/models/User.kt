package com.thechance.whatschance.domain.models

data class User(
    val id: String,
    val name: String,
    val mobileNumber: String,
    val image: String = "",
)
