package com.example.medtheorytester.di
import com.example.medtheorytester.viewModel.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { QuizViewModel(get()) }
}