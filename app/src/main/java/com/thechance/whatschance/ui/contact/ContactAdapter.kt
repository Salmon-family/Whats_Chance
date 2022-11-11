package com.thechance.whatschance.ui.contact

import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter
import com.thechance.whatschance.ui.base.BaseInteractionListener

class ContactAdapter(items: List<UserUi>, listener: ContactInteractionListener) :
    BaseAdapter<UserUi>(items, listener) {
    override val layoutID = R.layout.item_contact
}

interface ContactInteractionListener : BaseInteractionListener {
    fun onItemSelected(user: UserUi)
}