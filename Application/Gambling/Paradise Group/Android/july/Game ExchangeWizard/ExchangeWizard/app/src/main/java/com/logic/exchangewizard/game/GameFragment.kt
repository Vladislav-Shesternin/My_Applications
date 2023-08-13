package com.logic.exchangewizard.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.databinding.FragmentGameBinding
import com.logic.exchangewizard.game.manager.GameDataStoreManager
import com.logic.exchangewizard.game.manager.NavigationManager
import com.logic.exchangewizard.game.screens.LinearScreen
import com.logic.exchangewizard.game.screens.WizardScreen
import com.logic.exchangewizard.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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


        coroutine.launch(Dispatchers.IO) {
            val quit = GameDataStoreManager.Qwest.get() ?: false

            activityMain.lottie.hideLoader()
            navigationManager.navigate(if (quit) LinearScreen(activityMain) else WizardScreen(activityMain))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
    }


}