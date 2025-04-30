package com.joaovitor.recipecontrol.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joaovitor.recipecontrol.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY timestamp DESC")
    fun getAllSales(): Flow<List<Recipe>>

    @Query("DELETE FROM recipes WHERE id = :saleId")
    suspend fun deleteSaleById(saleId: Long)

    @Insert
     fun insertRecipe(recipe: Recipe)
}