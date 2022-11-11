package com.thechance.whatschance.data.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.data.PhoneAuthCallBack
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.main.MainActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val context: MainActivity,
    private val authCallbacks: PhoneAuthCallBack,
    private val fireStore: FirebaseFirestore
) : AuthenticationRepository {

    private fun signInWithAuthCredential(credential: PhoneAuthCredential): Task<AuthResult> {
        return auth.signInWithCredential(credential)
    }

    override fun authenticate(phone: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setCallbacks(authCallbacks)
            .setActivity(context)
            .setTimeout(120L, TimeUnit.SECONDS)
            .setPhoneNumber(phone)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    override fun onVerifyOtp(code: String): Task<AuthResult> {
        val credential = PhoneAuthProvider.getCredential(authCallbacks.getVerificationID(), code)
        return signInWithAuthCredential(credential)
    }

    override fun getUserPhone(): String {
        return auth.currentUser?.phoneNumber.orEmpty()
    }

    override fun getUserToken(): String {
        return authCallbacks.getToken()
    }


    override suspend fun insertUser() {
        Firebase.auth.currentUser?.let {
            fireStore.collection("TUsers")
                .document(it.uid)
                .set(User(it.uid, "Nada", it.phoneNumber!!))
                .addOnSuccessListener { Log.d("state", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("state", "Error writing document", e) }
        }
    }

    override suspend fun insertMessage(message: Message) {
        fireStore.collection("TMessages")
            .document(message.to)
            .set(message)
            .addOnSuccessListener { Log.d("state", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("state", "Error writing document", e) }
    }

    override suspend fun getMessage(): Message {
        var message = Message()
        Firebase.auth.currentUser?.let {
            fireStore.collection("TMessages")
                .document(it.phoneNumber!!)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    message = documentSnapshot.toObject<Message>()!!
                    Log.i("tag", message.content)
                }
        }
        return message
    }

}