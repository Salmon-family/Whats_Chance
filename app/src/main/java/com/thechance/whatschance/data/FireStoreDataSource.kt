package com.thechance.whatschance.data

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FireStoreDataSource @Inject constructor(
    private val fireStore: FirebaseFirestore
) {
    fun getUsers(): Flow<QuerySnapshot> {
        return fireStore.collection("TUsers").snapshots()
    }

    fun getMessages(uid: String): Flow<QuerySnapshot> {
        return fireStore.collectionGroup("TMessages")
            .snapshots()
    }

    fun getMessagesDoucment(uid: String): DocumentReference {
        return fireStore.collection("TMessages")
            .document(uid)
    }
}