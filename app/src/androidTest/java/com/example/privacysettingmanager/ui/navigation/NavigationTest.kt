package com.example.privacysettingmanager.ui.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.privacysettingmanager.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * NavigationTest to perform navigation from home screen to detail screen
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Injects dependencies before running tests
     */
    @Before
    fun setup() {
        hiltRule.inject()
    }

    /**
     * Tests that clicking the "Manage" button on the Home screen navigates to the Detail screen
     */
    @Test
    fun navigateFromHomeToDetail_showsDetailScreen() {

        composeTestRule.waitForIdle()

        Thread.sleep(1000)
        composeTestRule
            .onAllNodesWithText("Manage")[0]
            .performClick()

        composeTestRule.waitForIdle()
        Thread.sleep(1000)

        composeTestRule.onNodeWithText(
            text = "Settings",
            substring = true
        ).assertIsDisplayed()
        Thread.sleep(1000)
    }
}