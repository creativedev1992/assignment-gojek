package com.gojek.assignment

import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.internal.matchers.TypeSafeMatcher

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
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.gojek.assignment", appContext.packageName)
    }
    fun textViewTextColorMatcher(matcherColor: String): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {

            override fun matchesSafely(textView: TextView): Boolean {
                return matcherColor == textView.text.toString()
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("with text color: $matcherColor")
            }
        }
    }
    class TextViewValueMatcher : org.junit.internal.matchers.TypeSafeMatcher<View>() {
        override fun describeTo(description: org.hamcrest.Description?) {
            description?.appendText("1")
        }
        override fun matchesSafely(item: View?): Boolean {
            var textView =  item as TextView
            var value = textView.text.toString()
            var matching=false
            if(value.toInt()==0)
                matching=false
            else
                matching=true
            return matching
        }
    }
}