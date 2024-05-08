package com.github.panarik.english_quiz.ui.home.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class QuizSession(
    val sessionId: String,
    val quiz: Quiz,
    var answers: List<QuizAnswer>? = null
) : Serializable {



    fun isValid(): Boolean =
        sessionId.isNotEmpty() && quiz.isValid()

}