package com.example.privacysettingmanager.data.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit test for DataStoreManager using Hilt and AndroidJUnit4
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DataStoreManagerTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context
    private lateinit var dataStoreManager: DataStoreManager

    /**
     * Initializes Hilt and creates DataStoreManager instance before tests
     */
    @Before
    fun setup() {
        hiltRule.inject()
        context = ApplicationProvider.getApplicationContext()
        dataStoreManager = DataStoreManager(context)
    }

    /**
     * Test that verifies saving and retrieving a setting's value works correctly
     */
    @Test
    fun save_and_read_setting_state() = runTest {
        val serviceId = "google"
        val settingId = "location_history"
        val expectedValue = true

        dataStoreManager.saveSettingState(serviceId, settingId, expectedValue)

        val result = dataStoreManager.getSettingState(serviceId, settingId)

        assertEquals(expectedValue, result)
    }

    /**
     * Test that verifies getting a non-existent setting returns null
     */
    @Test
    fun get_unset_setting_returns_null() = runTest {
        val serviceId = "nonexistent_service"
        val settingId = "nonexistent_setting"

        val result = dataStoreManager.getSettingState(serviceId, settingId)

        assertNull(result)
    }
}