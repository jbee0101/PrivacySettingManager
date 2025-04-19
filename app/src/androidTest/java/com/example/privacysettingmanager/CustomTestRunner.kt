package com.example.privacysettingmanager

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * CustomTestRunner extends AndroidJUnitRunner to modify the application used in the tests
 */
class CustomTestRunner : AndroidJUnitRunner() {

    /**
     * This method is called to initialize the application for the test. We override it to use
     * HiltTestApplication instead of the default application.
     */
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}