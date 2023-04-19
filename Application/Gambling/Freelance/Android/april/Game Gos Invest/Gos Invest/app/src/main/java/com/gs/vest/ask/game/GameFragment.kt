package com.gs.vest.ask.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gs.vest.ask.Main
import com.gs.vest.ask.databinding.FragmentGameBinding
import com.gs.vest.ask.game.manager.NavigationManager
import com.gs.vest.ask.game.screens.ProfileScreen
import com.gs.vest.ask.game.util.BalanceUtil
import com.gs.vest.ask.game.util.TimerUtil
import com.gs.vest.ask.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@SuppressLint("StaticFieldLeak")
lateinit var navigationManager: NavigationManager private set
lateinit var timerUtil: TimerUtil private set

class GameFragment: Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val coroutine = CoroutineScope(Dispatchers.Default)



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BalanceUtil.initialize {
            timerUtil = TimerUtil()

            navigationManager = NavigationManager(requireActivity(), binding.rootLayout)
            navigationManager.navigate(ProfileScreen(requireActivity()))

            Main.lottie.hideLoader()

            timerUtil.initialize(coroutine)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
        BalanceUtil.dispose()
    }


}