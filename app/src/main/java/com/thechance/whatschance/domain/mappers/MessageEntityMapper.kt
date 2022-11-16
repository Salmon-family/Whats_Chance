package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.domain.models.Message
import com.thechance.whatschance.ui.Mapper
import javax.inject.Inject

class MessageEntityMapper @Inject constructor() : Mapper<MessageEntity, Message> {
    override fun map(input: MessageEntity): Message {
        return Message(
            textMessage = input.textMessage,
            sender = input.senderId,
            time = input.time
        )
    }
}