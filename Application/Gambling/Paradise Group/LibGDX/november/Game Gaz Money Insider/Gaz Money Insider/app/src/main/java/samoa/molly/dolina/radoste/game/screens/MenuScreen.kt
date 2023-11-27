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

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val aa = Actor()
    private val bb = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.assets.GMI.region)
        super.show()
        stageUI.root.animShow()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(aa, bb)
        aa.apply {
            setBounds(34f, 274f, 555f, 90f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.navigate(KKScreen::class.java.name, this::class.java.name)
                }
            }
        }
        bb.apply {
            setBounds(34f, 152f, 555f, 90f)
            setOnClickListener {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(game.activity, Uri.parse(MainActivity.webURL))
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------


}