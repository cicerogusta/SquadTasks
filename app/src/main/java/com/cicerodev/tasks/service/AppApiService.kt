package com.cicerodev.tasks.service



import com.cicerodev.tasks.model.Task
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET

interface AppApiService {
    @GET("/tasks")
    suspend fun fetchTasksUser(): MutableList<Task>
    @GET("/tasks")
    suspend fun addTask(@Body task: Task)

    companion object {
        private const val BASE_URL = "https://api.example.com/"

        fun create(): AppApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AppApiService::class.java)
        }
    }
}