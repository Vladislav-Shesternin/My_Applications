package samoa.molly.dolina.radoste.game.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.badlogic.gdx.scenes.scene2d.Actor
import samoa.molly.dolina.radoste.MainActivity
import samoa.molly.dolina.radoste.game.LibGDXGame
import samoa.molly.dolina.radoste.game.utils.TIME_ANIM_SCREEN_ALPHA
import samoa.molly.dolina.radoste.game.utils.actor.animHide
import samoa.molly.dolina.radoste.game.utils.actor.animShow
import samoa.molly.dolina.radoste.game.utils.actor.setOnClickListener
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedScreen
import samoa.molly.dolina.radoste.game.utils.advanced.AdvancedStage
import samoa.molly.dolina.radoste.game.utils.region

class KKDScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.assets.KKD.region)
        super.show()
        stageUI.root.animShow()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val a = Actor()
        addActor(a)
        a.apply {
            setBounds(48f, 17f, 105f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKScreen::class.java.name, this@KKDScreen::class.java.name)
                }
            }
        }
        val b = Actor()
        addActor(b)
        b.apply {
            setBounds(147f, 17f, 105f, 85f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKJScreen::class.java.name, this@KKDScreen::class.java.name)
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------


}