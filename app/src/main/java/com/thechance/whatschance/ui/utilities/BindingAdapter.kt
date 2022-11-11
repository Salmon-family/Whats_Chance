package com.thechance.whatschance.ui.utilities

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thechance.whatschance.ui.base.BaseAdapter

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())

}