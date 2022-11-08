package com.thechance.whatschance.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentLoginBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLast(viewModel.loginEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }

    }

    private fun onEvent(event: LoginUIEvent) {
        if (event is LoginUIEvent.LoginEvent) {
            val firebaseAuth = Firebase.auth

            val options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(event.phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToVerificationFragment(
                                credential.smsCode.toString()
                            )
                        )
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {

                    }
                })
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

    }

}