package com.github.panarik.english_quiz.services.database

import kotlin.random.Random

class QuizesData {

    fun getQuizes(): List<QuizEntity> {

        // Test put new quiz to local database
        val quiz = QuizEntity(
            id = 0,
            summary = "Summary12",
            question = "What is the capital of France?",
            wrong_answer_1 = "Paris",
            wrong_answer_2 = "Paris",
            wrong_answer_3 = "Paris",
            right_answer = "Anresd"
        )
        return listOf(quiz)
    }

}