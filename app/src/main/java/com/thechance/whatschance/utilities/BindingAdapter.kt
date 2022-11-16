package com.thechance.whatschance.utilities

import android.view.View
import android.widget.TextView
import com.google.firebase.installations.Utils
import java.util.*


fun setTimeTextVisibility(ts1: Long, ts2: Long, timeText: TextView) {
    if (ts2 == 0L) {
        timeText.visibility = View.VISIBLE
//        timeText.setText(Utils.formatDayTimeHtml(ts1))
    } else {
        val cal1 = Calendar.getInstance()
        val cal2 = Calendar.getInstance()
        cal1.timeInMillis = ts1
        cal2.timeInMillis = ts2
        val sameMonth = cal1[Calendar.YEAR] === cal2[Calendar.YEAR] &&
                cal1[Calendar.MONTH] === cal2[Calendar.MONTH]
        if (sameMonth) {
            timeText.visibility = View.GONE
            timeText.text = ""
        } else {
            timeText.visibility = View.VISIBLE
//            timeText.setText(Utils.formatDayTimeHtml(ts2))
        }
    }
}