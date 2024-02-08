package com.favsport.slots

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.favsport.slots.databinding.ActivityMainBinding
import kotlin.system.exitProcess

lateinit var activityContext: Activity private set
lateinit var navController: NavController private set
lateinit var binding: ActivityMainBinding private set
lateinit var adInterstitial: AdInterstitial private set

class AndroidLauncher : FragmentActivity(), AndroidFragmentApplication.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_FavSportSlots)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activityContext = this
        navController = findNavController(R.id.nav_host_fragment)
        adInterstitial = AdInterstitial().apply { load() }
    }

    override fun exit() {
        adInterstitial.dispose()
        finish()
        exitProcess(0)
    }

}