package com.example.domain.repository

import com.example.domain.model.Riddle

interface RiddleRepository {
    suspend fun getAllRiddles(startFrom: Int): List<Riddle>
}