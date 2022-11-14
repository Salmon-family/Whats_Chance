package com.thechance.whatschance.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentLoginBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectLast(viewModel.loginEvent) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
        binding.pickerCountryCode.setOnCountryChangeListener {
            viewModel.onCountryCodeChange(binding.pickerCountryCode.selectedCountryCodeWithPlus)
        }
    }

    private fun onEvent(event: LoginUIEvent) {
        if (event is LoginUIEvent.LoginEvent) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToVerificationFragment(event.phoneNumber, event.name)
            )
        }
    }

}

