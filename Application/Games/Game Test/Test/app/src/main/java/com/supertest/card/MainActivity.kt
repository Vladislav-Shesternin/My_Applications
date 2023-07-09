package com.supertest.card

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.supertest.card.databinding.ActivityMainBinding
import com.supertest.card.util.AppsflyerUtil
import com.supertest.card.game.utils.Completer
import com.supertest.card.util.FacebookUtil
import com.supertest.card.util.Lottie
import com.supertest.card.util.cancelCoroutinesAll
import com.supertest.card.util.log
import com.supertest.card.util.network.haveNetworkConnection
import com.supertest.card.webView.webViewFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        lateinit var binding      : ActivityMainBinding
        lateinit var navController: NavController

        val startFragmentIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webViewURL = "google.com"

        val lottie    by lazy { Lottie(binding) }
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        log("Network = ${haveNetworkConnection()}")
        if (haveNetworkConnection().not()) lottie.showNotNetwork()
        else {
            lottie.showLoader()
            coroutine.launch {
                launch(Dispatchers.Main) {
                    startFragmentIdFlow.collect { fragmentId ->
                        requestedOrientation = when (fragmentId) {
                            R.id.gameFragment -> {
                                binding.root.also { rootCL ->
                                    ConstraintSet().apply {
                                        clone(rootCL)
                                        constrainPercentWidth(binding.lottieLoader.id, .2f)
                                    }.applyTo(rootCL)
                                }
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                            }
                            R.id.webViewFragment -> {
                                window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.black)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }
                            else                 -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        setStartDestination(fragmentId)
                    }
                }

                withContext(Dispatchers.IO) {
                    getRemoteData()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exit()
    }

    override fun exit() {
        cancelCoroutinesAll(coroutine)
        finishAndRemoveTask()
        exitProcess(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        webViewFragment?.onActivityResult(requestCode, resultCode, data)
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
    }

    private fun setStartDestination(
        @IdRes destinationId: Int,
        args: Bundle? = null
    ) {
        with(navController) {
            navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, args) }
        }
    }

    private suspend fun getRemoteData() {
        // для теста инициализирую тут
        AppsflyerUtil.initialize(this)

        Firebase.firestore.collection("data")
            .get()
            .addOnSuccessListener { snapshot ->
                val flag = snapshot.documents.first().data?.get("flag").toString()
                val url  = snapshot.documents.first().data?.get("url").toString()

                log("Firestore Success:")
                log("flag = $flag")
                log("url  = $url")

                if (flag == "1") {
                    val completer = Completer(coroutine, 2) {
                        webViewURL = url
                        startFragmentIdFlow.tryEmit(R.id.webViewFragment)
                    }

                    // тут должен инициализироваться аппсфлаер по нормальному но так как нам нужно получить его ответ в игре по нажатии на кнопку то  инициализация то получения ответа с сервера (я о флаге 0-1)
                    // AppsflyerUtil.initialize(this)
                    FacebookUtil.initialize()

                    coroutine.launch { AppsflyerUtil.conversionFlow.collect {
                        it?.let { completer.complete() }
                    } }
                    coroutine.launch { FacebookUtil.deeplinkFlow.collect {
                        if (it != FacebookUtil.DEEPLINK_INIT_VALUE) {
                            log("Deeplink = $it")
                            completer.complete()
                        }
                    } }
                } else startFragmentIdFlow.tryEmit(R.id.gameFragment)
            }
            .addOnFailureListener {
                log("Firestore Failure: ${it.message}")
                startFragmentIdFlow.tryEmit(R.id.gameFragment)
            }
    }

}