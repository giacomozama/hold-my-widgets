package me.zama.holdmywidgets

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HoldMyWidgetsTest {

    @get:Rule(order = 1)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 2)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
    }

    @Test
    fun add_a_widget() {
        with (composeTestRule) {
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_clock)).performClick()
            onNodeWithText("#1 - " + composeTestRule.activity.getString(R.string.title_clock)).assertIsDisplayed()
        }
    }

    @Test
    fun add_then_remove_a_widget() {
        with (composeTestRule) {
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_clock)).performClick()
            onNodeWithContentDescription(activity.getString(R.string.remove_this_widget)).performClick()
            onNodeWithText("#1 - " + activity.getString(R.string.title_clock)).assertDoesNotExist()
        }
    }

    @Test
    fun add_four_widgets_then_remove_two() {
        with (composeTestRule) {
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_compass)).performClick()
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_coords)).performClick()
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_ip)).performClick()
            onNodeWithText(activity.getString(R.string.add_widget)).performClick()
            onNodeWithText(activity.getString(R.string.title_clock)).performClick()
            val removeButtons = onAllNodesWithContentDescription(activity.getString(R.string.remove_this_widget))
            removeButtons[0].performClick()
            removeButtons[0].performClick()
            onNodeWithText("#1 - " + activity.getString(R.string.title_ip)).assertIsDisplayed()
            onNodeWithText("#2 - " + activity.getString(R.string.title_clock)).assertIsDisplayed()
        }
    }
}