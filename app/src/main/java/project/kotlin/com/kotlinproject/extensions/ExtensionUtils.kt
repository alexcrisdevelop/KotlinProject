package project.kotlin.com.kotlinproject.extensions

import java.text.DateFormat
import java.util.*


/**
 * extension function able to convert a Long object into a visual date string.
 * */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
     val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
     return df.format(this)
}