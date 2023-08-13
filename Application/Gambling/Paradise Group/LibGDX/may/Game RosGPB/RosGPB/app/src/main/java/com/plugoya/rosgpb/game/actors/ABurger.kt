package com.plugoya.rosgpb.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.plugoya.rosgpb.game.actors.progress.AProgress
import com.plugoya.rosgpb.game.manager.FontTTFManager
import com.plugoya.rosgpb.game.manager.NavigationManager
import com.plugoya.rosgpb.game.manager.SpriteManager
import com.plugoya.rosgpb.game.musicUtil
import com.plugoya.rosgpb.game.screens.CardScreen
import com.plugoya.rosgpb.game.screens.NewsScreen
import com.plugoya.rosgpb.game.soundUtil
import com.plugoya.rosgpb.game.utils.actor.setOnClickListener
import com.plugoya.rosgpb.game.utils.advanced.AdvancedGroup
import com.plugoya.rosgpb.game.utils.runGDX
import kotlinx.coroutines.launch

class ABurger : AdvancedGroup() {

    companion object {
        var musicPercent = 100
        var soundPercent = 100
    }

    val burgerImage = Image(SpriteManager.GameRegion.NEWS.region)
    private val newsActor   = Actor()
    private val exitActor   = Actor()
    private val mProgress   = AProgress()
    private val sProgress   = AProgress()
    private val musicText   = Label("0%", Label.LabelStyle(FontTTFManager.RegFont.font_45.font, Color.BLACK))
    private val soundText   = Label("0%", Label.LabelStyle(FontTTFManager.RegFont.font_45.font, Color.BLACK))

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(burgerImage)
            addActirs()
        }
    }

    private fun addActirs() {
        addActors(mProgress, sProgress)
        mProgress.apply {
            setBounds(40f, 277f, 223f, 907f)
            progressPercentFlow.value = musicPercent.toFloat()
        }
        sProgress.apply {
            setBounds(341f, 277f, 223f, 907f)
            progressPercentFlow.value = soundPercent.toFloat()
        }

        addActors(newsActor, exitActor)
        newsActor.apply {
            setBounds(48f, 1329f, 535f, 158f)
            setOnClickListener { NavigationManager.navigate(NewsScreen(), CardScreen()) }
        }
        exitActor.apply {
            setBounds(141f, 94f, 348f, 102f)
            setOnClickListener { NavigationManager.exit() }
        }

        addLabels()

        coroutine.launch {
            launch { mProgress.progressPercentFlow.collect { percent -> runGDX {
                musicText.setText("${percent.toInt()}%")
                musicUtil.volumeLevelFlow.value = percent
                musicPercent = percent.toInt()
            } } }
            launch { sProgress.progressPercentFlow.collect { percent -> runGDX {
                soundText.setText("${percent.toInt()}%")
                soundUtil.volumeLevelPercent = percent
                soundPercent = percent.toInt()
            } } }
        }

    }

    private fun AdvancedGroup.addLabels() {
        addActors(musicText, soundText)
        musicText.apply {
            setBounds(96f, 724f, 110f, 51f)
            setAlignment(Align.center)
        }
        soundText.apply {
            setBounds(409f, 724f, 110f, 51f)
            setAlignment(Align.center)
        }
    }


    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------


}