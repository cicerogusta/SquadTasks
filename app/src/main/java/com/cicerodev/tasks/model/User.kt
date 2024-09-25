package com.cicerodev.tasks.model

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isHighLevelUser: Boolean = false,
    val listTasks: MutableList<Task> = mutableListOf(),
    val squad: String = ""

)
