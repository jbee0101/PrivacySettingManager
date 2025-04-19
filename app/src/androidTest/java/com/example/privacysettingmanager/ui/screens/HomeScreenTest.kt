package com.example.privacysettingmanager.ui.screens

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.privacysettingmanager.data.model.Service
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.performClick
import com.example.privacysettingmanager.data.model.Setting
import com.example.privacysettingmanager.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

/**
 * Test class for testing HomeScreen functionality
 */
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Test to ensure HomeScreen displays services and the "Manage" button properly
     */
    @Test
    fun testHomeScreenDisplaysServicesAndManageButton() {
        val mockViewModel = mockk<HomeScreenViewModel>(relaxed = true)
        val services = listOf(
            Service(
                id = "1",
                name = "Service 1",
                icon = "https://example.com/icon1",
                settings = listOf(
                    Setting(id = "Setting 1", name = "Setting 1", enabled = true),
                    Setting(id = "Setting 2", name = "Setting 2", enabled = true)
                )
            ),
            Service(
                id = "2",
                name = "Service 2",
                icon = "https://example.com/icon2",
                settings = listOf(
                    Setting(id = "Setting 1", name = "Setting 1", enabled = true)
                )
            )
        )

        val mockStateFlow: StateFlow<List<Service>> = MutableStateFlow(services)

        every { mockViewModel.uiState } returns mockStateFlow

        composeTestRule.setContent {
            HomeScreen(viewModel = mockViewModel, onServiceClick = {})
        }
        Thread.sleep(1000)

        composeTestRule.onNodeWithText("Service 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Service 2").assertIsDisplayed()
        composeTestRule.onAllNodesWithText("Manage").assertCountEquals(2)

        Thread.sleep(1000)
    }

    /**
     * Test to verify that the Manage button click invokes the correct callback
     */
    @Test
    fun testManageButtonClick() {
        val mockViewModel = mockk<HomeScreenViewModel>(relaxed = true)
        val mockOnServiceClick: (Service) -> Unit = mockk<(Service) -> Unit>(relaxed = true)

        val services = listOf(
            Service(
                id = "1",
                name = "Service 1",
                icon = "https://example.com/icon1",
                settings = listOf(
                    Setting(id = "Setting 1", name = "Setting 1", enabled = true),
                    Setting(id = "Setting 2", name = "Setting 2", enabled = true)
                )
            )
        )

        val mockStateFlow: StateFlow<List<Service>> = MutableStateFlow(services)

        every { mockViewModel.uiState } returns mockStateFlow
        Thread.sleep(1000)

        composeTestRule.setContent {
            HomeScreen(viewModel = mockViewModel, onServiceClick = mockOnServiceClick)
        }

        composeTestRule.onNode(hasText("Manage")).performClick()

        verify { mockOnServiceClick.invoke(services[0]) }
        Thread.sleep(1000)
    }
}