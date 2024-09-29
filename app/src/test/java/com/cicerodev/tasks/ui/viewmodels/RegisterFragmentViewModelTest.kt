package com.cicerodev.tasks.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cicerodev.tasks.model.User
import com.cicerodev.tasks.repository.AppFirebaseRepository
import com.cicerodev.tasks.service.AppFirebaseService
import com.cicerodev.tasks.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class RegisterFragmentViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterFragmentViewModel
    private lateinit var appFirebaseService: AppFirebaseService
    private lateinit var repository: AppFirebaseRepository
    private inline fun <reified T> mock(): T = mock(T::class.java)


    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mock(AppFirebaseRepository::class.java)
        appFirebaseService = AppFirebaseService(repository)
        viewModel = RegisterFragmentViewModel(appFirebaseService)
    }

    @Test
    fun `register success updates live data`() = runBlockingTest {

        val user = User(email = "teeeeste@gmail.com", password = "12345678")
        `when`(repository.addUser(user)).thenReturn(Result.success(Unit))


        val observer: Observer<UIState<User>> = mock()
        viewModel.uiState.observeForever(observer)

        viewModel.registerUser(user)
        verify(observer).onChanged(UIState.Loading)
        verify(observer).onChanged(UIState.Success(user))

    }



}