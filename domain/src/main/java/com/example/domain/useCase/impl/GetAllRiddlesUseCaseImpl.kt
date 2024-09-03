package com.example.domain.useCase.impl

import com.example.domain.model.Riddle
import com.example.domain.repository.RiddleRepository
import com.example.domain.useCase.GetAllRiddlesUseCase

internal class GetAllRiddlesUseCaseImpl(
    private val riddleRepository: RiddleRepository
) : GetAllRiddlesUseCase {
    override suspend fun execute(): List<Riddle> = riddleRepository.getAllRiddles()
}