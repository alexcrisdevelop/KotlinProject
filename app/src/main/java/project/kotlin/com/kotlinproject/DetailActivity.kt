package project.kotlin.com.kotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import project.kotlin.com.kotlinproject.data.server.Forecast
import project.kotlin.com.kotlinproject.domain.commands.RequestDayForecastCommand
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.extensions.color
import project.kotlin.com.kotlinproject.extensions.textColor
import project.kotlin.com.kotlinproject.extensions.toDateString
import java.text.DateFormat

class DetailActivity : CoroutineScopeActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        const val ID = "DetailActivity:id"
        const val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        toolbarTitle = intent.getStringExtra(CITY_NAME)

        enableHomeAsUp { onBackPressed() }


     /*   doAsync {
             val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
             uiThread { bindForecast(result) }
        } */

        launch {
             val id = intent.getLongExtra(ID, -1)
             val result = RequestDayForecastCommand(id).execute()
             bindForecast(result)
        }


    }


    private fun bindForecast(forecast: ForecastDomain) = with(forecast) {
         Picasso.with(this@DetailActivity).load(iconUrl).into(icon)
         supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
         weatherDescription.text = description
         bindWeather(high to maxTemperature, low to minTemperature)
    }


    /**
     * assigns a text and a text color to the TextViews based on the temperature.
     * */
    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
         it.second.text = "${it.first}"
         it.second.textColor = color(when (it.first) {  //defined in ExtentionUtils and ContextExtensions
             in -50..0 -> android.R.color.holo_red_dark
             in 0..15 -> android.R.color.holo_orange_dark
             else -> android.R.color.holo_green_dark
             })
         }
}
