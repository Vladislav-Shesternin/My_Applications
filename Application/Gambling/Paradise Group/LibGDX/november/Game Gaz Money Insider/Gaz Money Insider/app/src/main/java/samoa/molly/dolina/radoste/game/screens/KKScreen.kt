package samoa.molly.dolina.radoste.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import samoa.molly.dolina.radoste.game.HorizontalGroup
import samoa.molly.dolina.radoste.game.LibGDXGame
import samoa.molly.dolina.radoste.game.VerticalGroup
import samoa.molly.dolina.radoste.game.utils.TIME_ANIM_SCREEN_ALPHA
import samoa.molly.dolina.radoste.game.utils.actor.animHide
import samoa.molly.dolina.radoste.game.utils.actor.animShow
import samoa.molly.dolina.radoste.game.utils.actor.setOnClickListener
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedStage
import samoa.molly.dolina.radoste.game.utils.region

class KKScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val hg         = HorizontalGroup(this, 17f, startGap = 34f)
    private val scrollPane = ScrollPane(hg)

    private val vg          = VerticalGroup(this, -1f, startGap = 0f, endGap = 0f)
    private val scrollPanev = ScrollPane(vg)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.assets.KK.region)
        super.show()
        stageUI.root.animShow()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 927f, 625f, 174f)

        game.assets.bList.onEach {
            hg.addActor(Image(it).apply { setSize(269f, 173f) })
        }

        addActor(scrollPanev)
        scrollPanev.setBounds(35f, 217f, 555f, 594f)

        game.assets.aList.onEach {
            vg.addActor(Image(it).apply { setSize(555f, 115f) })
        }

        val a = Actor()
        addActor(a)
        a.apply {
            setBounds(147f, 17f, 105f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKJScreen::class.java.name, this@KKScreen::class.java.name)
                }
            }
        }

        val b = Actor()
        addActor(b)
        b.apply {
            setBounds(271f, 17f, 182f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKDScreen::class.java.name, this@KKScreen::class.java.name)
                }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------


}