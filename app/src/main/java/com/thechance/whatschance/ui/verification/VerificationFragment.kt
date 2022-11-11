package com.thechance.whatschance.ui.verification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentVerificationBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    override val layoutIdFragment = R.layout.fragment_verification
    override val viewModel: VerificationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLast(viewModel.verifyCodeEvent) {
            it.getContentIfNotHandled()?.let { checkVerificationCode(it) }
        }
    }

    private fun checkVerificationCode(event: VerificationUIEvent) {
        if (event is VerificationUIEvent.VerifyCodeEvent) {
            findNavController().navigate(VerificationFragmentDirections.actionVerificationFragmentToHomeFragment())
        }
    }
}