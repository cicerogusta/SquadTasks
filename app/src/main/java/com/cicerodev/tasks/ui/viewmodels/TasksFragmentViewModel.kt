package com.cicerodev.tasks.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.service.AppService
import com.cicerodev.tasks.ui.state.UIState
import kotlinx.coroutines.launch

class TasksFragmentViewModel(private val appService: AppService) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<MutableList<Task>>>(UIState.Loading)
     val uiState: MutableLiveData<UIState<MutableList<Task>>> get() = _uiState

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            val result = appService.fetchTasks()
            if (result.isSuccess) {
                _uiState.value = result.getOrNull()?.let { UIState.Success(it.toMutableList()) }
            } else {
                _uiState.value = UIState.Error(result.exceptionOrNull()?.message ?: "Error fetching tasks")
            }
        }
    }

    fun fetchUserTasksApi() {
        viewModelScope.launch {
//            val tasks = appRepository.fetchUserTasksApi()
//            _tasks.value = tasks
        }
    }
}