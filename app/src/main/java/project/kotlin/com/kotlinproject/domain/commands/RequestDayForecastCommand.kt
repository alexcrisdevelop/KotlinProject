package project.kotlin.com.kotlinproject.domain.commands

import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.datasource.ForecastProvider

class RequestDayForecastCommand(
         val id: Long,
         private val forecastProvider: ForecastProvider = ForecastProvider()) :
         Command<ForecastDomain> {

     override fun execute() = forecastProvider.requestForecast(id)
}