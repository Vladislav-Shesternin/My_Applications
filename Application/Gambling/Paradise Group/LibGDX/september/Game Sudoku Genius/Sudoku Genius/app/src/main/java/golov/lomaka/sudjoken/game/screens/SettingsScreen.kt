package golov.lomaka.sudjoken.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import golov.lomaka.sudjoken.R
import golov.lomaka.sudjoken.game.LibGDXGame
import golov.lomaka.sudjoken.game.utils.actor.animHiden
import golov.lomaka.sudjoken.game.utils.actor.animShown
import golov.lomaka.sudjoken.game.utils.actor.setOnClickListener
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedScreen
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedStage

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHiden()
        setUIBackground(game.spriteUtil.GAME.SETTINGS_BACKGROUND)
        super.show()
        stageUI.root.animShown(0.3f)
        game.activity.setNavigationBarColor(R.color.white)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val actorw = Actor()
        addActor(actorw)
        actorw.apply {
            setBounds(30f, 1159f, 76f, 76f)
            setOnClickListener {stageUI.root.animHiden(0.3f) { game.navigationManager.back() } }
        }

        val actorwq = Actor()
        addActor(actorwq)
        actorwq.apply {
            setBounds(27f, 26f, 551f, 933f)
            setOnClickListener { stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(ThemeScreen(game), SettingsScreen(game)) } }
        }

    }

}