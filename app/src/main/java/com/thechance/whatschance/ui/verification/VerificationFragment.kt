package com.thechance.whatschance.ui.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentVerificationBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    override val layoutIdFragment = R.layout.fragment_verification
    override val viewModel: VerificationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticate(viewModel.args.phone)
        collectLast(viewModel.verifyCodeEvent) {
            it.getContentIfNotHandled()?.let { checkVerificationCode(it) }
        }
    }

    private fun authenticate(phone: String) {
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setCallbacks(viewModel.getAuthCallbacks())
            .setActivity(requireActivity())
            .setTimeout(120L, TimeUnit.SECONDS)
            .setPhoneNumber(phone)
            .build()
        viewModel.sendSmsCode(options)
    }

    private fun checkVerificationCode(event: VerificationUIEvent) {
        if (event is VerificationUIEvent.VerifyCodeEvent) {
            findNavController().navigate(VerificationFragmentDirections.actionVerificationFragmentToHomeFragment())
        }
    }
}