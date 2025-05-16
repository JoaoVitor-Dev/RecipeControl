package com.joaovitor.recipecontrol

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.dao.RecipeDao
import com.joaovitor.recipecontrol.data.database.AppDatabase
import com.joaovitor.recipecontrol.data.entity.Goal
import com.joaovitor.recipecontrol.data.entity.Recipe
import com.joaovitor.recipecontrol.utils.DateUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var goalDao: GoalDao
    private lateinit var recipeDao: RecipeDao
    private lateinit var goal: Goal
    private lateinit var cardView: CardView
    private lateinit var btnNew: Button
    private lateinit var textGoal: TextView
    private lateinit var textTotal: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<Toolbar>(R.id.customToolbar)
        setSupportActionBar(toolbar)

        val customMenuButton = findViewById<ImageButton>(R.id.customMenuButton)
        customMenuButton.setOnClickListener {
            val popupMenu = PopupMenu(this, customMenuButton)
            popupMenu.menuInflater.inflate(R.menu.bottom_navigation_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_home -> {
                        true
                    }
                    R.id.nav_search -> {
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        setup()

        btnNew.setOnClickListener {
            register()
        }

        cardView.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    private fun setup(){
        cardView = findViewById<CardView>(R.id.cardTotais)
        btnNew = findViewById<Button>(R.id.buttonNovaReceita)
        textGoal = findViewById<TextView>(R.id.textGoal)
        textTotal = findViewById<TextView>(R.id.textTotal)

        CoroutineScope(Dispatchers.IO).launch {
            goalDao = AppDatabase.getDatabase(applicationContext).goalDao()
            recipeDao = AppDatabase.getDatabase(applicationContext).recipeDao()

            goal = goalDao?.getGoalByMonth(DateUtils.getCurrentMonth())!!
            var recipesCount = recipeDao.countRecipesByGoalId(goal?.id ?: 0)

            withContext(Dispatchers.Main) {
                textGoal.text =  "Meta: "+goal?.value
                textTotal.text = getString(R.string.receitas_de_maio)+" "+recipesCount
                supportActionBar?.title = "Olá "+goal?.name
            }
        }
    }

    private fun register(){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Atenção")
        builder.setMessage("Deseja lançar uma nova Receita?")

        builder.setPositiveButton("Sim") { dialog, _ ->
            saveRecipe()
            dialog.dismiss()
        }

        builder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun saveRecipe(){
        CoroutineScope(Dispatchers.IO).launch {

            val recipe = Recipe(
                0,
                "",
                DateUtils.getCurrentMonth(),
                DateUtils.getDay(),
                DateUtils.getYear(),
                DateUtils.getHour(),
                DateUtils.getMinute(),
                DateUtils.getTimestamp(),
                goal.id
            )

            recipeDao.insertRecipe(recipe)

            var recipesCount = recipeDao.countRecipesByGoalId(goal?.id ?: 0)

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Parabéns pela nova Venda!", Toast.LENGTH_SHORT).show()
                textTotal.text = getString(R.string.receitas_de_maio)+" "+recipesCount
            }
        }
    }
}