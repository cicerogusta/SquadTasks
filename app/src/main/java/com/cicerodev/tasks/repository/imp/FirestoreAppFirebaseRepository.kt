package com.cicerodev.tasks.repository.imp

import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.repository.AppFirebaseRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreAppFirebaseRepository(private val firestore: FirebaseFirestore): AppFirebaseRepository {
    override suspend fun addTask(task: Task): Result<Unit> {
        return try {
            firestore.collection("tasks")
                .add(task)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTasks(): Result<List<Task>> {
        return try {
            val tasks = firestore.collection("tasks")
                .get()
                .await()
                .toObjects(Task::class.java)
            Result.success(tasks)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addUser(user: User): Result<Unit> {
        return  try {
            val userData = hashMapOf(
                "name" to user.name,
                "email" to user.email,
                "isHighLevelUser" to user.isHighLevelUser,
                "squad" to user.squad
            )


            firestore.collection("users").document()
                .set(userData)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}