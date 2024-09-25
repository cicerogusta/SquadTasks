//package com.cicerodev.tasks.service
//
//
//
//import com.cicerodev.tasks.model.Task
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.POST
//
//interface AppApiService {
//    @GET("/tasks")
//    suspend fun fetchTasksUser(): MutableList<Task>
//    @POST("/posts")
//    suspend fun addTask(@Body task: Task)
//
//    companion object {
//        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//
//        fun create(): AppApiService {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            return retrofit.create(AppApiService::class.java)
//        }
//    }
//}