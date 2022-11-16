package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class MessageMapper @Inject constructor():Mapper<MessageDto,Message> {
    override fun map(input: MessageDto): Message {
        return Message(
            id = input.id,
            textMessage = input.textMessage,
            sender = input.sender,
            time = input.time
        )
    }
}