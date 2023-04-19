package com.zet.vest.app.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zet.vest.app.MainActivity
import com.zet.vest.app.databinding.FragmentGameBinding
import com.zet.vest.app.game.manager.NavigationManager
import com.zet.vest.app.game.screens.BalanceScreen
import com.zet.vest.app.game.util.BalanceUtil
import com.zet.vest.app.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@SuppressLint("StaticFieldLeak")
lateinit var navigationManager: NavigationManager private set

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
            navigationManager = NavigationManager(requireActivity(), binding.rootLayout)
            navigationManager.navigate(BalanceScreen(requireActivity()))
            MainActivity.lottie.hideLoader()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
        BalanceUtil.dispose()
    }


}