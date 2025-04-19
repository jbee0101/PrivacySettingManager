package com.example.privacysettingmanager.ui.screens

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.model.Setting
import com.example.privacysettingmanager.viewmodel.DetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.verify
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI test for the DetailScreen using a mocked DetailViewModel
 */
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var mockViewModel: DetailViewModel

    /**
     * Sets up a mocked ViewModel with predefined service and settings before each test
     */
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        val testService = Service(
            id = "1",
            name = "Mocked Service",
            icon = "https://example.com/icon",
            settings = listOf(
                Setting(id = "s1", name = "Setting 1", enabled = true),
                Setting(id = "s2", name = "Setting 2", enabled = false)
            )
        )

        mockViewModel = mockk(relaxed = true)
        every { mockViewModel.service } returns MutableStateFlow(testService)
    }

    /**
     * Verifies the UI displays settings correctly and toggles switch interaction invokes ViewModel method
     */
    @Test
    fun detailScreen_displaysSettings_andTogglesSwitch() {
        composeTestRule.setContent {
            DetailScreen(viewModel = mockViewModel)
        }

        Thread.sleep(1000)
        composeTestRule.onNodeWithText("Mocked Service Settings").assertIsDisplayed()
        composeTestRule.onNodeWithText("Setting 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Setting 2").assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("switch_s2")
            .assertExists()
            .performClick()

        verify { mockViewModel.toggleSetting("s2") }
        Thread.sleep(1000)
    }
}