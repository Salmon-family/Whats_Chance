package com.thechance.whatschance.data.repository

import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor() : AuthenticationRepository {

    override fun getUser(): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}