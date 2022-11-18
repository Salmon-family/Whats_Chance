package com.thechance.whatschance.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.thechance.whatschance.R
import com.thechance.whatschance.databinding.FragmentChatBinding
import com.thechance.whatschance.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>() {

    override val layoutIdFragment = R.layout.fragment_chat
    override val viewModel: ChatViewModel by viewModels()
    private val chatAdapter by lazy { ChatAdapter(emptyList(), viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(true, viewModel.args.userName)
        setAdapter()
    }

    private fun setAdapter() {
        binding.chatRecycler.adapter = chatAdapter
        lifecycleScope.launch {
            viewModel.chatUiState.collectLatest {
                chatAdapter.setItems(viewModel.chatUiState.value.chats)
                binding.chatRecycler.scrollToPosition(0)
            }
        }
    }
}