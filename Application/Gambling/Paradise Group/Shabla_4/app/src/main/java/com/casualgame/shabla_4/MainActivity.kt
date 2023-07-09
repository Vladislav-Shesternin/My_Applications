package com.casualgame.shabla_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.casualgame.shabla_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //TODO CREATE PACKAGE
    //TODO SETT MANIFEST
    //TODO CHANGE ICON
    //TODO CHANGE NAMES VARIABLES AND METHODS
    //TODO CHANGE LOCATION METHOD
    //TODO ADD TRASH CODE
    //TODO CHANGE BUNDLE
    //TODO KEY APSFLYER
    //TODO KEY ONESIGNAL
    //TODO CHANGE URL HOST
    //TODO CHANGE STRING CHECKER
    //TODO CHANGE DOMAIN
    //TODO CHANGE KEYDOMAIN
    //TODO CHANGE NAMING CLASSES
    //TODO ADD ANIMATION FROM LOTTIE
    //TODO PROGUARD
    //TODO SETTING RELISE PROGUARD
    //TODO CONNECT FIREBASE
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ani: LottieAnimationView = findViewById(R.id.download)
        ani.apply {
            //TODO ANIMATION
            setAnimation("lopperbar.json")
            repeatCount = LottieDrawable.INFINITE
            playAnimation()
        }
        viewModel.setHostAndGeo()
        viewModel.openGameFragment.observe(this) { shouldOpenGame ->
            if (shouldOpenGame) {
                openGameActivity()
            }
        }

        viewModel.openWebView.observe(this) { shouldOpenWebView ->
            if (shouldOpenWebView) {
                openWebViewActivity()
            }
        }
    }

    private fun openGameActivity() {
        val intent = Intent(this, Game::class.java)
        startActivity(intent)
    }

    private fun openWebViewActivity() {
        val intent = Intent(this, Web::class.java)
        startActivity(intent)
    }
}