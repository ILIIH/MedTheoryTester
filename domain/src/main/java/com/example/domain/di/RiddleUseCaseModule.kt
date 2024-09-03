package com.example.domain.di

import com.example.domain.useCase.GetAllRiddlesUseCase
import com.example.domain.useCase.impl.GetAllRiddlesUseCaseImpl
import org.koin.dsl.module


val RiddleUseCaseModule = module {
    single<GetAllRiddlesUseCase> { GetAllRiddlesUseCaseImpl(get()) }
}