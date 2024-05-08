package com.github.panarik.english_quiz.ui.home.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quiz(
    val summary: String,
    val question: String,
    val wrong_answers: List<String>,
    val right_answer: String
) : Serializable {

    fun isValid() =
        summary.isNotEmpty() && question.isNotEmpty() && wrong_answers.isNotEmpty() && right_answer.isNotEmpty()
}