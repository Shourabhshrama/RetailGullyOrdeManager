package com.maverick.e_nirikshan.data.web


import android.util.Log

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


const val API_BASE_URL = "http://maverickconsole.online/order_manager/Api/" // live

const val API_BASE_URL_TEST = "http://demo.maverickconsole.net/enirikshanpanna/app/"  // test


class RetrofitClient {


    internal object Factory {


        fun create(): AppWebService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            Log.d("Tagged", "Centre Tagged")

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .writeTimeout(5, TimeUnit.MINUTES)
                .build()


//            client.setConnectTimeout(30, TimeUnit.SECONDS); // connect timeout
//            client.setReadTimeout(30, TimeUnit.SECONDS);

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .client(client)
                .baseUrl(API_BASE_URL)
                .build()

            return retrofit.create(AppWebService::class.java)
        }


    }


}