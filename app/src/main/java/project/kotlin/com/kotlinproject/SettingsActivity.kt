package project.kotlin.com.kotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.toolbar.*
import project.kotlin.com.kotlinproject.extensions.DelegatesExt

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val ZIP_CODE = "zipCode"
        const val DEFAULT_ZIP = 94043L
    }

    private var zipCode: Long by DelegatesExt.longPreference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        cityCode.setText(zipCode.toString())

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
         android.R.id.home -> {
             onBackPressed()
             true
         }
         else -> false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = cityCode.text.toString().toLong()
    }
}
