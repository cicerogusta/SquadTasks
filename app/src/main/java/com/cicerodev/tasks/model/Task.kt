package com.cicerodev.tasks.model

data class Task(
    var id: String? = "",
    val taskTitle: String = "",
    val taskDescription: String = "",
    val whoSent: User = User(),
    val priorityTask: Boolean = false

) {
    constructor() : this("", "", "",)
}
