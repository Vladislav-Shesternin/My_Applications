package gsss.prog.rm.com.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import gsss.prog.rm.com.MainActivity
import gsss.prog.rm.com.databinding.FragmentGameBinding
import gsss.prog.rm.com.game.manager.NavigationManager
import gsss.prog.rm.com.game.screens.BanksScreen
import gsss.prog.rm.com.util.cancelCoroutinesAll
import gsss.prog.rm.com.util.network.NetworkUtil
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

        navigationManager = NavigationManager(requireActivity(), binding.rootLayout)
        navigationManager.navigate(BanksScreen(requireActivity()))

        MainActivity.lottie.hideLoader()

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