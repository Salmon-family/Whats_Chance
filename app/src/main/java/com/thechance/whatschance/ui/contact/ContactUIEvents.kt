package com.thechance.whatschance.ui.contact

sealed interface ContactUIEvents {
    data class SelectedUser(val user: UserUi) : ContactUIEvents
}