package com.thechance.whatschance.utilities

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Converter @Inject constructor() {
    fun convertLongToFullDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("HH:mm MM/dd/yyyy")
        return format.format(date)
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("hh:mm aa")
        return format.format(date)
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("HH:mm MM/dd/yyyy")
        return df.parse(date).time
    }
}