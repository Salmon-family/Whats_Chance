package com.thechance.whatschance.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentChatBinding
import com.thechance.whatschance.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override val layoutIdFragment = R.layout.fragment_chat
    override val viewModel: ChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, viewModel.args.userName)
        setAdapter()
    }

    private fun setAdapter() {
        val chatAdapter = ChatAdapter(emptyList(), viewModel)
        binding.chatRecycler.adapter = chatAdapter
    }
}