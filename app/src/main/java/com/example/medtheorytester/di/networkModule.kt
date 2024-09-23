package com.example.medtheorytester.di

import com.example.data.dataSource.RiddlesDS
import com.example.domain.model.Riddle
import com.example.medtheorytester.framework.APIInterface
import com.example.medtheorytester.framework.RetrofitInstance
import com.example.medtheorytester.framework.RiddleDataSource
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single { RetrofitInstance.create() }
    single { get<Retrofit>().create(APIInterface::class.java) }
    single<RiddlesDS> { RiddleDataSource(get()) }
}