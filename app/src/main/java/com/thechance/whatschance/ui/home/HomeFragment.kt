package com.thechance.whatschance.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentHomeBinding
import com.thechance.whatschance.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setTitle(true, resources.getString(R.string.whats_chance))

//        val color = viewModel.homeColorUiState.value.colorValueUi
//
//        activity?.window?.statusBarColor =
//            Color.parseColor(color)
//
//        Log.i("llllllllll", viewModel.homeColorUiState.value.colorValueUi)
    }
}