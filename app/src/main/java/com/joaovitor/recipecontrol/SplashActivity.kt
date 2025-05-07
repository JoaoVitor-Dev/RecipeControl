package com.joaovitor.recipecontrol

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Looper
import android.widget.TextView
import androidx.core.view.WindowCompat
import com.joaovitor.recipecontrol.data.dao.GoalDao
import com.joaovitor.recipecontrol.data.database.AppDatabase
import com.joaovitor.recipecontrol.utils.Month
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class SplashActivity : AppCompatActivity() {
    private lateinit var goalDao: GoalDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_splash)

        goalDao = AppDatabase.getDatabase(this).goalDao()

        CoroutineScope(Dispatchers.Main).launch {
            val intent = withContext(Dispatchers.IO) {
                if (goalDao.getGoalByMonth(LocalDate.now().monthValue) == null) {
                    Intent(this@SplashActivity, GoalActivity::class.java)
                } else {
                    Intent(this@SplashActivity, MainActivity::class.java)
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(intent)
                finish()
            }, 2000L)
        }
    }
}