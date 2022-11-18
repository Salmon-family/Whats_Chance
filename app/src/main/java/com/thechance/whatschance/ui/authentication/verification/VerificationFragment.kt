package com.thechance.whatschance.ui.authentication.verification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.R
import com.thechance.whatschance.data.PhoneAuthCallBack
import com.thechance.whatschance.databinding.FragmentVerificationBinding
import com.thechance.whatschance.domain.models.User
import com.thechance.whatschance.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    override val layoutIdFragment = R.layout.fragment_verification
    override val viewModel: VerificationViewModel by viewModels()

    private val USERS_COLLECTION = "users"

    private val authCallbacks = PhoneAuthCallBack()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticate(viewModel.args.phone)

        binding.fabVerify.setOnClickListener {
            getVerificationCode(
                viewModel.verifyCodeUIState.value.code,
                authCallbacks.getVerificationID()
            )
        }
    }

    private fun authenticate(phone: String) {
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setCallbacks(authCallbacks)
            .setActivity(requireActivity())
            .setTimeout(120L, TimeUnit.SECONDS)
            .setPhoneNumber(phone)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun getVerificationCode(userSmsCode: String, verificationID: String): Task<AuthResult> {
        return if (userSmsCode.isNotBlank() && userSmsCode.length == 6) {
            onVerifyOtp(userSmsCode, verificationID)
        } else {
            throw Throwable("Error")
        }

    }

    private fun onVerifyOtp(code: String, verificationID: String): Task<AuthResult> {
        val auth = FirebaseAuth.getInstance()
        val credential = PhoneAuthProvider.getCredential(verificationID, code)
        return auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(VerificationFragmentDirections.actionVerificationFragmentToWhatsChanceActivity())
                    task.result.user?.let {
                        addUser(User(it.uid, "Test${((0..10).random())}", it.phoneNumber!!))
                    }

                    requireActivity().finish()
                } else {
                    Log.i("ErrorOfListener", "Error")
                }
            }
    }

    private fun addUser(user: User) {
        val userRef =
            FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
                .document(user.userID)
        userRef.set(user)
    }


}