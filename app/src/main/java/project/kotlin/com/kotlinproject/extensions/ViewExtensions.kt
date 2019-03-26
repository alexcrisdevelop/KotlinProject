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
