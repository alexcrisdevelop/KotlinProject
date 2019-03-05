package project.kotlin.com.kotlinproject

import android.util.Log
import java.net.URL

/**
 * The constructor receives a URL. Then the run function reads the result and outputs the JSON in the Logcat.
 * */
class Request(private val url: String) {

    fun run() {

        // This method is not recommended for large responses,
         val forecastJsonStr = URL(url).readText()
         Log.d(javaClass.simpleName, forecastJsonStr)
    }

}