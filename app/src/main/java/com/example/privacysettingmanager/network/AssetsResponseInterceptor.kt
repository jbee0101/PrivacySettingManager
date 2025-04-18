package com.example.privacysettingmanager.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class AssetsResponseInterceptor(private val context: Context) : Interceptor {

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

