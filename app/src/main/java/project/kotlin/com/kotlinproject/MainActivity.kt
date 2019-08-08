package project.kotlin.com.kotlinproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*
import project.kotlin.com.kotlinproject.domain.commands.RequestForecastCommand
import project.kotlin.com.kotlinproject.extensions.DelegatesExt

class MainActivity : CoroutineScopeActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val zipCode: Long by DelegatesExt.longPreference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change
        //change in branch example_branch
        //new change

        initToolbar()


        //with extensions
        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)


    }


    override fun onResume() {
        super.onResume()
        loadForecast()
    }


    /**
     *  to return a Future with a result, you can use doAsyncResult.
     *
     */

  /*  private fun loadForecast() =   doAsync {
        //using the command and data classes:
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {

            //using lambdas
            forecastList.adapter = ForecastListAdapter(result) {


                //starting activity with Anko:
                startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)

            }

            toolbarTitle = "${result.city} (${result.country})"

        }

    } */

    //solution using coroutine builders:

    private fun loadForecast() = launch {
      //   val result = withContext(Dispatchers.IO) { RequestForecastCommand(zipCode).execute() }

        val result = RequestForecastCommand(zipCode).execute()
         val adapter = ForecastListAdapter(result) {

             //starting activity with Anko:
             startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
         }
         forecastList.adapter = adapter
                 toolbarTitle = "${result.city} (${result.country})"
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
