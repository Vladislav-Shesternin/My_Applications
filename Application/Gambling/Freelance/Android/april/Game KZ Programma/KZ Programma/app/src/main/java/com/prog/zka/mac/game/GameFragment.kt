package com.prog.zka.mac.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prog.zka.mac.Splash
import com.prog.zka.mac.databinding.FragmentGameBinding
import com.prog.zka.mac.game.manager.NavigationManager
import com.prog.zka.mac.game.screens.MinutesScreen
import com.prog.zka.mac.util.cancelCoroutinesAll
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

        navigationManager = NavigationManager(requireActivity(), binding.rootLayout)
        navigationManager.navigate(MinutesScreen(requireActivity()))

        Splash.lottie.hideLoader()

    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
    }


}