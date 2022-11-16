package com.thechance.whatschance.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentHomeBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewChats.adapter = HomeAdapter(emptyList(), viewModel)
        collectLast(viewModel.homeEvents) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(event: HomeUIEvents) {
        when (event) {
            HomeUIEvents.AddContactEvent -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToContactFragment())
            }
        }
    }
}