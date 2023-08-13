package com.nikelodeon.coursecalculator.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nikelodeon.coursecalculator.MainActivity
import com.nikelodeon.coursecalculator.databinding.FragmentGameBinding
import com.nikelodeon.coursecalculator.game.manager.GameDataStoreManager
import com.nikelodeon.coursecalculator.game.manager.NavigationManager
import com.nikelodeon.coursecalculator.game.screens.BlackScreen
import com.nikelodeon.coursecalculator.game.screens.CourseCalculatorScreen
import com.nikelodeon.coursecalculator.util.cancelCoroutinesAll
import com.nikelodeon.coursecalculator.util.log
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
            val answer = GameDataStoreManager.Answer.get() ?: false

            activityMain.lottie.hideLoader()
            navigationManager.navigate(if (answer) BlackScreen(activityMain) else CourseCalculatorScreen(activityMain))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
    }


}