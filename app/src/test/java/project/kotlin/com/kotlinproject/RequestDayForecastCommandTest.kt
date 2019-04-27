package project.kotlin.com.kotlinproject

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import project.kotlin.com.kotlinproject.domain.commands.RequestDayForecastCommand
import project.kotlin.com.kotlinproject.domain.datasource.ForecastProvider

class RequestDayForecastCommandTest {

    @Test
    fun `provider is called when command is executed`() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        runBlocking { command.execute() }

        verify(forecastProvider).requestForecast(2)
    }
}