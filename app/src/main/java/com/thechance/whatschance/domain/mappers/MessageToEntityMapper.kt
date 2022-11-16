package com.thechance.whatschance.domain.mappers

import com.thechance.whatschance.data.local.database.entity.MessageEntity
import com.thechance.whatschance.domain.models.Message
import javax.inject.Inject

class MessageToEntityMapper @Inject constructor() {
    fun map(input: Message, sendToId: String): MessageEntity {
        return MessageEntity(
            senderId = sendToId,
            textMessage = input.textMessage,
            id = input.id,
            isFromMe = true,
            time = input.time
        )
    }
}