package com.route.data.api

import android.util.Log
import com.google.gson.Gson
import com.route.data.api.interceptor.OkHttpAuthInterceptor
import com.route.data.api.interceptor.OkHttpCacheInterceptor
import com.route.data.api.interceptor.OkHttpOfflineCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di
// hilt
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor {
            Log.e("api->", it)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @OkHttpAuthInterceptor authInterceptor: Interceptor,
        cache: Cache,
        @OkHttpCacheInterceptor cacheInterceptor: Interceptor,
        @OkHttpOfflineCacheInterceptor offlineCacheInterceptor: Interceptor
    ):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .addNetworkInterceptor(cacheInterceptor)
            .addInterceptor(offlineCacheInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson():Gson{
        return Gson()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory):Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://ecommerce.routemisr.com/")
                .addConverterFactory(gsonConverterFactory)
                .build()


    }

    @Provides
    @Singleton
    fun provideNewsServices(retrofit: Retrofit): WebServices {
        return retrofit.create(WebServices::class.java)
    }
}