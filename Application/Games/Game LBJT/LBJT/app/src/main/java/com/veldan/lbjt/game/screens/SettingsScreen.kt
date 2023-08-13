package com.veldan.lbjt.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.veldan.lbjt.R
import com.veldan.lbjt.game.LibGDXGame
import com.veldan.lbjt.game.actors.label.AutoLanguageSpinningLabel
import com.veldan.lbjt.game.box2d.bodies.BLanguage
import com.veldan.lbjt.game.box2d.bodiesGroup.BGBorders
import com.veldan.lbjt.game.box2d.bodiesGroup.BGFrameLanguage
import com.veldan.lbjt.game.box2d.bodiesGroup.BGLanguage
import com.veldan.lbjt.game.box2d.bodiesGroup.BGVolume
import com.veldan.lbjt.game.box2d.disposeAll
import com.veldan.lbjt.game.box2d.util.CheckGroupBGLanguage
import com.veldan.lbjt.game.manager.AudioManager
import com.veldan.lbjt.game.utils.GameColor
import com.veldan.lbjt.game.utils.LanguageUtil
import com.veldan.lbjt.game.utils.TIME_ANIM_ALPHA
import com.veldan.lbjt.game.utils.actor.animHide
import com.veldan.lbjt.game.utils.actor.animShow
import com.veldan.lbjt.game.utils.advanced.AdvancedMouseScreen
import com.veldan.lbjt.game.utils.advanced.AdvancedStage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.roundToInt

class SettingsScreen(override val game: LibGDXGame): AdvancedMouseScreen() {

    companion object {
        var percentVolumeMusic = -1
        var percentVolumeSound = -1
    }

    // Actor
    private val aLanguageLbl by lazy { AutoLanguageSpinningLabel(this, R.string.language, Label.LabelStyle(game.fontTTFUtil.fontInterExtraBold.font_50.font, GameColor.textGreen)) }

    // BodyGroup
    private val bgBorders       = BGBorders(this)
    private val bgVolumeMusic   = BGVolume(this, R.string.music, if (percentVolumeMusic != -1) percentVolumeMusic else AudioManager.volumeLevelPercent.roundToInt())
    private val bgVolumeSound   = BGVolume(this, R.string.sound, if (percentVolumeSound != -1) percentVolumeSound else AudioManager.volumeLevelPercent.roundToInt())
    private val bgFrameLanguage = BGFrameLanguage(this)
    private val bgLanguageEN    = BGLanguage(this, BLanguage.Type.EN)
    private val bgLanguageUK    = BGLanguage(this, BLanguage.Type.UK)

    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.themeUtil.trc.BACKGROUND)
        super.show()
        stageUI.root.animShow(TIME_ANIM_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createBG_Volume()
        addLanguageLbl()
        createBG_FrameLanguage()
        createBG_Language {
            initialize()
            asyncCollectPercentVolumeFlow()
        }
    }

    override fun dispose() {
        listOf(
            bgBorders, bgVolumeMusic, bgVolumeSound,
            bgFrameLanguage, bgLanguageEN, bgLanguageUK,
        ).disposeAll()
        super.dispose()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLanguageLbl() {
        addActor(aLanguageLbl)
        aLanguageLbl.setBounds(10f, 559f, 337f, 65f)
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders(block: () -> Unit = {}) {
        bgBorders.requestToCreate(Vector2(0f, 0f), Vector2(700f, 1400f), block)
    }

    private fun createBG_Volume(block: () -> Unit = {}) {
        bgVolumeMusic.requestToCreate(Vector2(30f, 691f), Vector2(314f, 630f))
        bgVolumeSound.requestToCreate(Vector2(357f, 691f), Vector2(314f, 630f), block)
    }

    private fun createBG_FrameLanguage(block: () -> Unit = {}) {
        bgFrameLanguage.requestToCreate(Vector2(357f, 540f), Vector2(314f, 102f), block)
    }

    private fun createBG_Language(block: () -> Unit = {}) {
        val checkGroupBGLanguage = CheckGroupBGLanguage()

        bgLanguageEN.apply {
            this.checkGroupBGLanguage = checkGroupBGLanguage
            setOnCheckBlock { if (it) LanguageUtil.localeFlow.value = Locale("en") }
            requestToCreate(Vector2(413f, 0f), Vector2(202f, 498f))
        }
        bgLanguageUK.apply {
            this.checkGroupBGLanguage = checkGroupBGLanguage
            setOnCheckBlock { if (it) LanguageUtil.localeFlow.value = Locale("uk") }
            requestToCreate(Vector2(86f, 0f), Vector2(202f, 498f), block)
        }

        when(LanguageUtil.localeFlow.value.language) {
            "en" -> {
                checkGroupBGLanguage.currentCheckedBGLanguage = bgLanguageEN
                bgLanguageEN.check()
                bgLanguageUK.uncheck()
            }
            "uk" -> {
                checkGroupBGLanguage.currentCheckedBGLanguage = bgLanguageUK
                bgLanguageUK.check()
                bgLanguageEN.uncheck()
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun asyncCollectPercentVolumeFlow() {
        coroutine?.launch(Dispatchers.Default) {
            launch { bgVolumeMusic.percentVolumeFlow.collect { percent ->
                percentVolumeMusic = percent
                game.musicUtil.volumeLevelFlow.value = percent.toFloat()
            } }
            launch { bgVolumeSound.percentVolumeFlow.collect { percent ->
                percentVolumeSound = percent
                game.soundUtil.volumeLevel = percent / 100f
            } }
        }
    }

}