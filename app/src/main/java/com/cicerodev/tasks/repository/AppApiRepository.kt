package com.cicerodev.tasks.repository

import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.model.User

interface AppApiRepository {

    suspend fun addTask(task: Task): Result<Unit>
    suspend fun getTasks(): Result<List<Task>>
    suspend fun addUser(user: User): Result<Unit>
}