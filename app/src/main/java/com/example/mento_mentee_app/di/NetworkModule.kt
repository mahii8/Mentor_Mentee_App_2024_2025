package com.example.mento_mentee_app.di

import com.example.mento_mentee_app.data.api.TaskApi
import com.example.mento_mentee_app.data.repository.TaskRepository
import android.content.Context
import com.example.mento_mentee_app.data.api.RequestApi
import com.example.mento_mentee_app.data.datastore.TokenManager
import com.example.mento_mentee_app.data.api.MentorApi
import com.example.mento_mentee_app.data.repository.MentorRepository
import com.example.mento_mentee_app.data.repository.RequestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:8888/" // Localhost for Android emulator

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenManager: TokenManager): Interceptor {
        return Interceptor { chain ->
            val token = runBlocking {
                tokenManager.token.firstOrNull()
            }

            val newRequest = chain.request().newBuilder().apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }.build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideTaskApi(retrofit: Retrofit): TaskApi {
        return retrofit.create(TaskApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskApi: TaskApi): TaskRepository {
        return TaskRepository(taskApi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMentorApi(retrofit: Retrofit): MentorApi {
        return retrofit.create(MentorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRequestApi(retrofit: Retrofit): RequestApi {
        return retrofit.create(RequestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMentorRepository(mentorApi: MentorApi): MentorRepository {
        return MentorRepository(mentorApi)
    }

    @Provides
    @Singleton
    fun provideRequestRepository(requestApi: RequestApi): RequestRepository {
        return RequestRepository(requestApi)
    }
}
