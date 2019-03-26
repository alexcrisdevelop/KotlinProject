package project.kotlin.com.kotlinproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import project.kotlin.com.kotlinproject.domain.commands.RequestForecastCommand

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     //   val forecastList = findViewById<RecyclerView>(R.id.forecast_list)

        //using anko:
      //  val forecastList : RecyclerView = find(R.id.forecastList)
    //    forecastList.layoutManager = LinearLayoutManager(this)

        //with extensions
        forecastList.layoutManager = LinearLayoutManager(this)


        //create a list for the adapter

        //listOf: create an immutable list
    /*    val items = listOf("Mon 6/23 - Sunny - 31/17",
         "Tue 6/24 - Foggy - 21/8",
         "Wed 6/25 - Cloudy - 22/17",
         "Thurs 6/26 - Rainy - 18/11",
         "Fri 6/27 - Foggy - 21/10",
         "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
         "Sun 6/29 - Sunny - 20/7"
        ) */

   /*     forecastList.adapter = ForecastListAdapter(items)

        niceToast("Hello")  //classname as tag by default
        niceToast("Hello", "MyTag")
        niceToast("Hello", "MyTag", Toast.LENGTH_SHORT) */

        //using anko to perform an http requet asyncronously

     /*   val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
                "APPID=15646a06818f61f7b8d7823ca833e1ce&zip=94043&mode=json&units=metric&\\cnt=7" */

        // doAsync returns a java Future, in case you want to work with futures. If you need it
        //to return a Future with a result, you can use doAsyncResult.
         doAsync {
          //   Request(url).run()
          //   uiThread { longToast("Request performed") }

             //using the command and data classes:
             val result = RequestForecastCommand(94043).execute()
             uiThread {
               /*   forecastList.adapter = ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener{
                       override fun invoke(forecast: ForecastDomain) {
                           toast(forecast.date)
                           }
                  }) */

                 //using lambdas
                 forecastList.adapter = ForecastListAdapter(result) {
                  //   forecast -> toast(forecast.description)

                     //starting activity with Anko:
                     startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)

                 }

                 title = "${result.city} (${result.country})"

                 // In lambdas with only one argument, we
                 //can make use of the it reference, which prevents us from defining the left part of the
                 //function specifically.

             //    val adapter = ForecastListAdapter(result) { toast(it.date) }

             }

         }
    }



    /*
    inline fun <reified T: Activity> Context.startActivity(
             vararg params: Pair<String, String>) {

         val intent = Intent(this, T::class.java)
         params.forEach { intent.putExtra(it.first, it.second) }
         startActivity(intent)
    }*/

    /**
     * Default values can be specified in parameters
     * */
    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
         Toast.makeText(this, message, length).show()
    }

    /**
     * You can use template expressions directly in your strings, which simplifies
    writing complex strings based on static and variable parts. In the previous
    example, I used "$className $message".
    As you can see, anytime you want to add an expression, just write the $
    symbol. If the expression is a bit more complex, you can add a couple of
    brackets: "Your name is ${user.name}".
     * */
    fun niceToast(message: String, tag: String = MainActivity::class.java.simpleName,
                  length: Int = Toast.LENGTH_SHORT) {

         Toast.makeText(this, "[$tag] $message", length).show()
    }


    /** Extension function **/
    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
