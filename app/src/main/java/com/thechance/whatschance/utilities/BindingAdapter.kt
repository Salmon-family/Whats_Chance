package com.thechance.whatschance.utilities

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter


@BindingAdapter("app:isVisible")
fun <T> isVisible(view: View, value:Boolean) {
    view.isVisible = value
}