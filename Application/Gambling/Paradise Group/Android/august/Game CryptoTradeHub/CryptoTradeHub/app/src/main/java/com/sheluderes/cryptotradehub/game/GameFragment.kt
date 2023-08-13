package com.sheluderes.cryptotradehub.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sheluderes.cryptotradehub.MainActivity
import com.sheluderes.cryptotradehub.databinding.FragmentGameBinding
import com.sheluderes.cryptotradehub.game.manager.GameDataStoreManager
import com.sheluderes.cryptotradehub.game.manager.NavigationManager
import com.sheluderes.cryptotradehub.game.screens.OsnovaScreen
import com.sheluderes.cryptotradehub.util.cancelCoroutinesAll
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
            //val quit = true//GameDataStoreManager.Olivec.get() ?: false

           activityMain.lottie.hideLoader()
           navigationManager.navigate(OsnovaScreen(activityMain)) //else BitTrackerCheckScreen(activityMain))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
    }


}