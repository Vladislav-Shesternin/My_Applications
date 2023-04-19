package com.pharhaslo.slo7

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.pharhaslo.slo7.databinding.ActivityMainBinding
import com.pharhaslo.slo7.util.OkHttpCustomClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

lateinit var activityContext: Activity private set
lateinit var navController: NavController private set
lateinit var binding: ActivityMainBinding private set

@ExperimentalCoroutinesApi
@SuppressLint("SourceLockedOrientationActivity")
class MainActivity : FragmentActivity(), AndroidFragmentApplication.Callbacks {
    private var isShowButtonActivated = false
    lateinit var viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_PharaohSlots)
        viewModel = MainViewModel(application)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        OkHttpCustomClient.setOkHttpClient(WebSettings.getDefaultUserAgent(this))

        checkForAppUpdates()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        activityContext = this
        navController = findNavController(R.id.navHostFragment)


        val sharedPrefs = getSharedPreferences("save", 0)


        viewModel.isFullGameButtonActivated.observe(this){
            isShowButtonActivated = it
            if(!isShowButtonActivated){
                binding.buttonGoToFullGame.visibility = View.GONE
            } else {
                binding.buttonGoToFullGame.visibility = View.VISIBLE
            }
        }

        val isPhoneGood = sharedPrefs.getBoolean("isPhoneGood", true)
        if(isPhoneGood){
            binding.buttonGoToFullGame.setOnClickListener {
                navHostFragment.findNavController().navigate(R.id.navigation_verification)
            }
            navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id){
                    R.id.navigation_game -> {
                        showButtonGoToFullGame()
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }
                    else -> {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER
                        binding.buttonGoToFullGame.visibility = View.GONE
                    }
                }
            }
        } else {
            navHostFragment.findNavController().navigate(R.id.navigation_game)
        }

    }

    private fun checkForAppUpdates() {
        val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { result ->
            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, this, 0)
            }
        }
    }

    private fun showButtonGoToFullGame(){
        if(isShowButtonActivated){
            binding.buttonGoToFullGame.visibility = View.VISIBLE
        }
    }
    override fun onResume() {
        super.onResume()
        val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { result ->
            if (result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS){
                appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, this, 0)
            }
        }
    }

    companion object {
        private val coroutineLoader = CoroutineScope(Dispatchers.Main)



        fun showLoader() {
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = true
                    playAnimation()
                }
            }
        }

        fun hideLoader() {
            coroutineLoader.launch {
                binding.lottie.apply {
                    isVisible = false
                    cancelAnimation()
                }
            }
        }
    }

    override fun exit() {
        finish()
        exitProcess(0)
    }

}