package com.cosmo.plinko.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.actors.button.AButton
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.animShow
import com.cosmo.plinko.game.utils.actor.setOnClickListener
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.region
import java.util.Random

var levelBallIndex = -1

val leveles = mutableListOf(0,0,0,0,0,0)

class LevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val stars_1 = Image(game.gameAssets.STAR_LIST[leveles[0]])
    private val stars_2 = Image(game.gameAssets.STAR_LIST[leveles[1]])
    private val stars_3 = Image(game.gameAssets.STAR_LIST[leveles[2]])
    private val stars_4 = Image(game.gameAssets.STAR_LIST[leveles[3]])
    private val stars_5 = Image(game.gameAssets.STAR_LIST[leveles[4]])
    private val stars_6 = Image(game.gameAssets.STAR_LIST[leveles[5]])

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region, game.gameAssets.BACGROND_LEVEL.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
        addStars()
        addPlanetsActors()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        val palanet = AButton(this@LevelScreen, AButton.Static.Type.MENU)
        addActor(palanet)
        palanet.setBounds(57f, 1780f, 124f, 106f)
        palanet.setOnClickListener { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addStars() {
        addActors(stars_1, stars_2, stars_3, stars_4, stars_5, stars_6)
        stars_1.apply {
            setBounds(653f, 1331f, 150f, 55f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(32f))
        }
        stars_2.apply {
            setBounds(880f, 727f, 136f, 50f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(20f))
        }
        stars_3.apply {
            setBounds(790f, 56f, 118f, 43f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(-37f))
        }
        stars_4.apply {
            setBounds(449f, 234f, 118f, 43f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(31f))
        }
        stars_5.apply {
            setBounds(208f, 756f, 118f, 43f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(25f))
        }
        stars_6.apply {
            setBounds(567f, 996f, 90f, 33f)
            setOrigin(com.badlogic.gdx.utils.Align.center)
            addAction(Actions.rotateTo(10f))
        }
    }

    private fun AdvancedStage.addPlanetsActors() {
        val p1 = Actor()
        val p2 = Actor()
        val p3 = Actor()
        val p4 = Actor()
        val p5 = Actor()
        val p6 = Actor()
        addActors(p1, p2, p3, p4, p5, p6,)
        p1.apply {
            setBounds(464f, 1328f, 352f, 357f)
            setOnClickListener(game.soundUtil) { navigateGo(0) }
        }
        p2.apply {
            setBounds(716f, 739f, 352f, 357f)
            setOnClickListener(game.soundUtil) { navigateGo(1) }
        }
        p3.apply {
            setBounds(762f, 54f, 306f, 310f)
            setOnClickListener(game.soundUtil) { navigateGo(2) }
        }
        p4.apply {
            setBounds(264f, 220f, 306f, 310f)
            setOnClickListener(game.soundUtil) { navigateGo(3) }
        }
        p5.apply {
            setBounds(62f, 762f, 306f, 310f)
            setOnClickListener(game.soundUtil) { navigateGo(4) }
        }
        p6.apply {
            setBounds(469f, 1003f, 232f, 235f)
            setOnClickListener(game.soundUtil) { navigateGo(5) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(indexxxx: Int) {
        levelBallIndex = indexxxx
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            game.navigationManager.navigate(if (Random().nextBoolean()) GameScreen_1::class.java.name else GameScreen_2::class.java.name, this::class.java.name)
        }
    }


}