package com.goplaytoday.guildofhero.game.screens

import com.goplaytoday.guildofhero.game.LGDXGame
import com.goplaytoday.guildofhero.game.actors.button.AButton
import com.goplaytoday.guildofhero.game.actors.checkbox.ACheckBox
import com.goplaytoday.guildofhero.game.utils.TIME_ANIM_ALPHA
import com.goplaytoday.guildofhero.game.utils.actor.animHide
import com.goplaytoday.guildofhero.game.utils.actor.animShow
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedScreen
import com.goplaytoday.guildofhero.game.utils.advanced.AdvancedStage
import com.goplaytoday.guildofhero.game.utils.region

class SettScreen(override val game: LGDXGame): AdvancedScreen() {

    companion object {
        var isPlaySound = true
            private set
    }

    private val cbMusic = ACheckBox(this, ACheckBox.Static.Type.MUSIC)
    private val cbSound = ACheckBox(this, ACheckBox.Static.Type.SOUND)
    private val btnMenu = AButton(this, AButton.Static.Type.Menu)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.loaderAssets.BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMusic()
        addSound()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMusic() {
        addActor(cbMusic)
        cbMusic.apply {
            setBounds(315f, 343f, 315f, 315f)

            if (staticMusic?.isPlaying == true) check(false)

            setOnCheckListener {
                if (it) staticMusic?.play() else staticMusic?.pause()
            }
        }
    }

    private fun AdvancedStage.addSound() {
        addActor(cbSound)
        cbSound.apply {
            setBounds(1051f, 343f, 315f, 315f)

            if (isPlaySound) check(false)

            setOnCheckListener { isPlaySound = it }
        }
    }

    private fun AdvancedStage.addMenu() {
        addActor(btnMenu)
        btnMenu.apply {
            setBounds(751f, 115f, 197f, 91f)
            setOnClickListener { stageUI.root.animHide(TIME_ANIM_ALPHA) { game.navigationManager.back() } }
        }
    }

}