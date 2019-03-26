package project.kotlin.com.kotlinproject.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import project.kotlin.com.kotlinproject.domain.datasource.ForecastDataSource
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.extensions.*

/**
 * request data already saved in the database, and to save fresh data.
 * */

class ForecastDb(
        private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    /** requests a forecast based on a zip code and a date */
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " + "AND ${DayForecastTable.DATE} >= ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) } //extension function created

        val city = select(CityForecastTable.NAME)
                 .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                 .parseOpt { CityForecast(HashMap(it), dailyForecast) } //parseOpt can return a null object if the city doesn't exist

        // if the returned city is not null, we convert it to a domain object and return it,
        if (city != null) dataMapper.convertToDomain(city) else null

    }


    /**
     * clears the data from the database so that we save fresh
    data, converts the domain forecast model to database model, and inserts each daily
    forecast and the city forecast
     * */
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        //clear previous data:

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        // convert the data, and use the result to execute the insert queries. The ‘*’ used before the result of
        //toVarargArray indicates that the array is decomposed to a vararg parameter. This is
        //done automatically in Java, but we need to make it explicit in Kotlin.

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())    //use extension function for collections

            dailyForecast.forEach {
                insert(project.kotlin.com.kotlinproject.data.db.DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }


    override fun requestDayForecast(id: Long): ForecastDomain? = forecastDbHelper.use {
         val forecast = select(DayForecastTable.NAME).byId(id).
             parseOpt { DayForecast(HashMap(it)) }

         if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }
}