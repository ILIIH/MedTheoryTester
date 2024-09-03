package com.example.data.repository

import com.example.data.mock.RiddlesFactory
import com.example.domain.model.Riddle
import com.example.domain.repository.RiddleRepository


internal class RiddleRepositoryImpl(
    private val riddlesFactory: RiddlesFactory,
) : RiddleRepository {
    override suspend fun getAllRiddles(): List<Riddle> {
        return riddlesFactory.get();
    }
}