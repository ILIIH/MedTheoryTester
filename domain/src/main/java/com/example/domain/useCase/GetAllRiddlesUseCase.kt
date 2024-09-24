package com.example.domain.useCase

import com.example.domain.model.Riddle

interface GetAllRiddlesUseCase {
    suspend fun execute(startFrom: Int): List<Riddle>
}