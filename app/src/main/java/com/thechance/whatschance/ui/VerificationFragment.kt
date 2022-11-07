package com.thechance.whatschance.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentVerificationBinding
import com.thechance.whatschance.ui.base.BaseFragment


class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    override val layoutIdFragment  = R.layout.fragment_verification
    override val viewModel: ViewModel by viewModels()
}