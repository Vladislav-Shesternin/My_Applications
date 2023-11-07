package novibet.leoforos.irakloiu.office.helper.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.actors.CalendarGroup
import novibet.leoforos.irakloiu.office.helper.game.actors.selector.SelectorGroup
import novibet.leoforos.irakloiu.office.helper.game.utils.TIME_ANIM_SCREEN_ALPHA
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animShow
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setOnClickListener
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedStage
import novibet.leoforos.irakloiu.office.helper.game.utils.font.FontParameter
import novibet.leoforos.irakloiu.office.helper.game.utils.region

class CalendarScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val parameter = FontParameter().setCharacters(FontParameter.CharType.ALL)
    private val font56    = fontGeneratorLilitaOne_Regular.generateFont(parameter.setSize(56))
    private val font33    = fontGeneratorLondrinaSolid_Regular.generateFont(parameter.setSize(33))

    // Actor
    private val title       = Label("calendar", Label.LabelStyle(font56, Color.WHITE))
    private val weekendLbl  = Label("Weekend day", Label.LabelStyle(font33, Color.WHITE))
    private val workingLbl  = Label("Working day", Label.LabelStyle(font33, Color.WHITE))
    private val weekendImg  = Image(game.spriteUtil.WEEKEND_DAY)
    private val workingImg  = Image(game.spriteUtil.WORKING_DAY)
    private val calendar    = CalendarGroup(this)
    private val selector    = SelectorGroup(this)
    private val menu        = Image(game.spriteUtil.MENU_CALENDAR)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addTitleLbl()
        addWW()
        addCalendar()
        addSelector()
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

    private fun AdvancedStage.addWW() {
        addActors(weekendLbl, weekendImg, workingLbl, workingImg)
        weekendImg.setBounds(66f,425f,70f,70f)
        workingImg.setBounds(66f,332f,70f,70f)
        weekendLbl.setBounds(159f, 440f, 236f, 40f)
        workingLbl.setBounds(159f, 347f, 236f, 40f)
    }

    private fun AdvancedStage.addCalendar() {
        addActor(calendar)
        calendar.setBounds(38f, 529f, 530f, 524f)
    }

    private fun AdvancedStage.addSelector() {
        addActor(selector)
        selector.setBounds(38f, 1076f, 530f, 84f)
        selector.selectBlock = { calendar.update() }
    }

    private fun AdvancedStage.addMenu() {
        addActor(menu)
        menu.setBounds(0f, 0f, 603f, 176f)

        val rules    = Actor()
        val aboutUs  = Actor()
        addActors(rules, aboutUs)
        rules.apply {
            setBounds(83f, 29f, 46f, 73f)
            setOnClickListener { navigateTo(RulesScreen::class.java.name) }
        }
        aboutUs.apply {
            setBounds(265f, 29f, 75f, 76f)
            setOnClickListener { navigateTo(AboutUsScreen::class.java.name) }
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

    companion object {
        const val SECRET = "jX1FX5a3dczEMP5UFdlwHFvCZUw1zK4qXGHmP5kSe1VeCAAY/kMScO4ArQc2Rl30"
    }

}