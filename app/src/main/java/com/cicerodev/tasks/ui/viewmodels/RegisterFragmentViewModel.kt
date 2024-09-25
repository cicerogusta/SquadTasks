package com.cicerodev.tasks.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.service.AppFirebaseService
import com.cicerodev.tasks.ui.state.UIState
import kotlinx.coroutines.launch

class RegisterFragmentViewModel(private val appFirebaseService: AppFirebaseService) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<User>>()
    val uiState: LiveData<UIState<User>> get() = _uiState
    fun registerUser(user: User) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            val result = appFirebaseService.addUser(user)
            if (result.isSuccess) {
                _uiState.value = UIState.Success(user)
            } else {
                _uiState.value = UIState.Error(result.exceptionOrNull()?.message ?: "Error fetching tasks")
            }
        }
    }
}