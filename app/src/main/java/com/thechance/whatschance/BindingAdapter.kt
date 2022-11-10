package com.thechance.whatschance

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("app:showColorOffLine")
fun changeColorView(view: View, color: String) {
    view.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color))

//    view.setTextColor(Color.parseColor(color))
}