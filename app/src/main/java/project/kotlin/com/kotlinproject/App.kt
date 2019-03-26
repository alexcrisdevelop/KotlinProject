package project.kotlin.com.kotlinproject

import android.app.Application

class App : Application() {

     companion object {

         /**
          * We know that our singleton is not going to be null, but we cannot use the constructor to assign the property. So we can make use of a lateinit delegate
          * */
        lateinit var instance: App
         private set

        // var instance: App by DelegatesExt.notNullSingleValue()
     }

     override fun onCreate() {
         super.onCreate()
         instance = this
     }
}