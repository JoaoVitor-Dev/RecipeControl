package com.joaovitor.recipecontrol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.database.AppDatabase
import com.joaovitor.recipecontrol.data.entity.Goal
import com.joaovitor.recipecontrol.utils.Month
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GoalActivity : AppCompatActivity() {

    private lateinit var btnSave: Button;
    private lateinit var txtMonth: TextView;
    private lateinit var edtGoal: EditText;
    private lateinit var edtName: EditText;
    private lateinit var goalDao: GoalDao;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        setup()

        btnSave.setOnClickListener {
            save()
        }
    }

    private fun setup(){
        txtMonth = findViewById<TextView>(R.id.textViewMonth)
        btnSave = findViewById<Button>(R.id.buttonSave)
        edtGoal = findViewById<EditText>(R.id.editTextGoal)
        edtName = findViewById<EditText>(R.id.editTextNome)

        goalDao = AppDatabase.getDatabase(this).goalDao()
    }

    private fun save(){
        if (edtName.text.trim().isEmpty()){
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show()
            return
        }

        if (edtGoal.text.trim().isEmpty()){
            Toast.makeText(this, "Informe a sua meta do mês", Toast.LENGTH_SHORT).show()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            saveGoal()

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "Nova meta cadastrada com sucesso", Toast.LENGTH_SHORT).show()
            }
        }

        navigateToMain()
    }

    private fun saveGoal(){
        val goal = Goal(
            value = Integer.parseInt(edtGoal.text.trim().toString()),
            month = Month.getCurrentMonth()
        )
        goalDao.insertGoal(goal)
    }

    private fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}