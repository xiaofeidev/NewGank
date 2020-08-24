package com.github.xiaofeidev.comm.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.Result


/**
 * @author xiaofei_dev
 * @date 2020/8/15
 */
object RetrofitClient {
    //baseUrl 必须以'/'结尾！
    const val BASE_URL = "https://gank.io/api/v2/"

    private var logInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor.apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .retryOnConnectionFailure(true)
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
//        .addNetworkInterceptor(mTokenInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)

    suspend fun<T: Any, R: Any> apiCall(call: suspend () -> T, converter: (T) -> R): DataResult<R>{
        return try {
            val res = call()
            DataResult.Success(converter(res))
        } catch (e: Exception) {
            DataResult.Error(IOException(e.message, e))
        }
    }
}


