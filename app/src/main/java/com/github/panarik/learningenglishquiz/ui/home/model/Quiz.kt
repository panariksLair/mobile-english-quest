package com.github.panarik.learningenglishquiz.ui.home.model

import java.io.Serializable

data class Quiz(
    val summary: String,
    val question: String,
    val wrong_answers: List<String>,
    val right_answer: String
) : Serializable {

    fun isValid() =
        summary.isNotEmpty() && question.isNotEmpty() && wrong_answers.isNotEmpty() && right_answer.isNotEmpty()
}