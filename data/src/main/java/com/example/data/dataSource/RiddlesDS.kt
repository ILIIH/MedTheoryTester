package com.example.data.dataSource

import com.example.domain.model.Riddle

interface  RiddlesDS{
    suspend fun getAllRiddles(selectFrom:Int): List<Riddle>
}