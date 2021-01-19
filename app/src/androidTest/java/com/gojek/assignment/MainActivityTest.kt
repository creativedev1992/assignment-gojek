/*
 *
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.gojek.assignment



import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.PositionAssertions.isCompletelyAbove
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.gojek.assignment.ui.MainActivity
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.Description


@LargeTest
class MainActivityTest {

  @Rule
  @JvmField
  var rule = ActivityTestRule(MainActivity::class.java)
  @Test
  fun is_all_view_displayed() {
    onView(withId(R.id.tvrpm))
      .check(matches(isDisplayed()))
    onView(withId(R.id.imgwheel))
      .check(matches(isDisplayed()))
    onView(withId(R.id.btnupdate))
      .check(matches(isDisplayed()))
  }

  @Test
  fun is_rpm_text_above_wheel() {
    onView(withId(R.id.tvrpm)) .check(isCompletelyAbove(withId(R.id.imgwheel)))
  }
  @Test
  fun is_update_btn_disable_after_click()
  {
    onView(withId(R.id.btnupdate))
      .perform(click())
    onView(withId(R.id.btnupdate)).check(matches(not(isEnabled())))
  }
  @Test
  fun is_update_btn_alpha_set_after_click()
  {
    onView(withId(R.id.btnupdate))
      .perform(click())
    onView(withId(R.id.btnupdate)).check(matches(withAlpha(.4f)))
  }
  @Test
  fun is_rpm_not_empty_after_click()
  {
    onView(withId(R.id.btnupdate))
      .perform(click())
    onView(withId(R.id.tvrpm)).check(matches(not(withText(""))));
  }


}
