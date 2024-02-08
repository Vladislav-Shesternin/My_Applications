package tigerfortune.lucky.game.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import tigerfortune.lucky.game.game.LibGDXGame
import tigerfortune.lucky.game.game.actors.button.AButton
import tigerfortune.lucky.game.game.actors.checkbox.ACheckBox
import tigerfortune.lucky.game.game.utils.Completer
import tigerfortune.lucky.game.game.utils.TIME_ANIM
import tigerfortune.lucky.game.game.utils.actor.animHide
import tigerfortune.lucky.game.game.utils.actor.animShow
import tigerfortune.lucky.game.game.utils.actor.animShow_Suspend
import tigerfortune.lucky.game.game.utils.actor.setOnClickListener
import tigerfortune.lucky.game.game.utils.advanced.AdvancedScreen
import tigerfortune.lucky.game.game.utils.advanced.AdvancedStage
import tigerfortune.lucky.game.game.utils.region
import tigerfortune.lucky.game.game.utils.runGDX
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

import tigerfortune.lucky.game.game.actors.button.AButton.Static.Type as BtnType
import tigerfortune.lucky.game.game.actors.checkbox.ACheckBox.Static.Type as BoxType

class YellowRulesingScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val panel  = Image(game.allAssets.YcupleStars)
    private val music  = ACheckBox(this, BoxType.MUSIC)
    private val back   = AButton(this, BtnType.BACK)
    private val rules  = Image(game.allAssets.yPanelRules)
    private val tiger  = Image(game.allAssets.yCircleTiger)
    private val dialog = Image(game.allAssets.yDialogTiger)

    override fun show() {
        stageUI.root.color.a = 0f
        setUIBackground(game.allAssets.YellowMain.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        stageUI.root.animShow(TIME_ANIM) {
            runGDX {
                addBack()
                addMusic()
                addPanel()
                addRules()
                addTiger()
                addDialog()
            }
            coroutine?.launch {
                animBackAndMusic(0.5f)
                panel.animShow_Suspend(0.5f)
                animRules(0.3f)
                tiger.animShow_Suspend(0.5f)
                delay(200)
                dialog.animShow_Suspend(0.3f)
            }
        }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        addActor(back)
        back.apply {
            setBounds(21f, 1920f, 169f, 169f)
            setOnClickListener(game.soundUtil) { stageUI.root.animHide(TIME_ANIM) {
                game.navigationManager.back()
            } }
        }
    }

    private fun AdvancedStage.addMusic() {
        addActor(music)
        music.setBounds(893f, 1920f, 169f, 169f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addPanel() {
        panel.color.a = 0f
        addActor(panel)
        panel.setBounds(69f, 126f, 968f, 956f)
    }

    private fun AdvancedStage.addRules() {
        addActor(rules)
        rules.setBounds(322f, 1920f, 459f, 172f)
    }

    private fun AdvancedStage.addTiger() {
        tiger.color.a = 0f
        addActor(tiger)
        tiger.setBounds(28f, 1357f, 392f, 392f)

        tiger.setOnClickListener(game.soundUtil) {
            if (dialog.color.a == 0f) dialog.animShow(TIME_ANIM) else dialog.animHide(TIME_ANIM)
        }
    }

    private fun AdvancedStage.addDialog() {
        dialog.color.a = 0f
        addActor(dialog)
        dialog.setBounds(187f, 1059f, 710f, 427f)
    }

    // ------------------------------------------------------------------------
    // Anim
    // ------------------------------------------------------------------------

    private suspend fun animBackAndMusic(duration: Float) = CompletableDeferred<Unit>().also { continuation ->
        val completer = Completer(coroutine,2) { continuation.complete(Unit) }

        runGDX {
            back.addAction(Actions.sequence(
                Actions.moveTo(21f, 1731f, duration, Interpolation.swingOut),
                Actions.run { completer.complete() }
            ))
            music.addAction(Actions.sequence(
                Actions.moveTo(893f, 1731f, duration, Interpolation.swingOut),
                Actions.run { completer.complete() }
            ))
        }

    }.await()

    private suspend fun animRules(duration: Float) = suspendCoroutine<Unit> { continuation ->
        runGDX {
            rules.addAction(Actions.sequence(
                Actions.moveTo(322f, 1723f, duration, Interpolation.swingOut),
                Actions.run { continuation.resume(Unit) }
            ))
        }
    }


}