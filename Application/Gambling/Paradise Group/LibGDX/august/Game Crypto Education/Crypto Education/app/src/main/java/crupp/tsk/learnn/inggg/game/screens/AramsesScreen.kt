package crupp.tsk.learnn.inggg.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import crupp.tsk.learnn.inggg.game.LibGDXGame
import crupp.tsk.learnn.inggg.game.actor_tools.scroll.VerticalGroup
import crupp.tsk.learnn.inggg.game.utils.actor.animHide
import crupp.tsk.learnn.inggg.game.utils.actor.setOnClickListener
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedScreen
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedStage
import crupp.tsk.learnn.inggg.game.utils.numStr

class AramsesScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val topikImg = Image(game.spriteUtil.GAME.TOPIK)
    private val cartaImg = Image(game.spriteUtil.GAME.BOBIK)
    private val kipitImg = Image(game.spriteUtil.GAME.KIPTIK)
    private val manduImg = Image(game.spriteUtil.GAME.BOTOMOK)
    private val veshenka = VerticalGroup(this, 40f)
    private val tortik   = ScrollPane(veshenka)
    private val texta    = Label("${numStr(10,99,1)},${numStr(100, 999,1)}.${numStr(0,9,2)}", Label.LabelStyle(game.fontTTFUtil.fontInterBlack.font_50.font, Color.WHITE))


    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        topikImg()
        bobikImg()
        botkoImg()
        addTortik()
        addPisuLizka()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.topikImg() {
        addActor(topikImg)
        topikImg.apply {
            setBounds(33f, 1000f, 556f, 295f)
            addAction(Actions.moveTo(33f,971f, 0.7f, Interpolation.smoother))
        }
        addActor(texta)
        texta.apply {
            setBounds(200f, 1014f, 520f, 54f)
            addAction(Actions.moveTo(70f,1014f, 0.7f, Interpolation.smoother))
        }
    }

    private fun AdvancedStage.bobikImg() {
        addActor(cartaImg)
        cartaImg.apply {
            setBounds(-560f, 638f, 558f, 300f)
            addAction(Actions.moveTo(33f, 638f, 0.7f, Interpolation.smoother))
        }
        addActor(kipitImg)
        kipitImg.apply {
            setBounds(600f, 547f, 556f, 54f)
            addAction(Actions.moveTo(35f, 547f, 0.7f, Interpolation.smoother))
        }
    }

    private fun AdvancedStage.botkoImg() {
        addActor(manduImg)
        manduImg.apply {
            setBounds(0f, -150f, 624f, 141f)
            addAction(Actions.moveTo(0f, 0f, 0.7f, Interpolation.smoother))
        }
    }

    private fun AdvancedStage.addTortik() {
        addActors(tortik)
        tortik.setBounds(23f, 170f, 578f, 353f)
        tortik.animHide()

        listOf(
            game.spriteUtil.GAME.p_LIST,
            game.spriteUtil.GAME.p_LIST,
            game.spriteUtil.GAME.p_LIST,
            game.spriteUtil.GAME.p_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(578f, 61f) }
            veshenka.addActor(img)
        }

        tortik.addAction(Actions.fadeIn(1f, Interpolation.smooth))
    }

    private fun AdvancedStage.addPisuLizka() {
        List(4) { Actor() }.onEachIndexed { index, actor ->
            addActor(actor)
            when(index) {
                0 -> { actor.apply {
                    setBounds(50f, 51f, 73f, 73f)
                    setOnClickListener {  }
                } }
                1 -> { actor.apply {
                    setBounds(195f, 48f, 59f, 76f)
                    setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.navigate(AugustScreen(game), AramsesScreen(game)) } }
                } }
                2 -> { actor.apply {
                    setBounds(330f, 48f, 108f, 76f)
                    setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.navigate(FevrallScreen(game), AramsesScreen(game)) } }
                } }
                3 -> { actor.apply {
                    setBounds(513f, 48f, 83f, 76f)
                    setOnClickListener { game.activity.webViewFragment.openTerms() }
                } }
            }
        }
    }

}