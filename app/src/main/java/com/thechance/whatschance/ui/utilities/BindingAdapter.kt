package com.thechance.whatschance.ui.utilities

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter

val stickers = mapOf(
    ":happy:" to R.drawable.happy,
    ":angry:" to R.drawable.evil,
    ":cry:" to R.drawable.cry,
    ":laugh:" to R.drawable.laugh,
    ":love:" to R.drawable.love,
    ":smile:" to R.drawable.smile,
    ":unhappy:" to R.drawable.sad,
    ":wink:" to R.drawable.wink,
    ":wow:" to R.drawable.wow,
    ":sad:" to R.drawable.sad,
)

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())

}

@BindingAdapter("app:sticker")
fun setSticker(view: TextView, stickerName: String) {
    if (stickerName in stickers.keys) {
        stickers[stickerName]?.let { view.setBackgroundResource(it) }
    }
}