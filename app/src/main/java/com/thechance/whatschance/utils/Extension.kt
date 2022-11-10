package com.thechance.whatschance.utils

import android.app.Activity
import android.graphics.Color


fun Activity.changeStatusBarColor(color: String){
    this.window.statusBarColor = Color.parseColor(color)
}