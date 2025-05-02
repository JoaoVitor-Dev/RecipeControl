package com.joaovitor.recipecontrol

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.joaovitor.recipecontrol.utils.Month

class GoalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        setup()
    }

    private fun setup(){
        var textViewMonth: TextView = findViewById(R.id.textViewMonth)

        textViewMonth.setText(Month.getCurrentMonthName())
    }
}