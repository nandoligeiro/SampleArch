package br.com.nandoligeiro.samplearch.data.network

import android.util.Log
import br.com.nandoligeiro.samplearch.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.*


object GithubApiImpl{

    private fun initRetrofit(): Retrofit {

        val okClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("tic", System.currentTimeMillis().toString())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url()

                val url = originalHttpUrl.newBuilder()
                   // .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)

                val request = requestBuilder.build()

                val response = chain.proceed(request)

                val name = response.request().url().encodedPath()
                val tic = Date(java.lang.Long.parseLong(response.request().header("tic")!!))
                val time = System.currentTimeMillis() - tic.time
                Log.i("Track Timing Event", "$name - $time milliseconds")

                response
            }.build()

        val moshi = Moshi.Builder()
            .add(NullToEmptyStringAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    }

    val api : GithubApi = initRetrofit().create(GithubApi::class.java)
}