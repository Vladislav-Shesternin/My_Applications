package com.kurs.mon.fin.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kurs.mon.fin.MainActivity
import com.kurs.mon.fin.databinding.FragmentGameBinding
import com.kurs.mon.fin.game.manager.NavigationManager
import com.kurs.mon.fin.game.screens.ExchangerScreen
import com.kurs.mon.fin.util.cancelCoroutinesAll
import com.kurs.mon.fin.util.log
import com.kurs.mon.fin.util.network.NetworkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
lateinit var navigationManager: NavigationManager private set
//lateinit var timerUtil: TimerUtil private set

class GameFragment: Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val coroutine = CoroutineScope(Dispatchers.Default)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        BalanceUtil.initialize {
//            timerUtil = TimerUtil()

            coroutine.launch(Dispatchers.IO) {
                ExchangerScreen.currenciesMap = NetworkUtil.service.getCurrencies().kzt


                withContext(Dispatchers.Main) {
                    navigationManager = NavigationManager(requireActivity(), binding.rootLayout)
                    navigationManager.navigate(ExchangerScreen(requireActivity()))

                    MainActivity.lottie.hideLoader()
                }
            }

     //       timerUtil.initialize(coroutine)
     //   }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelCoroutinesAll(coroutine)
        navigationManager.dispose()
      //  BalanceUtil.dispose()
    }


}