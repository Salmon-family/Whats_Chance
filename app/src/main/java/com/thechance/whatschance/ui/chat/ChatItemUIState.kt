package com.thechance.whatschance.ui.chat

sealed class ChatItemUIState(val priority: Int) {

    class DateText : ChatItemUIState(0)

    class TodayMessage(val data: List<MessageUi>) : ChatItemUIState(1)
}
