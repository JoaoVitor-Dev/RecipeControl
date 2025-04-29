package com.joaovitor.recipecontrol.data.repository

import com.joaovitor.recipecontrol.data.dao.RecipeDao
import com.joaovitor.recipecontrol.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    fun getAllSales(): Flow<List<Recipe>> = recipeDao.getAllSales()

    suspend fun deleteSaleById(id: Long) = recipeDao.deleteSaleById(id)
}