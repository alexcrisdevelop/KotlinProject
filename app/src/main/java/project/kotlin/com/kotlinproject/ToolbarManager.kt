package project.kotlin.com.kotlinproject

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.toast
import project.kotlin.com.kotlinproject.extensions.ctx
import project.kotlin.com.kotlinproject.extensions.slideEnter
import project.kotlin.com.kotlinproject.extensions.slideExit


/**
 * Toolbar interface. Interfaces are stateless, so the property can be defined, but no value can be assigned. Classes that implement it require overriding the property.
 * */
interface ToolbarManager {
    val toolbar: Toolbar

    /**
     * stateless properties without the need of being overridden can be added.
     * */
    var toolbarTitle: String
     get() = toolbar.title.toString()
     set(value) {
         toolbar.title = value
     }


    /**
     * initializes the toolbar, by inflating a menu and setting a listener:
     * */
    fun initToolbar() {
         toolbar.inflateMenu(R.menu.menu)
         toolbar.setOnMenuItemClickListener {
             when (it.itemId) {
                 R.id.action_settings -> App.instance.toast("Settings")
             else -> App.instance.toast("Unknown option")
             }
             true
         }
    }


    /**
     * enables the navigation icon in the toolbar, sets an arrow icon and a listener that will be fired when the icon is pressed
     * */
    fun enableHomeAsUp(up: () -> Unit) {
         toolbar.navigationIcon = createUpDrawable()
         toolbar.setNavigationOnClickListener { up() }
    }


    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }


    /**
     * allows the toolbar to be attached to a scroll, and animates the toolbar depending on the direction of the scroll. The toolbar will be hidden while we are scrolling down and displayed again when scrolling up
     * */
    fun attachToScroll(recyclerView: RecyclerView) {
         recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
             override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                 if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
                 }
             })
    }

}