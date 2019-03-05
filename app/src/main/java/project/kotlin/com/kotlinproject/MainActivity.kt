package project.kotlin.com.kotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //

        message.text = "Hello kotlin!"

        niceToast("Hello")  //classname as tag by default
        niceToast("Hello", "MyTag")
        niceToast("Hello", "MyTag", Toast.LENGTH_SHORT)
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
}
