package com.joaovitor.recipecontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.database.AppDatabase
import com.joaovitor.recipecontrol.data.entity.Goal
import com.joaovitor.recipecontrol.data.repository.GoalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var goalDao: GoalDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val cardTotais: CardView = findViewById(R.id.cardTotais)
        val btnNova: Button = findViewById(R.id.buttonNovaReceita)

        cardTotais.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }

        btnNova.setOnClickListener {

            val goal = Goal(
                value = 1000, // Valor do goal
                month = 4     // Mês (exemplo: abril)
            )

            goalDao = AppDatabase.getDatabase(this).goalDao()

            goalDao.insertGoal(goal)
        }
    }
}