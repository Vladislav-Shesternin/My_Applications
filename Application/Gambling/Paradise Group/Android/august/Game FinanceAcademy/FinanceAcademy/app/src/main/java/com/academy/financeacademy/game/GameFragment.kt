package com.academy.financeacademy.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.academy.financeacademy.MainActivity
import com.academy.financeacademy.databinding.FragmentGameBinding
import com.academy.financeacademy.game.manager.NavigationManager
import com.academy.financeacademy.game.screens.OnboardingScreen
import com.academy.financeacademy.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment: Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val coroutine     = CoroutineScope(Dispatchers.Default)
    private val activityMain  by lazy { requireActivity() as MainActivity }

    lateinit var navigationManager: NavigationManager private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationManager = NavigationManager(activityMain, binding.rootLayout, this)


        coroutine.launch {
            //val quit = true//GameDataStoreManager.Olivec.get() ?: false
            delay(1000)
            activityMain.lottie.hideLoader()
            navigationManager.navigate(OnboardingScreen(activityMain)) //else BitTrackerCheckScreen(activityMain))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
    }


}