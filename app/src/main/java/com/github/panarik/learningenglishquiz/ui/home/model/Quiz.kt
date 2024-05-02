package com.github.panarik.learningenglishquiz.ui.home.model

data class Quiz(
    val summary: String,
    val question: String,
    val wrong_answers: List<String>,
    val right_answer: String
) {

    fun isValid() =
        summary.isNotEmpty() && question.isNotEmpty() && wrong_answers.isNotEmpty() && right_answer.isNotEmpty()
}