package com.cicerodev.tasks.service

import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.repository.AppRepository

class AppService(
    private val firestoreRepository: AppRepository
//    private val apiRepository: AppRepository
) {
     suspend fun createTask(task: Task): Result<Unit> {
       return firestoreRepository.addTask(task)
    }

    suspend fun fetchTasks(): Result<List<Task>> {
        return firestoreRepository.getTasks()
    }

    suspend fun addUser(user: User): Result<Unit> {
        return firestoreRepository.addUser(user)
    }


}