package com.example.data.mock

import com.example.domain.model.Answer
import com.example.domain.model.Riddle
import kotlinx.coroutines.delay

internal class RiddlesFactory() {
    suspend fun get(): List<Riddle> {
        delay(1000)
        return listOf(
            Riddle(
                id = 1,
                question = "I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?",
                answers = listOf(
                    Answer(id = 1, answer = "An echo", isTrue = true),
                    Answer(id = 2, answer = "A shadow", isTrue = false),
                    Answer(id = 3, answer = "A ghost", isTrue = false)
                )
            ),
            Riddle(
                id = 2,
                question = "I’m tall when I’m young, and I’m short when I’m old. What am I?",
                answers = listOf(
                    Answer(id = 1, answer = "A candle", isTrue = true),
                    Answer(id = 2, answer = "A tree", isTrue = false),
                    Answer(id = 3, answer = "A shadow", isTrue = false)
                )
            ),
            Riddle(
                id = 3,
                question = "What has keys but can’t open locks?",
                answers = listOf(
                    Answer(id = 1, answer = "A piano", isTrue = true),
                    Answer(id = 2, answer = "A map", isTrue = false),
                    Answer(id = 3, answer = "A keyboard", isTrue = true)
                )
            ),
            Riddle(
                id = 4,
                question = "The more of this there is, the less you see. What is it?",
                answers = listOf(
                    Answer(id = 1, answer = "Darkness", isTrue = true),
                    Answer(id = 2, answer = "Light", isTrue = false),
                    Answer(id = 3, answer = "Fog", isTrue = false)
                )
            )
        )
    }
}