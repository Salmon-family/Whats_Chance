package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.models.User
import javax.inject.Inject

class ChatRepositoryImp @Inject constructor(
    private val fireStore: FirebaseFirestore
) : ChatRepository {
    override suspend fun insertUser(user: User) {
        fireStore.collection("Users")
            .document(user.name)
            .set(user)
            .addOnSuccessListener { Log.d("state", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("state", "Error writing document", e) }
    }

    override suspend fun sendMessage(message: Message) {
        val messageRef = fireStore.collection("Messages")
            .document("${message.chatUsers.first()}_${message.chatUsers.last()}")
        fireStore.runBatch { batch ->
            batch.set(messageRef, message, SetOptions.merge())
            batch.update(messageRef,"textMessage", FieldValue.arrayUnion(message.textMessage))
        }
    }

    override suspend fun getMessage(user1: String, user2: String) {
        fireStore.collection("Messages")
            .document("${user1}_${user2}")
            .addSnapshotListener { messageSnapshot, e ->
                when{
                    e != null -> Log.e("ERROR", e.message.toString())
                    messageSnapshot != null && messageSnapshot.exists() -> {
                        Log.i("msg", "${messageSnapshot.data?.get("textMessage")}")
                    }
                }
            }
    }
}