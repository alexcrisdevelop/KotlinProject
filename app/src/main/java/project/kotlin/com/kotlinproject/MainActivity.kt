package project.kotlin.com.kotlinproject

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


     //   val forecastList = findViewById<RecyclerView>(R.id.forecast_list)
        //using anko:
        val forecastList : RecyclerView = find(R.id.forecast_list)

        forecastList.layoutManager = LinearLayoutManager(this)


        //create a list for the adapter

        //listOf: create an immutable list
        val items = listOf("Mon 6/23 - Sunny - 31/17",
         "Tue 6/24 - Foggy - 21/8",
         "Wed 6/25 - Cloudy - 22/17",
         "Thurs 6/26 - Rainy - 18/11",
         "Fri 6/27 - Foggy - 21/10",
         "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
         "Sun 6/29 - Sunny - 20/7"
        )

        forecastList.adapter = ForecastListAdapter(items)

      /*  niceToast("Hello")  //classname as tag by default
        niceToast("Hello", "MyTag")
        niceToast("Hello", "MyTag", Toast.LENGTH_SHORT) */
    }

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
