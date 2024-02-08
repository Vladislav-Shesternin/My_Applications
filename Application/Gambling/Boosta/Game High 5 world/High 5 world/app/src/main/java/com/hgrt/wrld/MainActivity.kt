package com.hgrt.wrld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.hgrt.wrld.databinding.ActivityMainBinding
import com.hgrt.wrld.util.Lottie
import com.hgrt.wrld.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding: ActivityMainBinding
        val lottie by lazy { Lottie(binding) }
    }

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