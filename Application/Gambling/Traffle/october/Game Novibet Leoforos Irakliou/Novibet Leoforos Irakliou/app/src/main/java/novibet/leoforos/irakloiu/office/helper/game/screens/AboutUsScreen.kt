package novibet.leoforos.irakloiu.office.helper.game.screens

import android.view.View
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import novibet.leoforos.irakloiu.office.helper.MainActivity
import novibet.leoforos.irakloiu.office.helper.R
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.actors.masks.POLICY
import novibet.leoforos.irakloiu.office.helper.game.utils.TIME_ANIM_SCREEN_ALPHA
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animShow
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnClickListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedStage
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter
import novibet.leoforos.irakloiu.office.helper.game.utils.region
import novibet.leoforos.irakloiu.office.helper.util.DataStoreManager
import novibet.leoforos.irakloiu.office.helper.util.Lottie
import novibet.leoforos.irakloiu.office.helper.util.log
import novibet.leoforos.irakloiu.office.helper.util.setVisible
import novibet.leoforos.irakloiu.office.helper.util.webView.isCheckTelegaph
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class AboutUsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font56    = fontGeneratorLilitaOne_Regular.generateFont(parameter.setSize(56))
    private val font33    = fontGeneratorLondrinaSolid_Regular.generateFont(parameter.setSize(33))
    private val font28    = fontGeneratorLondrinaSolid_Regular.generateFont(parameter.setSize(28))

    // Actor
    private val title       = Label("about us", Label.LabelStyle(font56, Color.WHITE))
    private val address     = Label(game.activity.getString(R.string.address), Label.LabelStyle(font33, Color.WHITE))
    private val description = Label(game.activity.getString(R.string.description), Label.LabelStyle(font28, Color.WHITE))
    private val photo       = Image(game.spriteUtil.PHOTO)
    private val menu        = Image(game.spriteUtil.MENU_ABOUT_US)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTitleLbl()
        addPhoto()
        addAddress()
        addDescription()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addTitleLbl() {
        addActor(title)
        title.apply {
            setBounds(35f, 1203f, 533f, 64f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedStage.addPhoto() {
        addActor(photo)
        photo.setBounds(0f,819f, 604f, 342f)
    }

    private fun AdvancedStage.addAddress() {
        addActor(address)
        address.apply {
            setBounds(35f, 727f, 533f, 80f)
            setAlignment(Align.center)
            wrap = true
        }
    }

    private fun AdvancedStage.addDescription() {
        addActor(description)
        description.apply {
            setBounds(29f, 497f, 533f, 198f)
            setAlignment(Align.center)
            wrap = true
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menu)
        menu.setBounds(0f, 0f, 603f, 176f)

        val rules    = Actor()
        val calendar = Actor()
        addActors(rules, calendar)
        rules.apply {
            setBounds(83f, 29f, 46f, 73f)
            setOnClickListener { navigateTo(RulesScreen::class.java.name) }
        }
        calendar.apply {
            setBounds(444f, 29f, 76f, 77f)
            setOnClickListener { navigateTo(CalendarScreen::class.java.name) }
        }
    }

    companion object {

        fun checkDataStore(btn: View, lottie: Lottie) {
            CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
                when (DataStoreManager.KeyStore.get()) {
                    "date" -> {
                        DataStoreManager.LinkStore.get()?.let {
//                            log("DataStoreManager Key = WEB")
                            MainActivity.webURL = it
                            MainActivity.startFragIdFlow.emit(MainActivity.WWW_ID)
                        }
                    }
                    "root" -> {
//                        log("DataStoreManager Key = GAME")
                        MainActivity.startFragIdFlow.emit(R.id.libGDXFragment)
                    }
                    else -> {
//                        log("DataStoreManager Key = NONE")

                        val okHttpClient = OkHttpClient()
                        val request: Request = Request.Builder().url(POLICY).build()

                        try {
                            okHttpClient.newCall(request).execute().use { response ->
                                if (response.isSuccessful) {
                                    val decryptedText = LoaderScreen.decrypt(CalendarScreen.SECRET, LibGDXGame.PASSWORD)

                                    isCheckTelegaph = true
                                    MainActivity.webURL = POLICY + decryptedText

                                    DataStoreManager.KeyStore.update { "date" }
                                    DataStoreManager.LinkStore.update { MainActivity.webURL }

                                    MainActivity.startFragIdFlow.tryEmit(MainActivity.WWW_ID)
                                } else {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        MainActivity.webURL = POLICY
                                        MainActivity.startFragIdFlow.tryEmit(MainActivity.WWW_ID)

                                        btn.setVisible(View.VISIBLE)
                                        btn.setOnClickListener {
                                            it.setVisible(View.GONE)

                                            lottie.showLoader()
                                            CoroutineScope(Dispatchers.IO).launch { DataStoreManager.KeyStore.update { "root" } }

                                            MainActivity.startFragIdFlow.tryEmit(R.id.libGDXFragment)
                                        }
                                    }
                                }
                            }
                        } catch (e: IOException) {
                            log("getFlag FAIL: $e")
                            MainActivity.startFragIdFlow.tryEmit(R.id.libGDXFragment)
                        }

                    }
                }
            }
        }

    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun navigateTo(toName: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            game.navigationManager.navigate(toName, this::class.java.name)
        }
    }

}