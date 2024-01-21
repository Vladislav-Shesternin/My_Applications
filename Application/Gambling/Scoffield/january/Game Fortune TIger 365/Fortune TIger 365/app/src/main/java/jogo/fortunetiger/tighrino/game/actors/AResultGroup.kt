package jogo.fortunetiger.tighrino.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import jogo.fortunetiger.tighrino.game.screens.TGameScreen
import jogo.fortunetiger.tighrino.game.screens.TMenuScreen
import jogo.fortunetiger.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogo.fortunetiger.tighrino.game.utils.actor.animHide
import jogo.fortunetiger.tighrino.game.utils.actor.setOnClickListener
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedGroup
import jogo.fortunetiger.tighrino.game.utils.advanced.AdvancedScreen
import jogo.fortunetiger.tighrino.game.utils.font.FontParameter

class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars+":").setSize(50)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val imgBackground = Image()
    private val timeLbl       = Label("0", Label.LabelStyle(font, Color.WHITE))

    private var isNext = true

    override fun addActorsOnGroup() {
        touchable = Touchable.disabled
        addAndFillActor(imgBackground)
        addTime()
        addBtns()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addTime() {
        addActor(timeLbl)
        timeLbl.setBounds(555f, 640f, 304f, 85f)
        timeLbl.setAlignment(Align.center)
    }

    private fun addBtns() {
        val restart = Actor()
        val menu    = Actor()
        val next    = Actor()

        addActors(restart, menu, next)

        restart.apply {
            setBounds(199f, 266f, 165f, 171f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(TGameScreen::class.java.name) }
            }
        }
        menu.apply {
            setBounds(435f, 234f, 208f, 216f)
            setOnClickListener(screen.game.soundUtil) {
                screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(TMenuScreen::class.java.name) }
            }
        }
        next.apply {
            setBounds(715f, 266f, 165f, 171f)
            setOnClickListener(screen.game.soundUtil) {
                if (isNext) screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { screen.game.navigationManager.navigate(TGameScreen::class.java.name) }
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(isWin: Boolean, time: String = "0") {
        if (isWin) {
            timeLbl.setText(time)
            isNext = true
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.B_WIN)
            screen.game.soundUtil.apply { play(level_passed) }
        } else {
            timeLbl.setText("0")
            isNext = false
            imgBackground.drawable = TextureRegionDrawable(screen.game.gameAssets.B_FAILE)
            screen.game.soundUtil.apply { play(fail) }
        }
    }

}