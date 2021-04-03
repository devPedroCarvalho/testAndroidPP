package app.devpedrocarvalho.testpp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.devpedrocarvalho.testpp.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun checkIfShowProgressBarWhenActivityCreate(){
        onView(withId(R.id.mainProgressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun checkIfShowRecyclerViewWhenActivityCreate(){
        onView(withId(R.id.contactsRecyclerView)).check(matches(isDisplayed()))
    }

}