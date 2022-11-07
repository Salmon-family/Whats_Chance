package com.thechance.whatschance.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentHomeBinding
import com.thechance.whatschance.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, resources.getString(R.string.whats_chance))
    }
}