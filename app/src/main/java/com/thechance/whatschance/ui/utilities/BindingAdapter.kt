package com.thechance.whatschance.ui.utilities

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.thechance.whatschance.R
import com.thechance.whatschance.ui.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
    view.scrollToPosition(0)

}

@BindingAdapter("app:sticker")
fun setSticker(view: ImageView, stickerName: String) {
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
    if (stickerName in stickers.keys) {
        stickers[stickerName]?.let { view.setBackgroundResource(it) }
        view.load(stickerName)
    }
}

@BindingAdapter(value = ["app:isEnabled", "app:brandColor"])
fun setSendButtonColor(view: ImageView, isEnabled: Boolean, brandColor: String) {
    if (isEnabled) {
        view.imageTintList = ColorStateList.valueOf(Color.parseColor(brandColor))
    } else {
        view.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.gray))
    }
}