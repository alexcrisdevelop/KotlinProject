package project.kotlin.com.kotlinproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Scope for asynchronous taks
 * */
abstract class CoroutineScopeActivity : AppCompatActivity(), CoroutineScope {

     override val coroutineContext: kotlin.coroutines.CoroutineContext
     get() = Dispatchers.Main + job

     lateinit var job: Job

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         job = Job()
     }

     override fun onDestroy() {
         job.cancel()
         super.onDestroy()
     }
}