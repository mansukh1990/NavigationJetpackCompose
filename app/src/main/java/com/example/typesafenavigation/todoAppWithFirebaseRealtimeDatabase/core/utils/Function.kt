package com.example.typesafenavigation.todoAppWithFirebaseRealtimeDatabase.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

fun formatToReadableToDate(date: Long): String {

    val sdf = SimpleDateFormat("dd MMM, h:mm a, yyyy", java.util.Locale.ENGLISH)
    sdf.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
    return sdf.format(Date(date))
}