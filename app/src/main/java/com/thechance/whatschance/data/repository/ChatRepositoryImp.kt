package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
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

    override suspend fun insertMessage(message: Message) {
        fireStore.collection("Messages")
            .add(message)
            .addOnSuccessListener { Log.d("state", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("state", "Error writing document", e) }
    }

    override suspend fun getMessage(): Message {
        var message = Message()
        fireStore.collection("Messages")
            .document("GTp1xWitHo5XHypv8viq")
            //.orderBy("textMessage", Query.Direction.DESCENDING)
            //.limit(1)
            //.whereEqualTo("textMessage","name")
            .get()
            .addOnSuccessListener { documentSnapshot ->
                message = documentSnapshot.toObject<Message>()!!//.last()
                Log.i("tag",message.textMessage)
            }
        return message
    }
}