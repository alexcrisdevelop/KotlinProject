package project.kotlin.com.kotlinproject.domain.commands.mappers

import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.data.server.Forecast
import project.kotlin.com.kotlinproject.data.server.ForecastResult
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/** Map  data to the domain model */

class ForecastDataMapper {

     fun convertFromDataModel(forecast: ForecastResult): ForecastList = ForecastList(forecast.city.id, forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))

     private fun convertForecastListToDomain(list: List<Forecast>) : List<ForecastDomain> {
         return list.mapIndexed { i, forecast ->
             val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
                 convertForecastItemToDomain(forecast.copy(dt = dt))
             }
         }

     private fun convertForecastItemToDomain(forecast: Forecast): ForecastDomain {
        // return ForecastDomain(forecast.dt, forecast.weather[0].description, forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
         return ForecastDomain(-1,forecast.dt, forecast.weather[0].description, forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))

     }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"

     private fun convertDate(date: Long): String {
         val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
         return df.format(date)
     }
}