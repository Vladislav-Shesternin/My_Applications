package com.legojum.kangarooper.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.legojum.kangarooper.game.LibGDXGame
import com.legojum.kangarooper.game.manager.MusicManager
import com.legojum.kangarooper.game.manager.ParticleEffectManager
import com.legojum.kangarooper.game.manager.SoundManager
import com.legojum.kangarooper.game.manager.SpriteManager
import com.legojum.kangarooper.game.utils.TIME_ANIM
import com.legojum.kangarooper.game.utils.actor.animHide
import com.legojum.kangarooper.game.utils.actor.animShow
import com.legojum.kangarooper.game.utils.actor.setOnClickListener
import com.legojum.kangarooper.game.utils.advanced.AdvancedScreen
import com.legojum.kangarooper.game.utils.advanced.AdvancedStage
import com.legojum.kangarooper.game.utils.font.FontParameter
import com.legojum.kangarooper.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class KangarooMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var RECORD = 0
    }

    private val params = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(65)
    private val font   = fontGenerator_AvertaDemoPE.generateFont(params)

    private val coupImg = Image(game.allAssets.coup)
    private val label   = Label("Record: ${RECORD}m", Label.LabelStyle(font, Color.valueOf("201DC0")))
    private val playImg = Image(game.allAssets.play)

    override fun show() {
        stageUI.root.animHide()
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addCoupImg()
        addLabel()
        addPlayImg()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addCoupImg() {
        addActor(coupImg)
        coupImg.setBounds(242f, 1101f, 596f, 675f)
    }

    private fun AdvancedStage.addLabel() {
        addActor(label)
        label.setBounds(338f, 1118f, 407f, 97f)
        label.setAlignment(Align.center)
    }

    private fun AdvancedStage.addPlayImg() {
        addActor(playImg)
        playImg.setBounds(337f, 414f, 406f, 402f)
        playImg.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM) {
                game.navigationManager.navigate(KangarooGameScreen::class.java.name, KangarooMenuScreen::class.java.name)
            }
        }
    }

}