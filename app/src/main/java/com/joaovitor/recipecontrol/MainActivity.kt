package com.joaovitor.recipecontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var goalDao: GoalDao

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnNova: Button = findViewById(R.id.buttonNovaReceita)

        btnNova.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                cadastrar()

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Cadastrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val cardTotais: CardView = findViewById(R.id.cardTotais)
        cardTotais.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun cadastrar(){

        val goal = Goal(
            value = 100, // Valor do goal
            month = 4     // Mês (exemplo: abril)
        )
        goalDao = AppDatabase.getDatabase(this).goalDao()

        goalDao.insertGoal(goal)
    }
}