package com.cicerodev.tasks.di.module

import com.cicerodev.tasks.repository.AppApiRepository
import com.cicerodev.tasks.repository.AppFirebaseRepository
import com.cicerodev.tasks.repository.imp.ApiAppRepository
import com.cicerodev.tasks.repository.imp.FirestoreAppFirebaseRepository
import com.cicerodev.tasks.service.AppFirebaseService
import com.cicerodev.tasks.ui.viewmodels.NewTaskFragmentViewModel
import com.cicerodev.tasks.ui.viewmodels.RegisterFragmentViewModel
import com.cicerodev.tasks.ui.viewmodels.TasksFragmentViewModel
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Firebase Firestore
    single { FirebaseFirestore.getInstance() }

    // Retrofit para API
//    single { AppApiService.create() }

    // Repositórios
    single<AppFirebaseRepository> { FirestoreAppFirebaseRepository(get()) }
    single<AppApiRepository> { ApiAppRepository(get()) }

    // Service
    single { AppFirebaseService(get()) }

    // ViewModel
    viewModel { NewTaskFragmentViewModel(get()) }
    viewModel { RegisterFragmentViewModel(get()) }
    viewModel { TasksFragmentViewModel(get()) }
}
