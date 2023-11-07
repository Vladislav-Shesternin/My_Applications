package novibet.leoforos.irakloiu.office.helper.game.screens

import android.content.pm.ActivityInfo
import androidx.constraintlayout.widget.ConstraintSet
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import novibet.leoforos.irakloiu.office.helper.MainActivity
import novibet.leoforos.irakloiu.office.helper.R
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.utils.TIME_ANIM_SCREEN_ALPHA
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animShow
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnClickListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedStage
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter
import novibet.leoforos.irakloiu.office.helper.game.utils.region

class RulesScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font56    = fontGeneratorLilitaOne_Regular.generateFont(parameter.setSize(56))
    private val font23    = fontGeneratorLilitaOne_Regular.generateFont(parameter.setSize(23))

    // Actor
    private val title  = Label("rules", Label.LabelStyle(font56, Color.WHITE))
    private val text   = Label(game.activity.getString(R.string.rules_text), Label.LabelStyle(font23, Color.WHITE)).apply {
        setAlignment(Align.center)
        wrap = true
    }
    private val scroll = ScrollPane(text)
    private val menu   = Image(game.spriteUtil.MENU_RULES)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTitleLbl()
        addScroll()
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

    private fun AdvancedStage.addScroll() {
        addActor(scroll)
        scroll.apply {
            setBounds(35f, 120f, 533f, 1041f)
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menu)
        menu.setBounds(0f, 0f, 603f, 176f)

        val aboutUs  = Actor()
        val calendar = Actor()
        addActors(aboutUs, calendar)
        aboutUs.apply {
            setBounds(265f, 29f, 75f, 76f)
            setOnClickListener { navigateTo(AboutUsScreen::class.java.name) }
        }
        calendar.apply {
            setBounds(444f, 29f, 76f, 77f)
            setOnClickListener { navigateTo(CalendarScreen::class.java.name) }
        }
    }

    class Headline(val activity: MainActivity) {
        fun launchStartFragmentId() {
            CoroutineScope(Dispatchers.Main).launch(Dispatchers.Main) {
                MainActivity.startFragIdFlow.collect { fragmentId ->
                    when (fragmentId) {
                        R.id.libGDXFragment      -> {
                            activity.binding.root.also { rootCL ->
                                ConstraintSet().apply {
                                    clone(rootCL)
                                    setVerticalBias(activity.binding.loader.id, .9f)
//                                    constrainPercentWidth(binding.loader.id, .4f)
//                                    constrainPercentWidth(binding.internet.id, .22f)
                                }.applyTo(rootCL)
                            }

                            activity.webViewFragment.goneWebView()
                            activity.setNavigationBarColor(R.color.background)
                            activity.setStartDestination(fragmentId)
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }

                        MainActivity.WWW_ID -> {
                            activity.webViewFragment.showAndOpenUrl()
                            activity.setNavigationBarColor(R.color.white)
                            ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                        }

                        else                     -> ActivityInfo.SCREEN_ORIENTATION_FULL_USER
                    }.also { activity.requestedOrientation = it }
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