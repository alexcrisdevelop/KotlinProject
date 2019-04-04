package project.kotlin.com.kotlinproject.domain.commands

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import project.kotlin.com.kotlinproject.domain.commands.mappers.ForecastDataMapper
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.data.server.ForecastRequest
import project.kotlin.com.kotlinproject.domain.datasource.ForecastProvider

/* old versions
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
     override fun execute(): ForecastList {
         val forecastRequest = ForecastRequest(zipCode)

         return ForecastDataMapper().convertFromDataModel(
                 forecastRequest.execute())
        }
} */

class RequestForecastCommand(private val zipCode: Long, private val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

     companion object {
         const val DAYS = 7
         }

 /*    override fun execute(): ForecastList =
             forecastProvider.requestByZipCode(zipCode, DAYS) */

    override suspend fun execute() = withContext(Dispatchers.IO) {
         forecastProvider.requestByZipCode(zipCode, DAYS)
    }
}