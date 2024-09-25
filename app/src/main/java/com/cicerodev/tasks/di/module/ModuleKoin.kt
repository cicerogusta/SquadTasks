package com.cicerodev.tasks.di.module

import com.cicerodev.tasks.repository.AppRepository
import com.cicerodev.tasks.repository.imp.FirestoreAppRepository
import com.cicerodev.tasks.service.AppService
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
    single<AppRepository> { FirestoreAppRepository(get()) }
//    single<AppRepository> { ApiAppRepository(get()) }

    // Service
    single { AppService(get()) }

    // ViewModel
    viewModel { NewTaskFragmentViewModel(get()) }
    viewModel { RegisterFragmentViewModel(get()) }
    viewModel { TasksFragmentViewModel(get()) }
}
