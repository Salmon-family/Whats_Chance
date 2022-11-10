package com.thechance.whatschance.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentLoginBinding
import com.thechance.whatschance.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}