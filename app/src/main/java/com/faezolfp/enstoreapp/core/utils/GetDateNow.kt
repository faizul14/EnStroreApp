package com.faezolfp.enstoreapp.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object GetDateNow {
    fun getCurrentFormattedDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID")) // Menggunakan locale Indonesia
        return dateFormat.format(currentDate)
    }

    fun formatDate(timeStamp: Long): String{
        val date = Date(timeStamp)
        val format = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))
        return format.format(date)
    }
}