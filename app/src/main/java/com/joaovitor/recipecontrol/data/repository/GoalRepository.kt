package com.joaovitor.recipecontrol.data.repository

import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.entity.Goal

class GoalRepository(private val goalDao: GoalDao) {
    fun insertGoal(goal: Goal) = goalDao.insertGoal(goal)
}