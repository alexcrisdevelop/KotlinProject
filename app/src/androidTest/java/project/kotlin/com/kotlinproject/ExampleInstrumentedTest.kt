package project.kotlin.com.kotlinproject

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.EventLogTags
import android.widget.TextView
import org.hamcrest.CoreMatchers.`is`

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

import java.util.regex.Matcher
import java.util.regex.Pattern.matches


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("project.kotlin.com.kotlinproject", appContext.packageName)
    }

    /**
     * Test if go to details when clicking on an item of forecast list
     * */


    @get:Rule val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test fun itemClickNavigatesToDetail() {
        onView(withId(R.id.forecastList)).perform(
                RecyclerViewActions
                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        //error
      /*  onView(withId(R.id.weatherDescription))
                .check(matches(isAssignableFrom(TextView::class.java))) */

    }


    @Test fun modifyZipCodeChangesToolbarTitle() {
        openActionBarOverflowOrOptionsMenu(activityRule.activity)

        onView(withText(R.string.settings)).perform(click())

        onView(withId(R.id.cityCode)).perform(replaceText("94301"))

        pressBack()

        //error
    /*    onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(withToolbarTitle(`is`("Palo Alto (US)")))) */

    }


 /*   private fun withToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<Any> = object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
        override fun matchesSafely(toolbar: Toolbar): Boolean = textMatcher.matches(toolbar.title)
        override fun describeTo(description: EventLogTags.Description) {
            description.appendText("with toolbar title: ") textMatcher.describeTo(description)
        } } */

}
