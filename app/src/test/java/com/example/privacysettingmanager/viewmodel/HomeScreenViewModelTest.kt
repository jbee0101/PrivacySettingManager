package com.example.privacysettingmanager.viewmodel

import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.repository.ServicesRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * This test class tests the functionality of HomeScreenViewModel.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var repository: ServicesRepository

    private val testDispatcher = StandardTestDispatcher()

    /**
     * Setup method to initialize the ViewModel and repository before each test.
     * It also sets the main dispatcher to the test dispatcher.
     */
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
    }

    /**
     *  Tear down method to reset the main dispatcher after each test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test method to verify if the uiState is updated correctly with services from the repository.
     */
    @Test
    fun `uiState is updated with services from repository`() = runTest {

        val fakeServices = listOf(
            Service(
                id = "google",
                name = "Google",
                icon = "icon_url",
                settings = emptyList()
            ),
            Service(
                id = "facebook",
                name = "Facebook",
                icon = "icon_url",
                settings = emptyList()
            )
        )

        whenever(repository.getServices()).thenReturn(fakeServices)

        viewModel = HomeScreenViewModel(repository)

        advanceUntilIdle()

        assertEquals(fakeServices, viewModel.uiState.value)
    }
}