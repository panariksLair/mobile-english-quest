package com.github.panarik.learningenglishquiz.ui.home.model

import java.io.Serializable

data class QuizSession(
    val sessionId: String,
    val quiz: Quiz,
    var answers: List<QuizAnswer>? = null
) : Serializable {

    fun isValid(): Boolean =
        sessionId.isNotEmpty() && quiz.isValid()

}