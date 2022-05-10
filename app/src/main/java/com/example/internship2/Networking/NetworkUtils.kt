package com.example.internship2.Networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    val BASE_URL = "http://stock.digitalregister.in:8080"
//    val BASE_URL = "http://stock.staging.digitalregister.in:8080"

    fun getRetrofitInstance(): Retrofit {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor = interceptor)
            .build()

        val retrofitInstance: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitInstance
    }

    fun getApiInterfaceInstance(): ApiInterface =
        getRetrofitInstance().create(ApiInterface::class.java)

}