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
    suspend fun getQuizes(): List<QuizEntity>
}