package project.kotlin.com.kotlinproject.domain.datasource

import project.kotlin.com.kotlinproject.data.db.ForecastDb
import project.kotlin.com.kotlinproject.data.server.ForecastServer
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.extensions.firstResult

class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

     companion object {
         const val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
         val SOURCES by lazy { listOf(ForecastDb(), ForecastServer()) }
     }


    /* old way
    fun requestByZipCode(zipCode: Long, days: Int): ForecastList  = sources.firstResult {
        requestSource(it, days, zipCode)
    }

    fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
         val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
         return if (res != null && res.size >= days) res else null
    } */

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
         val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
         if (res != null && res.size >= days) res else null
    }

     fun requestForecast(id: Long): ForecastDomain = requestToSources {
         it.requestDayForecast(id)
     }


    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS

    /**
     * returns the requested forecast by id
     * **/
    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }


}