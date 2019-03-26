package project.kotlin.com.kotlinproject.domain.commands.model

data class ForecastList(val id: Long,val city: String, val country: String, val dailyForecast: List<ForecastDomain>) {
    val size: Int
        get() = dailyForecast.size

     operator fun get(position: Int): ForecastDomain = dailyForecast[position]
}

data class ForecastDomain(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)
