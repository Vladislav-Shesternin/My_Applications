package com.hepagame.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.hepagame.game.LibGDXGame
import com.hepagame.game.utils.TIME_ANIM
import com.hepagame.game.utils.actor.animHide
import com.hepagame.game.utils.actor.animShow
import com.hepagame.game.utils.actor.setOnClickListener
import com.hepagame.game.utils.advanced.AdvancedScreen
import com.hepagame.game.utils.advanced.AdvancedStage
import com.hepagame.game.utils.region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DianisScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val dianis = Image(game.assasinAll.dianis)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.assasinLoader.PAGRAN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(dianis.apply { setBounds(407f, 37f, 732f, 820f) })

        val agreeActor    = Actor()
        val disagreeActor = Actor()

        addActors(agreeActor, disagreeActor)

        agreeActor.setBounds(479f, 294f, 234f, 76f)
        disagreeActor.setBounds(832f, 294f, 234f, 76f)

        game.activity.apply {
            blockSave = { game.gamePrefsDialog.edit().putBoolean("lol", true).apply() }
            blockToMenuScreen = { stageUI.root.animHide(TIME_ANIM) { game.navigationManager.navigate(MenuScreen::class.java.name) } }

            agreeActor.setOnClickListener(game.soundUtil) {
                coroutine?.launch(Dispatchers.Main) {
                    usANser = true
                    languster.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }
            disagreeActor.setOnClickListener(game.soundUtil) {
                coroutine?.launch(Dispatchers.Main) {
                    usANser = false
                    languster.launch(arrayOf("android.permission.POST_NOTIFICATIONS"))
                }
            }
        }

        addTreasures()
    }

    private fun AdvancedStage.addTreasures() {
        val left  = Image(game.assasinAll.SS)
        val right = Image(game.assasinAll.DD)

        addActors(left, right)
        left.apply {
            setBounds(57f, 75f, 320f, 309f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(-20f, 0f, 1f),
                Actions.moveBy(50f, 0f, 2f),
                Actions.moveBy(-30f, 0f, 1.5f),
            )))
        }
        right.apply {
            setBounds(1149f, 52f, 347f, 353f)
            addAction(
                Actions.forever(
                    Actions.sequence(
                Actions.moveBy(20f, 0f, 1f),
                Actions.moveBy(-50f, 0f, 2f),
                Actions.moveBy(30f, 0f, 1.5f),
            )))
        }
    }

}