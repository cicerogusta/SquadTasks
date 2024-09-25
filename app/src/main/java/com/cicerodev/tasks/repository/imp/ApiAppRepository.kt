package com.cicerodev.tasks.repository.imp

import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.repository.AppApiRepository
import com.cicerodev.tasks.service.AppApiService

class ApiAppRepository(private val appApiService: AppApiService) : AppApiRepository {
    override suspend fun addTask(task: Task): Result<Unit> {
        return try {
            appApiService.addTask(task)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTasks(): Result<List<Task>> {
        return try {
            val tasks = appApiService.fetchTasksUser()
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addUser(user: User): Result<Unit> {
//        addUser for api
        return Result.success(Unit)
    }
}