package gps.x.an3000qiapplication

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import gps.x.an3000qiapplication.view.MenuActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuActivityTest {
    val activityRule = ActivityTestRule(MenuActivity::class.java)

    @Test
    fun openAllCharacters(){
        activityRule.launchActivity(Intent())
        onView(withId(R.id.all_characters_button)).perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.lv)).check(ViewAssertions.matches(isDisplayed()))
    }

}