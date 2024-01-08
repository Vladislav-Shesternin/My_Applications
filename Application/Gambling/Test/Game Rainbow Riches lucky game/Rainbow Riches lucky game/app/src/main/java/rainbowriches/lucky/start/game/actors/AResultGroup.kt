package rainbowriches.lucky.start.game.actors

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import rainbowriches.lucky.start.game.actors.button.AButton
import rainbowriches.lucky.start.game.actors.panel.LEVEL
import rainbowriches.lucky.start.game.actors.panel.Level
import rainbowriches.lucky.start.game.screens.EasyNormalHardScreen
import rainbowriches.lucky.start.game.utils.actor.setOnClickListener
import rainbowriches.lucky.start.game.utils.advanced.AdvancedGroup
import rainbowriches.lucky.start.game.utils.advanced.AdvancedScreen

var isWINNER = true
class AResultGroup(override val screen: AdvancedScreen): AdvancedGroup() {

    private val menuBtn = AButton(screen, AButton.Static.Type.MENU)
    private val result  = Image()
    private val btns    = listOf(
        Image(screen.game.gameAssets.L_EASY),
        Image(screen.game.gameAssets.L_NORMAL),
        Image(screen.game.gameAssets.L_HARD),
    )

    override fun addActorsOnGroup() {
        addAndFillActor(Image(screen.game.gameAssets.WIN_LOSE_BLOOR))
        addMenuBtn()
        addResult()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun addMenuBtn() {
        addActor(menuBtn)
        menuBtn.apply {
            setBounds(70f, 1684f, 182f, 193f)
            setOnClickListener { screen.animHideScreen { screen.game.navigationManager.back() } }
        }
    }

    private fun addResult() {
        addActor(result)
        result.setBounds(22f, 1101f, 1036f, 401f)
    }

    private fun addBtns() {
        val yyy = listOf(782f, 513f, 245f)
        btns.onEachIndexed { index, btn ->
            addActor(btn)
            btn.setBounds(323f, yyy[index], 435f, 178f)
            btn.setOnClickListener(screen.game.soundUtil) {
                Level = when(index) {
                    0 -> LEVEL.EASY
                    1 -> LEVEL.NORMAL
                    2 -> LEVEL.HARD
                    else -> LEVEL.EASY
                }
                screen.animHideScreen { screen.game.navigationManager.navigate(EasyNormalHardScreen::class.java.name) }
            }
        }
    }

    fun lila() {
        result.drawable = TextureRegionDrawable(if (isWINNER) screen.game.gameAssets.YOU_WIN else screen.game.gameAssets.YOU_LOSE)
    }

}