package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.data.FireStoreDataSource
import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserRepository {

    suspend fun getUsers(): Flow<List<UserDto>>

    suspend fun insertUser(name: String)

    suspend fun insertMessage(userID: String, message: Message)

    suspend fun getMessage(): Task<DocumentSnapshot>

    suspend fun insertFirstMessage(userID: String, message: Message)

    suspend fun getMyAccount(): String

    suspend fun getDocument(userID: String): Task<DocumentSnapshot>
}

class UserRepositoryImp @Inject constructor(
    private val fireStoreDataSource: FireStoreDataSource,
    private val fireStore: FirebaseFirestore
) : UserRepository {

    override suspend fun getUsers(): Flow<List<UserDto>> {
        return fireStoreDataSource.getUsers().map { it.toObjects(UserDto::class.java) }
    }

    override suspend fun getMessage(): Task<DocumentSnapshot> {
        return fireStoreDataSource.getMessagesDoucment(Firebase.auth.uid!!).get()
    }

    override suspend fun insertUser(name: String) {
        Firebase.auth.currentUser?.let {
            fireStore.collection("TUsers")
                .document(it.uid)
                .set(User(it.uid, name, it.phoneNumber!!))
        }
    }

    override suspend fun insertMessage(userID: String, message: Message) {
        val messages = fireStore.collection("TMessages")
            .document(userID)
        messages.update("content", FieldValue.arrayUnion(message.content.first()))
    }

    override suspend fun getDocument(userID: String): Task<DocumentSnapshot> {
        return fireStore.collection("TMessages")
            .document(userID).get()
    }

    override suspend fun insertFirstMessage(userID: String, message: Message) {
        fireStore.collection("TMessages")
            .document(userID)
            .set(message)
            .addOnFailureListener {
                Log.e("TESTTEST", it.message.toString())
            }
    }

    override suspend fun getMyAccount(): String {
        return Firebase.auth.currentUser?.uid ?: ""
    }
}