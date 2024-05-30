package com.bricks.vs.balls

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.NavController
import com.bricks.vs.balls.databinding.ActivityMainBinding
import com.bricks.vs.balls.util.LottieUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

lateinit var appContext: Context private set

class App: Application() {
    companion object {
        const val jjj = "adb"

        fun lotos(preferences: SharedPreferences) = preferences.contains(jjj)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

//    private val coroutine  = CoroutineScope(Dispatchers.Default)
//
//    private lateinit var navController: NavController
//
//    private lateinit var binding : ActivityMainBinding
//
//    lateinit var lottie          : LottieUtil
//
//    fun toGame() {
//        binding.apply {
//            listOf(toppp, centerrr, alll).onEach { itemView ->
//                itemView.clearAnimation()
//                root.removeView(itemView)
//            }
//            viewsWebs.onEach { root.removeView(it) }
//            tmpDialog?.dismiss()
//        }
//
//        ConstraintSet().run {
//            this.clone(binding.root)
//            this.constrainPercentWidth(binding.loader.id, 0.175f)
//            this.applyTo(binding.root)
//        }
//
//        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(R.id.libGDXFragment) }.also { setGraph(it, null) } }
//    }
//
//    private fun initialize() {
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        navController = findNavController(R.id.nav_host_fragment)
//        lottie        = LottieUtil(binding)
//    }

}