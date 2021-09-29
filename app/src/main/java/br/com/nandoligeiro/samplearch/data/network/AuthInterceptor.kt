package br.com.nandoligeiro.samplearch.data.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Long
import java.util.*

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.BODY

        var req = chain.request()

        val requestBuilder = req.newBuilder()
            .header("tic", System.currentTimeMillis().toString())

        val request = requestBuilder.build()

        val url = request.url().newBuilder()
            .addQueryParameter("APPID", "your_key_here")
            .build()

        req = request.newBuilder().url(url).build()

        val response = chain.proceed(req)

        val name = response.request().url().encodedPath()
        val tic = Date(Long.parseLong(response.request().header("tic")!!))
        val time = System.currentTimeMillis() - tic.time
        Log.i("Track Timing Event", "$name - $time milliseconds")

        return response
    }
}