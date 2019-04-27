package project.kotlin.com.kotlinproject
import junit.framework.Assert
import junit.framework.Assert.assertNotNull

import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import project.kotlin.com.kotlinproject.data.server.Forecast
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.domain.datasource.ForecastDataSource
import project.kotlin.com.kotlinproject.domain.datasource.ForecastProvider
import org.mockito.Mockito.`when` as whenever

/**
 * Test ForecastProvider class
 * */

class ForecastProviderTest {

    @Test
    fun `data source returns a valid value`() {
        val ds = mock(ForecastDataSource::class.java)
         whenever(ds.requestDayForecast(0)).then {
             ForecastDomain(0, 0, "desc", 20, 0, "url")
         }

        val provider = ForecastProvider(listOf(ds))
        assertNotNull(provider.requestForecast(0))

    }


    @Test fun `empty database returns server value`() {
        val db = mock(ForecastDataSource::class.java)

        val server = mock(ForecastDataSource::class.java)

        whenever(server.requestForecastByZipCode(
                any(Long::class.java), any(Long::class.java)))
                .then {
                    ForecastList(0, "city", "country", listOf())
                }

         val provider = ForecastProvider(listOf(db, server))

         assertNotNull(provider.requestByZipCode(0, 0))
    }


}