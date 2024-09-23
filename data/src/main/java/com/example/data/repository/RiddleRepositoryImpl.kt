package com.example.data.repository

import com.example.data.dataSource.RiddlesDS
import com.example.domain.model.Riddle
import com.example.domain.repository.RiddleRepository


internal class RiddleRepositoryImpl(
    private val riddlesDataSource: RiddlesDS,
) : RiddleRepository {
    override suspend fun getAllRiddles(): List<Riddle> {
        return riddlesDataSource.getAllRiddles();
    }
}