package com.example.medtheorytester.di

import com.example.medtheorytester.framework.APIInterface
import com.example.medtheorytester.framework.RetrofitInstance
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single { RetrofitInstance.create() }
    single { get<Retrofit>().create(APIInterface::class.java) }
}