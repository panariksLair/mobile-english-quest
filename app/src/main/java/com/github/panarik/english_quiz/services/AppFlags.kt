package com.github.panarik.english_quiz.services

import android.util.Log
import com.github.panarik.english_quiz.ui.home.model.GameStates

object AppFlags {

    private const val TAG = "[AppFlags]"
    private const val AD_PERIOD_SESSIONS = 10 // for example each Ad per 10 sessions.

    private var sessionsCount = 0

    fun isAdTime(gameState: GameStates?): Boolean {
        return if (sessionsCount >= AD_PERIOD_SESSIONS && gameState == GameStates.QUIZ_FINISHED_SUCCESS) {
            Log.d(
                TAG,
                "Its time for Ad. ad_period=$AD_PERIOD_SESSIONS, current=$sessionsCount, game_state=$gameState"
            )
            true
        } else {
            Log.d(
                TAG,
                "Its NOT time for Ad. ad_period=$AD_PERIOD_SESSIONS, current=$sessionsCount, game_state=$gameState"
            )
            false
        }
    }

    fun newSession() {
        sessionsCount++
    }

    fun resetSession() {
        sessionsCount = 0
    }
}