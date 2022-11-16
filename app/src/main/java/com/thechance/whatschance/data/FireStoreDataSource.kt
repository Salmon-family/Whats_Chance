package com.thechance.whatschance.data

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
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

    fun addMessage(uId: String, message: MessageDto): Task<DocumentReference> {
        val messagesRef = fireStore.collection(MESSAGES_COLLECTION).document(uId)
            .collection(MESSAGE_COLLECTION)
        return messagesRef.add(message)
    }

    fun getMessages(uId: String, senderId: String): Flow<List<MessageDto>> {
        return fireStore.collection(MESSAGES_COLLECTION).document(uId)
            .collection(MESSAGE_COLLECTION).whereEqualTo(
                SENDER_ID_KEY, senderId
            ).snapshots().map { it.toObjects(MessageDto::class.java) }
    }

    fun deleteMessages(uId: String) {
        fireStore.collection(MESSAGES_COLLECTION).document(uId).delete()
            .addOnSuccessListener {
                Log.e("TESt","Success")
            }
            .addOnFailureListener {
                Log.e("TESt",it.message.toString())
            }
    }

    companion object {
        private const val U_ID_KEY = "userID"
        private const val SENDER_ID_KEY = "sender"
        private const val USERS_COLLECTION = "devfalahUsers"
        private const val MESSAGES_COLLECTION = "devfalahMessages"
        private const val MESSAGE_COLLECTION = "messages"
    }
}