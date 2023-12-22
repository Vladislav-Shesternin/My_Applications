package fball.fteam.fquiz.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import fball.fteam.fquiz.game.LibGDXGame
import fball.fteam.fquiz.game.actors.ACheckBox
import fball.fteam.fquiz.game.actors.ACheckBoxGroup
import fball.fteam.fquiz.game.utils.actor.animHide
import fball.fteam.fquiz.game.utils.actor.animShow
import fball.fteam.fquiz.game.utils.actor.setOnClickListener
import fball.fteam.fquiz.game.utils.advanced.AdvancedScreen
import fball.fteam.fquiz.game.utils.advanced.AdvancedStage
import fball.fteam.fquiz.game.utils.language.Language
import fball.fteam.fquiz.game.utils.region

class LanguageScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val flgsImg = Image(assets.flgs)
    private val beckImg = Image(assets.beck)
    private val enCB    = ACheckBox(this, ACheckBox.Static.Type.LanguageR)
    private val ukCB    = ACheckBox(this, ACheckBox.Static.Type.LanguageR)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.pole.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addFlgs()
        addBeck()
        addCBS()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addFlgs() {
        addActor(flgsImg)
        flgsImg.setBounds(171f, 281f, 1058f, 259f)
    }

    private fun AdvancedStage.addBeck() {
        addActor(beckImg)
        beckImg.setBounds(0f, 625f, 86f, 75f)
        beckImg.setOnClickListener { stageUI.root.animHide(0.3f) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addCBS() {
        addActors(enCB, ukCB)
        val cbg = ACheckBoxGroup()
        enCB.apply {
            setBounds(308f, 115f, 105f, 105f)
            checkBoxGroup = cbg
            setOnCheckListener {
                if (it) game.languageUtil.languageFlow.value = Language.EN
            }
        }
        ukCB.apply {
            setBounds(987f, 115f, 105f, 105f)
            checkBoxGroup = cbg
            setOnCheckListener {
                if (it) game.languageUtil.languageFlow.value = Language.UK
            }
        }

        if (game.languageUtil.languageFlow.value == Language.EN) enCB.check(false) else ukCB.check(false)
    }

}