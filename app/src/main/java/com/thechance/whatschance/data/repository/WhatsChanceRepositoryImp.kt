package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.thechance.whatschance.data.response.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WhatsChanceRepositoryImp @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val firebaseStore: FirebaseFirestore,
) : WhatsChanceRepository {

    override fun getColorTheme(key: String): String {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                return@addOnCompleteListener
            }
        }
        return remoteConfig.getString(key)
    }

    override suspend fun sendSticker(message: Message) {
        firebaseStore.collection("Stickers")
            .add(message)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }

    override suspend fun getStickers(): Flow<List<Message>> {
        val result = firebaseStore.collection("Stickers")
            .snapshots()
        return result.map { it.toObjects(Message::class.java) }
    }
}