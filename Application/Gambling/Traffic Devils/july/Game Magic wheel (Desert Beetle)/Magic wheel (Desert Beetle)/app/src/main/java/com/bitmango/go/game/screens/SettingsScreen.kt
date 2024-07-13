package com.bitmango.go.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.bitmango.go.game.LibGDXGame
import com.bitmango.go.game.actors.AButton
import com.bitmango.go.game.actors.HorizontalGroup
import com.bitmango.go.game.actors.checkbox.ACheckBox
import com.bitmango.go.game.utils.TIME_ANIM
import com.bitmango.go.game.utils.actor.animHide
import com.bitmango.go.game.utils.actor.animShow
import com.bitmango.go.game.utils.advanced.AdvancedScreen
import com.bitmango.go.game.utils.advanced.AdvancedStage
import com.bitmango.go.game.utils.region

class SettingsScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val settings = Image(game.bbb.utytrg)
    private val back     = AButton(this, AButton.Static.Type.Back)

    private val horizontalGroup = HorizontalGroup(this, 25f)
    private val scroll          = ScrollPane(horizontalGroup)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.aaa.back.random().region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(settings.apply { setBounds(78f, 231f, 385f, 456f) })
        addPanel()

        addActor(back)
        back.apply {
            setBounds(176f, 90f, 188f, 78f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM) { game.navigationManager.back() } }
        }

        addActor(scroll)
        scroll.setBounds(0f, 749f, 540f, 160f)

        game.bbb.itemsT.onEach {
            horizontalGroup.addActor(Image(it).apply { setSize(128f, 160f) })
        }
    }

    private fun AdvancedStage.addPanel() {
        val musicCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.MUS)
        val soundCB = ACheckBox(this@SettingsScreen, ACheckBox.Static.Type.SOD)
        addActors(musicCB, soundCB)
        musicCB.apply {
            setBounds(145f, 489f, 248f, 97f)
            if (game.musicUtil.music?.isPlaying == false) check(false)
            setOnCheckListener {
                if (it) game.musicUtil.music?.pause() else game.musicUtil.music?.play()
            }
        }
        soundCB.apply {
            setBounds(145f, 349f, 248f, 97f)
            if (game.soundUtil.isPause) check(false)
            setOnCheckListener {
                game.soundUtil.isPause = it
            }
        }
    }

}