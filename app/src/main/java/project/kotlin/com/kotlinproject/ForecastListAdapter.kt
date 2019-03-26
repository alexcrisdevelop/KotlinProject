package project.kotlin.com.kotlinproject


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.*
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastDomain
import project.kotlin.com.kotlinproject.domain.commands.model.ForecastList
import project.kotlin.com.kotlinproject.extensions.ctx


class ForecastListAdapter(private val weekForecast: ForecastList,
                       //   private val itemClick: ForecastListAdapter.OnItemClickListener)
                          private val itemClick: (ForecastDomain) -> Unit ) //using lambdas
    : RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
//{

    /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(TextView(parent.context))
     } */

    /**
     * Inline version, the same as upward
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       // return ViewHolder(TextView(parent.context))

        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)

        return ViewHolder(view, itemClick)
    }


     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         /**
          * with is a useful function included in the standard Kotlin library. It receives an object and an extension function as parameters and makes the object
         execute the function. That way, all the code that we define inside the brackets acts as an extension function for the object provided in the first parameter, and we can use all its public functions and properties, as well
         as this. The with function is helpful to simplify code when we do several operations over the same object.
          * */

      /*   with(weekForecast[position]) {
              holder.textView.text = "$date - $description - $high/$low"
         } */

         holder.bindForecast(weekForecast[position])
     }

     override fun getItemCount(): Int = weekForecast.size

    // class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    class ViewHolder(
                //     private val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {    //with the interface
                     override val containerView: View,
                     private val itemClick: (ForecastDomain) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer  { //using lambdas


    /*    private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView = view.find<TextView>(R.id.description)
        private val maxTemperatureView = view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView = view.find<TextView>(R.id.minTemperature) */

        fun bindForecast(forecast: ForecastDomain) {
             with(forecast) {

              /*   dateView.text = date
                 descriptionView.text = description
                 maxTemperatureView.text = "$high"
                 minTemperatureView.text = "$low" */

                 //using extentions: get rid of find view by id
                 Picasso.with(itemView.ctx).load(iconUrl).into(iconPic)
                 dateText.text = date.toString()
                 descriptionText.text = description //with LayoutContainer
                 maxTemperatureText.text = "$high"
                 minTemperatureText.text = "$low"

                 itemView.setOnClickListener { itemClick(this) }
             }
        }


    }

 /*   interface OnItemClickListener {
         operator fun invoke(forecast: ForecastDomain)
    }*/
}