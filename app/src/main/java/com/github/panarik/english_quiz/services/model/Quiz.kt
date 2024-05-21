package com.github.panarik.english_quiz.services.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quiz(
    val summary: String,
    val question: String,
    val wrong_answer_1: String,
    val wrong_answer_2: String,
    val wrong_answer_3: String,
    val right_answer: String
) : Serializable {

    fun isValid() =
        summary.isNotEmpty() &&
                question.isNotEmpty() &&
                wrong_answer_1.isNotEmpty() &&
                wrong_answer_2.isNotEmpty() &&
                wrong_answer_3.isNotEmpty() &&
                right_answer.isNotEmpty()
}