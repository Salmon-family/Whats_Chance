package com.thechance.whatschance.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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
        setChatAdapter()
        setAutoCompleteAdapter()
    }

    private fun setChatAdapter() {
        val chatAdapter = ChatAdapter(emptyList(), viewModel)
        binding.chatRecycler.adapter = chatAdapter
    }

    private fun setAutoCompleteAdapter() {
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_sticker_text,
            R.id.text_sticker, resources.getStringArray(R.array.stickers)
        )
        binding.autoComplete.setAdapter(adapter)
    }

}