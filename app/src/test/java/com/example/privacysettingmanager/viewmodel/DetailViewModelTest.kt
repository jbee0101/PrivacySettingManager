package com.example.privacysettingmanager.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.privacysettingmanager.data.local.DataStoreManager
import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.model.Setting
import com.google.gson.Gson
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
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
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.net.URLEncoder

/**
 * This test class tests the functionality of DetailViewModel.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataStoreManager: DataStoreManager
    private lateinit var savedStateHandle: SavedStateHandle

    private val testDispatcher = StandardTestDispatcher()

    private val fakeService = Service(
        id = "google",
        name = "Google",
        icon = "icon_url",
        settings = listOf(
            Setting("ad_personalization", "Ad Personalization", true),
            Setting("location_history", "Location History", false)
        )
    )

    /**
     * Setup method to initialize objects and set up the test environment before each test.
     */
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        dataStoreManager = mock()
        val serviceJson = URLEncoder.encode(Gson().toJson(fakeService), "utf-8")
        savedStateHandle = SavedStateHandle(mapOf("serviceJson" to serviceJson))
    }

    /**
     * Tear down method to reset the main dispatcher after each test.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test method to verify that the service is loaded correctly and the saved toggle states are applied.
     */
    @Test
    fun `loads service and applies saved toggle states`() = runTest {
        whenever(dataStoreManager.getSettingState("google", "ad_personalization")).thenReturn(false)
        whenever(dataStoreManager.getSettingState("google", "location_history")).thenReturn(true)

        viewModel = DetailViewModel(savedStateHandle, dataStoreManager)

        advanceUntilIdle()

        val loadedService = viewModel.service.value

        assertNotNull(loadedService)
        assertEquals("google", loadedService?.id)
        assertFalse(
            loadedService?.settings?.first { it.id == "ad_personalization" }?.enabled ?: true
        )
        assertTrue(loadedService?.settings?.first { it.id == "location_history" }?.enabled ?: false)
    }

    /**
     * Test method to verify that toggling a setting updates the state and saves it to the DataStore.
     */
    @Test
    fun `toggleSetting updates state and saves to datastore`() = runTest {
        whenever(dataStoreManager.getSettingState(any(), any())).thenReturn(null)

        viewModel = DetailViewModel(savedStateHandle, dataStoreManager)
        advanceUntilIdle()

        viewModel.toggleSetting("ad_personalization")
        advanceUntilIdle()

        val updatedSetting =
            viewModel.service.value?.settings?.first { it.id == "ad_personalization" }
        assertEquals(false, updatedSetting?.enabled)

        verify(dataStoreManager).saveSettingState("google", "ad_personalization", false)
    }
}
