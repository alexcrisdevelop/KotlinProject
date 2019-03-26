package project.kotlin.com.kotlinproject.data.server

import project.kotlin.com.kotlinproject.domain.datasource.ForecastDataSource
import project.kotlin.com.kotlinproject.data.db.ForecastDb
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.domain.mappers.ServerDataMapper

class ForecastServer(
        private val dataMapper: ServerDataMapper = ServerDataMapper(),
        private val forecastDb: ForecastDb = ForecastDb())
 : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
         val result = ForecastByZipCodeRequest(zipCode).execute()
         val converted = dataMapper.convertToDomain(zipCode, result)
         forecastDb.saveForecast(converted)
         return forecastDb.requestForecastByZipCode(zipCode, date)

    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()

}