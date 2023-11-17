package aiebu.kakono.tutokazalos

import com.badlogic.gdx.scenes.scene2d.Actor
import aiebu.kakono.tutokazalos.soloha.pisoha.TIME_ANIM_SCREEN_ALPHA
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.animHide
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.animShow
import aiebu.kakono.tutokazalos.soloha.pisoha.manija.setOnClickListener
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedScreen
import aiebu.kakono.tutokazalos.soloha.pisoha.front.AdvancedStage
import aiebu.kakono.tutokazalos.soloha.pisoha.region
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

class VstrechaScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe = Actor()
    private val priv = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.BANAKIR.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(barbe, priv)
        barbe.apply {
            setBounds(44f, 103f,638f, 103f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(
                    SaskapotScreen::class.java.name) }
            }
        }

        priv.apply {
            setBounds(44f, 241f,638f, 103f)
            setOnClickListener {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(game.activity, Uri.parse(MainActivity.webURL))
            }
        }
    }

}