package com.joaovitor.recipecontrol.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.dao.RecipeDao
import com.joaovitor.recipecontrol.data.entity.Recipe
import com.joaovitor.recipecontrol.data.entity.Goal

@Database(entities = [Goal::class,Recipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun goalDao(): GoalDao
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "RecipeControl_Database"
                )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}