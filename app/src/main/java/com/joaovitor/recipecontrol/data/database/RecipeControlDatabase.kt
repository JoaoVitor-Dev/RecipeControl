package com.joaovitor.recipecontrol.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joaovitor.recipecontrol.data.dao.RecipeDao
import com.joaovitor.recipecontrol.data.entity.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeControlDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeControlDatabase? = null

        fun getDatabase(context: Context): RecipeControlDatabase {
            // retorna uma nova instancia caso seja nula
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeControlDatabase::class.java,
                    "RecipeControl_Database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}