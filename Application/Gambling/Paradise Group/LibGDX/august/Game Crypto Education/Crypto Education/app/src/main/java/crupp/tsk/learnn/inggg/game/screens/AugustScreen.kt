package crupp.tsk.learnn.inggg.game.screens

import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import crupp.tsk.learnn.inggg.game.LibGDXGame
import crupp.tsk.learnn.inggg.game.actor_tools.scroll.HorizontalGroup
import crupp.tsk.learnn.inggg.game.actor_tools.scroll.VerticalGroup
import crupp.tsk.learnn.inggg.game.utils.actor.animHide
import crupp.tsk.learnn.inggg.game.utils.actor.setOnClickListener
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedScreen
import crupp.tsk.learnn.inggg.game.utils.advanced.AdvancedStage

class AugustScreen(override val game: LibGDXGame): AdvancedScreen() {

    // Actor
    private val rnkImg   = Image(game.spriteUtil.GAME.RNK)
    private val cartaImg = Image(game.spriteUtil.GAME.BATTAM)
    private val veshenka = VerticalGroup(this, 40f)
    private val tortik   = ScrollPane(veshenka)
    private val veshenkaH = HorizontalGroup(this, 27f, 32f)
    private val tortikH   = ScrollPane(veshenkaH)


    override fun show() {
        //stageUI.root.animHide()
//        setUIBackground(game.spriteUtil.SPLASH.BACKGROUND)
        super.show()
        //stageUI.root.animShow(0.3f)

    }

    override fun AdvancedStage.addActorsOnStageUI() {
        topikImg()
        bobikImg()
        addTortik()
        addPisuLizka()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.topikImg() {
        addActor(rnkImg)
        rnkImg.apply {
            setBounds(33f, 1100f, 558f, 270f)
            addAction(Actions.moveTo(33f,995f, 0.7f, Interpolation.smoother))
        }

    }

    private fun AdvancedStage.bobikImg() {
        addActor(cartaImg)
        cartaImg.apply {
            setBounds(0f, -150f, 624f, 141f)
            addAction(Actions.moveTo(0f, 0f, 1f, Interpolation.smoother))
        }
    }

    private fun AdvancedStage.addTortik() {
        addActors(tortik)
        tortik.setBounds(23f, 142f, 579f, 483f)
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



        addActors(tortikH)
        tortikH.setBounds(1f, 677f, 624f, 299f)
        tortikH.animHide()

        listOf(
            game.spriteUtil.GAME.e_LIST,
            game.spriteUtil.GAME.e_LIST,
            game.spriteUtil.GAME.e_LIST,
        ).flatten().shuffled().onEach {
            val img = Image(it).apply { setSize(250f, 300f) }
            veshenkaH.addActor(img)
        }

        tortikH.addAction(Actions.fadeIn(1f, Interpolation.smooth))
    }

    private fun AdvancedStage.addPisuLizka() {
        List(4) { Actor() }.onEachIndexed { index, actor ->
            addActor(actor)
            when(index) {
                0 -> { actor.apply {
                    setBounds(50f, 51f, 73f, 73f)
                    setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.navigate(AramsesScreen(game), AugustScreen(game)) } }
                } }
                1 -> { actor.apply {
                    setBounds(195f, 48f, 59f, 76f)
                    setOnClickListener {  }
                } }
                2 -> { actor.apply {
                    setBounds(330f, 48f, 108f, 76f)
                    setOnClickListener { stageUI.root.animHide(0.5f) { game.navigationManager.navigate(FevrallScreen(game), AugustScreen(game)) } }
                } }
                3 -> { actor.apply {
                    setBounds(513f, 48f, 83f, 76f)
                    setOnClickListener { game.activity.webViewFragment.openPrivacy() }
                } }
            }
        }
    }

}