package com.ningsih.aplikasi_desa.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {
    const val  BASEURL ="http://192.168.1.6/aplikasi_desa2/public/"

    private fun setOkHttp(): OkHttpClient{
        val logging = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        ).setLevel(HttpLoggingInterceptor.Level.BASIC)

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    private fun setRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(setOkHttp())
            .baseUrl(BASEURL+ "api/")
            .build()
    }

    fun getService(): ApiService{
        return setRetrofit().create(ApiService::class.java)
    }
}