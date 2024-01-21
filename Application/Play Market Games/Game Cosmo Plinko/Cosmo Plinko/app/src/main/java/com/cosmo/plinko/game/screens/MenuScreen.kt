package com.cosmo.plinko.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.cosmo.plinko.game.LibGDXGame
import com.cosmo.plinko.game.utils.TIME_ANIM_SCREEN_ALPHA
import com.cosmo.plinko.game.utils.actor.animHide
import com.cosmo.plinko.game.utils.actor.setBounds
import com.cosmo.plinko.game.utils.advanced.AdvancedScreen
import com.cosmo.plinko.game.utils.advanced.AdvancedStage
import com.cosmo.plinko.game.utils.font.FontParameter
import com.cosmo.plinko.game.utils.font.FontParameter.CharType
import com.cosmo.plinko.game.actors.AKomete
import com.cosmo.plinko.game.actors.AMusor
import com.cosmo.plinko.game.actors.button.AButtonTextDef
import com.cosmo.plinko.game.utils.actor.animShow
import com.cosmo.plinko.game.utils.region
import java.util.Random
import com.cosmo.plinko.game.utils.Layout.Splash as LS

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameter = FontParameter()


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPalanet()
        addKomete()
        addMusor()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPalanet() {
        val palanet = Image(game.splashAssets.PALANET)
        addActor(palanet)
        palanet.setBounds(LS.palanet)
    }

    private fun AdvancedStage.addKomete() {
        val palanet = AKomete(this@MenuScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMusor() {
        val palanet = AMusor(this@MenuScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMenu() {
        val menu = Image(game.gameAssets.MENU)
        addActor(menu)
        menu.setBounds(126f, 310f, 828f, 1298f)

        val texts = listOf(
            "PLAY",
            "LEVEL",
            "RULES",
            "SETTINGS",
            "EXIT",
        )

        val texts2 = listOf(
            if (Random().nextBoolean()) GameScreen_1::class.java.name else GameScreen_2::class.java.name,
            LevelScreen::class.java.name,
            RulesScreen::class.java.name,
            SettingsScreen::class.java.name,
            "EXIT",
        )

        val ls   = Label.LabelStyle(fontGeneratorMachineGunk.generateFont(parameter.setCharacters(CharType.LATIN).setSize(98)), Color.WHITE)

        var ny = 1198f

        repeat(5) {
            val btn = AButtonTextDef(this@MenuScreen, texts[it], ls)
            addActor(btn)
            btn.setBounds(281f, ny, 519f, 152f)
            ny -= 186f

            btn.setOnClickListener { navigateGo(texts2[it]) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (id=="EXIT") game.navigationManager.exit()
            else game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}