package com.example.privacysettingmanager.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * An OkHttp Interceptor that serves responses from local assets instead of making actual network calls.
 *
 * @param context The application context used to access the assets folder.
 */
class AssetsResponseInterceptor(private val context: Context) : Interceptor {

    /**
     * Intercepts outgoing HTTP requests. If the request URL ends with "services", it loads
     * a local JSON file "services.json" from the app's assets and returns it as the response.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        return if (url.endsWith("services")) {
            val json = context.assets.open("services.json")
                .bufferedReader()
                .use { it.readText() }

            Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(
                    json.toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("Content-Type", "application/json")
                .build()
        } else {
            chain.proceed(request)
        }
    }
}

