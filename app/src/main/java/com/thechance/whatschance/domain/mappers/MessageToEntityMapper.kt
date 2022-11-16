package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class MessageToEntityMapper @Inject constructor() : Mapper<Message, MessageEntity> {
    override fun map(input: Message): MessageEntity {
        return MessageEntity(
            userId = input.sender,
            textMessage = input.textMessage,
            id = input.id,
            isFromMe = true
        )
    }
}