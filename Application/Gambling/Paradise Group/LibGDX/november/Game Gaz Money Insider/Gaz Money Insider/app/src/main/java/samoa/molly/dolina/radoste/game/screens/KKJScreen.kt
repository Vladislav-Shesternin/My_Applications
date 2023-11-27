package samoa.molly.dolina.radoste.game.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import samoa.molly.dolina.radoste.MainActivity
import samoa.molly.dolina.radoste.game.LibGDXGame
import samoa.molly.dolina.radoste.game.VerticalGroup
import samoa.molly.dolina.radoste.game.utils.TIME_ANIM_SCREEN_ALPHA
import samoa.molly.dolina.radoste.game.utils.actor.animHide
import samoa.molly.dolina.radoste.game.utils.actor.animShow
import samoa.molly.dolina.radoste.game.utils.actor.setOnClickListener
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedStage
import samoa.molly.dolina.radoste.game.utils.region

class KKJScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val vg          = VerticalGroup(this, -1f, startGap = 0f, endGap = 0f)
    private val scrollPanev = ScrollPane(vg)


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.assets.KKJ.region)
        super.show()
        stageUI.root.animShow()
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        addActor(scrollPanev)
        scrollPanev.setBounds(35f, 217f, 555f, 818f)

        game.assets.aList.onEach {
            vg.addActor(Image(it).apply { setSize(555f, 115f) })
        }

        val a = Actor()
        addActor(a)
        a.apply {
            setBounds(48f, 17f, 105f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKScreen::class.java.name, this@KKJScreen::class.java.name)
                }
            }
        }

        val b = Actor()
        addActor(b)
        b.apply {
            setBounds(271f, 17f, 182f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKDScreen::class.java.name, this@KKJScreen::class.java.name)
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------


}