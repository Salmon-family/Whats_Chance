package com.thechance.whatschance.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import com.thechance.whatschance.data.response.MessageDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FireStoreDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    fun getUsers() : Flow<QuerySnapshot> {
        return fireStore.collection("devfalahUsers").snapshots()
    }

    fun addMessage(uId : String,message: MessageDto) : Boolean {
        val messagesRef = fireStore.collection("devfalahMessages").document(uId).collection("messages")
        return messagesRef.add(message).isSuccessful
    }

    fun getMessages(uId: String,senderId: String) : Flow<QuerySnapshot>{
        return fireStore.collection("devfalahMessages").document(uId).collection("messages").whereEqualTo("sender",senderId).snapshots()
    }
}