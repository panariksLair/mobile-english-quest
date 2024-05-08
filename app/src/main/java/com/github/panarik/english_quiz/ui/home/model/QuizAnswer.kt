package com.github.panarik.english_quiz.ui.home.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class QuizAnswer(val answer:String, val isRight:Boolean):Serializable