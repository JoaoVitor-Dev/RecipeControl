package com.joaovitor.recipecontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    private lateinit var textGoal: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.customToolbar)
        setSupportActionBar(toolbar)

        //supportActionBar?.title = "RecipeControl"

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_home -> {
                Toast.makeText(this, "Configurações selecionadas", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.nav_search -> {
                Toast.makeText(this, "Sobre selecionado", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setup(){
        cardView = findViewById<CardView>(R.id.cardTotais)
        btnNew = findViewById<Button>(R.id.buttonNovaReceita)
        textGoal = findViewById<TextView>(R.id.textGoal)

        CoroutineScope(Dispatchers.IO).launch {
            goalDao = AppDatabase.getDatabase(applicationContext).goalDao()

            var goal = goalDao.getGoalByMonth(Month.getCurrentMonth())

            withContext(Dispatchers.Main) {
                textGoal.text =  "Meta: "+goal?.value
                supportActionBar?.title = "Olá "+goal?.name
            }
        }
    }

    private fun cadastrar(){

    }
}