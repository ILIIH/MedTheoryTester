package com.example.medtheorytester.framework

import com.example.data.dataSource.RiddlesDS
import com.example.domain.model.Riddle

class RiddleDataSource(private val api:APIInterface): RiddlesDS {
    override suspend fun  getAllRiddles() = api.getRiddles();
}