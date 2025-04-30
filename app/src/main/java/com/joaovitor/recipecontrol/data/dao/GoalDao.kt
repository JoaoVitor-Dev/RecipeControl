package com.joaovitor.recipecontrol.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.joaovitor.recipecontrol.data.entity.Goal
import com.joaovitor.recipecontrol.data.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {
    @Insert
    fun insertGoal(goal: Goal)
}