package crupp.tsk.learnn.inggg.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import crupp.tsk.learnn.inggg.game.actor_tools.checkbox.ACheckBoxGroup
import crupp.tsk.learnn.inggg.game.LibGDXGame
import crupp.tsk.learnn.inggg.game.actor_tools.checkbox.ACheckBox
import crupp.tsk.learnn.inggg.game.actor_tools.checkbox.ACheckBox.Type.*
import crupp.tsk.learnn.inggg.game.utils.actor.animHide
import crupp.tsk.learnn.inggg.game.utils.actor.animShow
import crupp.tsk.learnn.inggg.game.utils.actor.setOnClickListener
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedScreen
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedStage
import crupp.tsk.learnn.inggg.game.utils.numStr

class FevrallScreen(override val game: LibGDXGame): AdvancedScreen() {

    val gvintik = game.spriteUtil.GAME.d_LIST

    // Actor
    private val topikImg = Image(game.spriteUtil.GAME.ETETIK)
    private val texta    = Label("${numStr(1,9,1)},${numStr(100, 999,1)}.${numStr(0,9,2)}", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_50.font, Color.WHITE))
    private val acatar   = Actor()
    private val cbksaImg = Image(game.spriteUtil.GAME.CIFERIKK)
    private val titulImg = Image(gvintik.random())
    private val butanImg = Image(game.spriteUtil.GAME.PK)



    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        topikImg()
        cbgfImg()
        titulImg()
        pkImg()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.topikImg() {
        addActor(topikImg)
        topikImg.apply {
            setBounds(-400f, 879f, 377f, 380f)
            addAction(Actions.moveTo(31f,879f, 0.7f, Interpolation.smoother))
        }
        addActor(texta)
        texta.apply {
            setBounds(527f, 916f, 335f, 54f)
            addAction(Actions.moveTo(227f,916f, 0.7f, Interpolation.smoother))
        }
        addActor(acatar)
        acatar.apply {
            setBounds(0f, 1164f, 138f, 138f)
            setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.back() } }
        }
    }

    private fun AdvancedStage.cbgfImg() {
        addActor(cbksaImg)
        cbksaImg.apply {
            setBounds(90f, 700f, 445f, 71f)
            addAction(Actions.moveTo(90f, 783f, 0.5f, Interpolation.smoother))
        }

        val groupik = ACheckBoxGroup()
        val listType = listOf(DDD, D7, M1, M6, Y1)
        List(5) { ACheckBox(this@FevrallScreen, listType[it]) }.onEachIndexed { index, actor ->
            addActor(actor)
            actor.checkBoxGroup = groupik
            when(index) {
                0 -> actor.setBounds(70f, 784f, 70f, 70f)
                1 -> actor.setBounds(173f, 784f, 70f, 70f)
                2 -> actor.setBounds(276f, 784f, 70f, 70f)
                3 -> actor.setBounds(384f, 784f, 70f, 70f)
                4 -> actor.setBounds(488f, 784f, 70f, 70f)
            }
            actor.check(false)
            actor.setOnCheckListener { if (it) titulImg.drawable = TextureRegionDrawable(gvintik.random()) }
        }

    }

    private fun AdvancedStage.titulImg() {
        addActor(titulImg)
        titulImg.apply {
            animHide()
            setBounds(33f, 236f, 539f, 506f)
            animShow(1f)
        }
    }

    private fun AdvancedStage.pkImg() {
        addActor(butanImg)
        butanImg.apply {
            setBounds(33f, -100f, 558f, 93f)
            addAction(Actions.moveTo(33f, 61f, 1f, Interpolation.smoother))
            setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.back() } }
        }
    }

}