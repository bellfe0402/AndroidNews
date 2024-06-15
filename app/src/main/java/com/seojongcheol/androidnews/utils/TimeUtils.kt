package com.seojongcheol.androidnews.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object TimeUtils {
    fun convertTime(utcTime: String): String{
        val utcDateTime = ZonedDateTime.parse(utcTime)
        val seoulDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("Asia/Seoul"))

        return seoulDateTime.format(DateTimeFormatter.ofPattern("yyyy년 M월 d일 h시 m분"))
    }
}