package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class MessageToDtoMapper @Inject constructor() : Mapper<Message, MessageDto> {
    override fun map(input: Message): MessageDto {
        return MessageDto(
            textMessage = input.textMessage,
            sender = input.sender,
            time = input.time
        )
    }
}