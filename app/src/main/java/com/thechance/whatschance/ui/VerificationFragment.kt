package com.thechance.whatschance.ui


import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentVerificationBinding
import com.thechance.whatschance.ui.base.BaseFragment


class VerificationFragment : BaseFragment<FragmentVerificationBinding>() {
    override val layoutIdFragment  = R.layout.fragment_verification
    override val viewModel: ViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showSoftKeyboard(binding.otpEditText)
    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm: InputMethodManager = requireActivity(). getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}