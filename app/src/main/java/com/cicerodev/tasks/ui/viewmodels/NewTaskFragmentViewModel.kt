package com.cicerodev.tasks.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicerodev.tasks.model.Task
import com.cicerodev.tasks.service.AppFirebaseService
import com.cicerodev.tasks.ui.state.UIState
import kotlinx.coroutines.launch

class NewTaskFragmentViewModel(private val appFirebaseService: AppFirebaseService) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<Boolean>>()
    val uiState: MutableLiveData<UIState<Boolean>> get() = _uiState



    fun addNewTask(task: Task) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            val result = appFirebaseService.createTask(task)
            if (result.isSuccess) {
                _uiState.value = UIState.Success(result.isSuccess)
            } else {
                _uiState.value = UIState.Error(result.exceptionOrNull()?.message ?: "Failed to add task")
            }
        }
    }
}