package com.testanymind.presentation.extension

import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.jvm.Throws

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun Date.toReadableMonthYear(outputFormat: String) : String {
    val simpleDateFormat = SimpleDateFormat(outputFormat, Locale.US)
    return simpleDateFormat.format(this)
}

@Throws(ParseException::class)
fun String.toDate(inputFormat: String)  : Date {
    val sdf = SimpleDateFormat(inputFormat, Locale.US)
    return sdf.parse(this)
}