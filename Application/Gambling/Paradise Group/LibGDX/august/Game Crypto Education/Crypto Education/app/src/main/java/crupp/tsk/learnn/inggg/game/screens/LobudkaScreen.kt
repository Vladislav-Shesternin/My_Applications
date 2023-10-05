package crupp.tsk.learnn.inggg.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import crupp.tsk.learnn.inggg.game.LibGDXGame
import crupp.tsk.learnn.inggg.game.actor_tools.button.AButton
import crupp.tsk.learnn.inggg.game.actor_tools.checkbox.ACheckBox
import crupp.tsk.learnn.inggg.game.utils.actor.animHide
import crupp.tsk.learnn.inggg.game.utils.actor.setOnClickListener
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedScreen
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedStage

class LobudkaScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val logotypeImg = Image(game.spriteUtil.GAME.LOGOTYPE)
    private val prodoBtn    = AButton(this, AButton.Type.REGULAR)
    private val prodoCB     = ACheckBox(this, ACheckBox.Type.REGULAR)
    private val textesImg   = Image(game.spriteUtil.GAME.TEXTS)


    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        logotypeImg()
        btn()
        cb()
        text()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.logotypeImg() {
        addActors(logotypeImg)
        logotypeImg.apply {
            setBounds(34f, 856f, 555f, 404f)
            addAction(Actions.moveTo(34f,508f, 0.5f, Interpolation.swingOut))
        }
    }

    private fun AdvancedStage.btn() {
        addActors(prodoBtn)
        prodoBtn.apply {
            setBounds(34f, 50f, 557f, 91f)
            addAction(Actions.moveTo(34f,88f, 0.5f, Interpolation.swingOut))
            disable()
            setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.navigate(AramsesScreen(game), LobudkaScreen(game)) } }
        }
    }

    private fun AdvancedStage.cb() {
        addActors(prodoCB)
        prodoCB.apply {
            setBounds(-50f, 238f, 52f, 52f)
            addAction(Actions.moveTo(34f,238f, 0.9f, Interpolation.elasticOut))
            setOnCheckListener { if (it) prodoBtn.enable() else prodoBtn.disable() }
        }
    }

    private fun AdvancedStage.text() {
        addActors(textesImg)
        textesImg.apply {
            setBounds(600f, 201f, 472f, 90f)
            addAction(Actions.moveTo(87f,201f, 0.9f, Interpolation.fade))
        }

        List(4) { Actor() }.onEachIndexed { index, actor ->
            addActor(actor)
            when(index) {
                0 -> { actor.apply {
                    setBounds(447f, 265f, 124f, 26f)
                    setOnClickListener { game.activity.webViewFragment.openPrivacy() }
                } }
                1 -> { actor.apply {
                    setBounds(87f, 233f, 209f, 25f)
                    setOnClickListener { game.activity.webViewFragment.openPrivacy() }
                } }
                2 -> { actor.apply {
                    setBounds(318f, 228f, 236f, 25f)
                    setOnClickListener { game.activity.webViewFragment.openTerms() }
                } }
                3 -> { actor.apply {
                    setBounds(82f, 201f, 236f, 25f)
                    setOnClickListener { game.activity.webViewFragment.openTerms() }
                } }
            }
        }

    }

}