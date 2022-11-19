package com.thechance.whatschance.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.data.response.UserDto
import com.thechance.whatschance.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FireStoreDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    fun addUser(user: User) {
        val userRef =
            fireStore.collection(USERS_COLLECTION).document(user.userID)
        userRef.set(user)
    }

    fun getUsers(uId: String): Flow<List<UserDto>> {
        return fireStore.collection(USERS_COLLECTION).whereNotEqualTo(U_ID_KEY, uId).snapshots()
            .map { it.toObjects(UserDto::class.java) }
    }

    fun getUser(users: List<String>): Flow<List<UserDto>> {
        return fireStore.collection(USERS_COLLECTION)
            .whereIn(U_ID_KEY, users).snapshots()
            .map { it.toObjects(UserDto::class.java) }
    }

    fun addMessage(uId: String, message: MessageDto): Task<DocumentReference> {
        val messagesRef = fireStore.collection(MESSAGES_COLLECTION).document(uId)
            .collection(MESSAGE_COLLECTION)
        return messagesRef.add(message)
    }

    fun getMessages(uId: String): Flow<List<MessageDto>> {
        return fireStore.collection(MESSAGES_COLLECTION).document(uId)
            .collection(MESSAGE_COLLECTION).snapshots().map { it.toObjects(MessageDto::class.java) }
    }

    fun deleteMessages() {
        fireStore.collection(MESSAGES_COLLECTION)
            .document(Firebase.auth.uid ?: "")
            .collection(MESSAGE_COLLECTION).get()
            .addOnSuccessListener { documentSnapshots ->
                for (documentSnapshot in documentSnapshots)
                    documentSnapshot.reference.delete()
            }
    }

    companion object {
        private const val U_ID_KEY = "userID"
        private const val USERS_COLLECTION = "users"
        private const val MESSAGES_COLLECTION = "theChanceMessages"
        private const val MESSAGE_COLLECTION = "messages"
    }
}