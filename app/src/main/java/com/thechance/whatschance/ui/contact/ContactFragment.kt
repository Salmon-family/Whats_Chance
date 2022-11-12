package com.thechance.whatschance.ui.contact

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentContactBinding
import com.thechance.whatschance.ui.base.BaseFragment
import com.thechance.whatschance.utilities.collectLast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_contact
    override val viewModel: ContactViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerContact.adapter = ContactAdapter(emptyList(), viewModel)
        collectLast(viewModel.contactEvents) {
            it.getContentIfNotHandled()?.let { onEvent(it) }
        }
    }

    private fun onEvent(events: ContactUIEvents) {
        when (events) {
            is ContactUIEvents.SelectedUser -> {
                findNavController().navigate(
                    ContactFragmentDirections.actionContactFragmentToChatFragment(
                        events.user.name,
                        events.user.uId
                    )
                )
            }
        }
    }
}