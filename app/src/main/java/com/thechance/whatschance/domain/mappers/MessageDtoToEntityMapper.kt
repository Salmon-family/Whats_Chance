package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.data.response.MessageDto
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class MessageDtoToEntityMapper @Inject constructor() : Mapper<MessageDto, MessageEntity> {
    override fun map(input: MessageDto): MessageEntity {
        return MessageEntity(
            senderId = input.sender,
            textMessage = input.textMessage,
            id =0,
            isFromMe = false,
            time = input.time
        )
    }
}