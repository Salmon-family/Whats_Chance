package com.thechance.whatschance.ui.chat

import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class MessageUIStateMapper @Inject constructor() : Mapper<Message,MessageUIState>{
    override fun map(input: Message): MessageUIState {
        return MessageUIState(
            input.textMessage
        )
    }
}