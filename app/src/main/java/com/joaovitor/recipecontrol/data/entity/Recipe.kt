package com.joaovitor.recipecontrol.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes",
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["goal_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "observation")
    val customerName: String,

    @ColumnInfo(name = "month")
    val month: Int,

    @ColumnInfo(name = "day")
    val day: Int,

    @ColumnInfo(name = "year")
    val year: Int,

    @ColumnInfo(name = "hour")
    val hour: Int,

    @ColumnInfo(name = "minute")
    val minute: Int,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "goal_id")
    val goalId: Long
)