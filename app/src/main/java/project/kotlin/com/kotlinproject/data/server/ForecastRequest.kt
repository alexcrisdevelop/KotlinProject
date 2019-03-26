package project.kotlin.com.kotlinproject.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastRequest(private val zipCode: String) {

    /**
     * In Kotlin, we cannot create static properties or functions, but we need to rely on objects.
     * If we need some static properties, constants or functions in a class, we can use a companion object. This object is shared among all instances of the class, the same as a static field or method would do in Java. Use the reserved
       word const for your compile-time constants.
     * */

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"

        private const val URL = "http://api.openweathermap.org/data/2.5/" + "forecast/daily?mode=json&units=metric&cnt=7"

        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="

    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}