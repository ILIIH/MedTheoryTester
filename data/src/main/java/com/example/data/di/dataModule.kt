package com.example.data.di
import com.example.data.repository.RiddleRepositoryImpl
import com.example.domain.repository.RiddleRepository
import org.koin.dsl.module

val dataModule = module {
    single<RiddleRepository> { RiddleRepositoryImpl(get()) }
}