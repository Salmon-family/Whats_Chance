package com.thechance.whatschance.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.data.response.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FireStoreDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    fun getUsers(uId: String) : Flow<List<UserDto>> {
        return fireStore.collection(USERS_COLLECTION).whereNotEqualTo(U_ID_KEY,uId).snapshots().map { it.toObjects(UserDto::class.java) }
    }

    fun addMessage(uId : String,message: MessageDto) : Boolean {
        val messagesRef = fireStore.collection(MESSAGES_COLLECTION).document(uId).collection(MESSAGE_COLLECTION)
        return messagesRef.add(message).isSuccessful
    }

    fun getMessages(uId: String,senderId: String) : Flow<List<MessageDto>>{
        return fireStore.collection(MESSAGES_COLLECTION).document(uId).collection(MESSAGE_COLLECTION).whereEqualTo(
            SENDER_ID_KEY,senderId).snapshots().map { it.toObjects(MessageDto::class.java) }
    }


    companion object{
        private const val U_ID_KEY = "uId"
        private const val SENDER_ID_KEY = "sender"
        private const val USERS_COLLECTION = "devfalahUsers"
        private const val MESSAGES_COLLECTION = "devfalahMessages"
        private const val MESSAGE_COLLECTION = "messages"
    }
}