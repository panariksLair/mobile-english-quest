package com.github.panarik.english_quiz.services.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuizEntity::class], version = 4)
abstract class QuizesDatabase : RoomDatabase() {

    abstract val dao: QuizDao

}