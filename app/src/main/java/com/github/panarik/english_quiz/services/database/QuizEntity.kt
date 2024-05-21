package com.github.panarik.english_quiz.services.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quizes")
data class QuizEntity(

    @PrimaryKey val id: Long,

    val summary: String,
    val question: String,
    val wrong_answer_1: String,
    val wrong_answer_2: String,
    val wrong_answer_3: String,
    val right_answer: String
)