package com.github.panarik.english_quiz

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.github.panarik.english_quiz.databinding.ActivityMainBinding
import com.github.panarik.english_quiz.services.database.QuizesData
import com.github.panarik.english_quiz.services.database.QuizesDatabase
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch

private const val TAG = "[MainActivity]"

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            QuizesDatabase::class.java,
            "my-quiz"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationBar()
        setupDatabase()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigationBar() {
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupDatabase() {
        Log.d(TAG, "Finding Quiz database...")
        lifecycleScope.launch {
            val quizes = db.dao.getQuizes()
            Log.d(TAG, "Quizes found: ${quizes.joinToString()}")
            if (quizes.isEmpty()) {
                Log.d(TAG, "Quiz database is empty. Starting set database.")
                db.dao.insertAll(QuizesData().getQuizes())
                if (db.dao.getQuizes().isEmpty()) {
                    Log.e(TAG, "Error during setup Quiz database!")
                }
            } else {
                Log.d(TAG, "Quiz database is ready to go.")
            }
        }
    }
}