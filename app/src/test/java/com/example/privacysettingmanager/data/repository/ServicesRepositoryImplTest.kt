package com.example.privacysettingmanager.data.repository

import com.example.privacysettingmanager.data.model.Service
import com.example.privacysettingmanager.data.model.ServiceResponse
import com.example.privacysettingmanager.network.ServiceApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

/**
 * This class tests the implementation of ServicesRepository.
 */
class ServicesRepositoryImplTest {

    private lateinit var repository: ServicesRepository
    private lateinit var api: ServiceApi

    /**
     * Setup method to initialize the mock API and the repository.
     */
    @Before
    fun setup() {
        api = mock()
        repository = ServicesRepositoryImpl(api)
    }

    /**
     * Test method to verify that the getServices() method in the repository returns the expected list from the API.
     */
    @Test
    fun `getServices returns list from api`() = runTest {
        val expectedServices = listOf(
            Service(id = "google", name = "Google", icon = "icon1", settings = emptyList()),
            Service(id = "facebook", name = "Facebook", icon = "icon2", settings = emptyList())
        )
        val fakeResponse = ServiceResponse(services = expectedServices)

        whenever(api.getServices()).thenReturn(fakeResponse)

        val result = repository.getServices()

        assertEquals(expectedServices, result)
    }
}