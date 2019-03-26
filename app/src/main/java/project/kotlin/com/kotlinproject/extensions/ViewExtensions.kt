package project.kotlin.com.kotlinproject.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

/** Create a textColor property extension +*/

var TextView.textColor: Int
     get() = currentTextColor
     set(v) = setTextColor(v)



/**
 * animations used in ToolbarManager
 * */
fun View.slideExit() {
     if (translationY == 0f) animate().translationY(-height.toFloat())
}


 fun View.slideEnter() {
     if (translationY < 0f) animate().translationY(0f)
 }
