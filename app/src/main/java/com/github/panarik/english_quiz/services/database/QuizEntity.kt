package com.github.panarik.english_quiz.services.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.panarik.english_quiz.services.model.Quiz
import com.github.panarik.english_quiz.services.model.QuizGroup
import com.github.panarik.english_quiz.ui.home.model.QuizAnswer
import com.github.panarik.english_quiz.ui.home.model.QuizSession

@Entity(tableName = "quizes")
data class QuizEntity(

    @PrimaryKey val id: String,
    val group: QuizGroup,
    val viewed: Boolean,
    val difficult: String,
    val topic: String,
    val summary: String,
    val question: String,
    val wrong_answer_1: String,
    val wrong_answer_2: String,
    val wrong_answer_3: String,
    val right_answer: String
) {

    fun toQuizSession(): QuizSession = QuizSession(
        sessionId = this.id,
        quiz = Quiz(
            summary = this.summary,
            question = this.question,
            wrong_answer_1 = this.wrong_answer_1,
            wrong_answer_2 = this.wrong_answer_2,
            wrong_answer_3 = this.wrong_answer_3,
            right_answer = this.right_answer
        ),
        answers = listOf(
            QuizAnswer(answer = this.right_answer, true),
            QuizAnswer(answer = this.wrong_answer_1, false),
            QuizAnswer(answer = this.wrong_answer_2, false),
            QuizAnswer(answer = this.wrong_answer_3, false),
        )
    )

}