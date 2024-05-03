package com.github.panarik.learningenglishquiz.ui.home.model

import java.io.Serializable

data class QuizSession(val sessionId: String, val quiz: Quiz) : Serializable {

    fun isValid(): Boolean =
        sessionId.isNotEmpty() && quiz.isValid()

}