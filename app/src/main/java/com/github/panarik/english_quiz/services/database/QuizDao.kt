package com.github.panarik.english_quiz.services.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizDao {

    @Insert
    suspend fun insertAll(quizes: List<QuizEntity>)

    @Delete
    suspend fun delete(quiz: QuizEntity)

    @Query("SELECT * FROM quizes")
    suspend fun getAllQuizes(): List<QuizEntity>

    @Query("SELECT * FROM quizes WHERE viewed = 0")
    suspend fun getQuizes():List<QuizEntity>

    @Query("UPDATE quizes SET viewed = 'true' WHERE id=:id")
    suspend fun markAsWatchedQuiz(id: String)
}