package project.kotlin.com.kotlinproject.domain.datasource

import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList

interface ForecastDataSource {
     fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

     fun requestDayForecast(id: Long): ForecastDomain?
}