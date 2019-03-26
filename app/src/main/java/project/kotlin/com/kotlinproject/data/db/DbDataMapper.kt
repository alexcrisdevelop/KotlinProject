package project.kotlin.com.kotlinproject.data.db

import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList


class DbDataMapper {

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    internal fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        ForecastDomain(_id, date, description, high, low, iconUrl)
    }

    /** Conversion from the domain model ***/
    fun convertFromDomain(forecast: ForecastList) = with(forecast) {

        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    fun convertDayFromDomain(cityId: Long, forecast: ForecastDomain) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
}


