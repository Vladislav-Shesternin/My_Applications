package golov.lomaka.sudjoken.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import golov.lomaka.sudjoken.R
import golov.lomaka.sudjoken.game.ACheckBox
import golov.lomaka.sudjoken.game.ACheckBoxGroup
import golov.lomaka.sudjoken.game.LibGDXGame
import golov.lomaka.sudjoken.game.utils.actor.animHiden
import golov.lomaka.sudjoken.game.utils.actor.animShown
import golov.lomaka.sudjoken.game.utils.actor.setOnClickListener
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedScreen
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedStage

class ThemeScreen(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHiden()
        setUIBackground(game.spriteUtil.GAME.THEME_BACKGROUND)
        super.show()
        stageUI.root.animShown(0.3f)
        game.activity.setNavigationBarColor(R.color.white)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val actorw = Actor()
        addActor(actorw)
        actorw.apply {
            setBounds(8f, 1231f, 76f, 76f)
            setOnClickListener {stageUI.root.animHiden(0.3f) { game.navigationManager.back() } }
        }

        val vfgf = ACheckBoxGroup()
        val black = ACheckBox(this@ThemeScreen, ACheckBox.Type.DEF)
        addActor(black)
        black.apply {
            setBounds(81f, 905f, 157f, 157f)
            setOnCheckListener { if (it) game.isWhite = false }
            checkBoxGroup = vfgf
        }
        val white = ACheckBox(this@ThemeScreen, ACheckBox.Type.DEF)
        addActor(white)
        white.apply {
            setBounds(375f, 244f, 157f, 157f)
            setOnCheckListener { if (it) game.isWhite = true }
            checkBoxGroup = vfgf
        }

        if (game.isWhite) white.check(false) else black.check(false)

    }

}