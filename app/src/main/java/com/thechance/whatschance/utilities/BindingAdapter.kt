package com.thechance.whatschance.utilities

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SimpleDateFormat")
@BindingAdapter("bind_epochTimeMsToDate")
fun TextView.bindEpochTimeMsToDate(epochTimeMs: Long) {
    if (epochTimeMs > 0) {
        val currentTimeMs = Date().time
        val numOfDays = TimeUnit.MILLISECONDS.toDays(currentTimeMs - epochTimeMs)

        val replacePattern = when {
            numOfDays >= 1.toLong() -> "Yy"
            else -> "YyMd"
        }
        val pat = SimpleDateFormat().toLocalizedPattern().replace("\\W?[$replacePattern]+\\W?".toRegex(), " ")
        val formatter = SimpleDateFormat(pat, Locale.getDefault())
        this.text = formatter.format(Date(epochTimeMs))
    }
}

//@BindingAdapter("bind_date", "bind_message_viewModel")
//fun View.bindShouldMessageShowTimeText(date: TextView, viewModel: ChatViewModel) {
//    val lastIndex = viewModel.chatUiState.value.chats.lastIndex - 1
//    val lastMessage = viewModel.chatUiState.value.chats[lastIndex].textMessage
//    val newMessage = viewModel.chatUiState.value.chats.last().date
//
//    if (lastMessage == newMessage) {
//        this.visibility = View.GONE
//    } else {
//        this.visibility = View.VISIBLE
//        date.text = String.format(viewModel.chatUiState.value.chats.last().date)
//    }
//}
//
//
