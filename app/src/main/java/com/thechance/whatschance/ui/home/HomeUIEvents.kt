package com.thechance.whatschance.ui.home

sealed interface HomeUIEvents {
    object AddContactEvent : HomeUIEvents
    class OnFriendSelected(val friend: FriendsUIState) : HomeUIEvents
}