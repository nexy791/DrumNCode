package com.test.common.time

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TimeExt {

    companion object {

        // get date in format dd MMMM yyyy from unix time based on locale
        fun Long.formatToHuman(): String {
            return try {
                val unixTime = this
                val calendar = Calendar.getInstance().apply {
                    timeInMillis = unixTime * 1000L
                }
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val date = "$year.$month.$day"
                val sdf = SimpleDateFormat("yyyy.MM.dd")
                val formattedDate = sdf.parse(date)
                val formattedDateInString = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(formattedDate)
                formattedDateInString
            } catch (e: Exception) {
                this.toString()
            }
        }

        inline fun <reified T : Any> T.formatToHuman() = when (this) {
            is Long -> formatToHuman()
            is String -> this.toLong().formatToHuman()
            else -> throw IllegalArgumentException("Can't format $this to human readable format")
        }

    }

}