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
import com.joaovitor.recipecontrol.utils.Month
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var goalDao: GoalDao
    private lateinit var cardView: CardView
    private lateinit var btnNew: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setup()

        btnNew.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                cadastrar()

                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Cadastrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cardView.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setup(){
        cardView = findViewById<CardView>(R.id.cardTotais)
        btnNew = findViewById<Button>(R.id.buttonNovaReceita)

        goalDao = AppDatabase.getDatabase(this).goalDao()

        val goal = goalDao.getGoalByMonth(Month.getCurrentMonth())


    }

    private fun cadastrar(){

    }
}