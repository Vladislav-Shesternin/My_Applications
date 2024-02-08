package com.tmesfo.frtunes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.tmesfo.frtunes.databinding.ActivityMainBinding
import com.tmesfo.frtunes.util.Lottie
import com.tmesfo.frtunes.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.system.exitProcess

lateinit var binding: ActivityMainBinding
val lottie by lazy { Lottie(binding) }

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    private val coroutineMain = CoroutineScope(Dispatchers.Main)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
        lottie.showLoader()
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutineMain)
        finishAndRemoveTask()
        exitProcess(0)
    }



    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}