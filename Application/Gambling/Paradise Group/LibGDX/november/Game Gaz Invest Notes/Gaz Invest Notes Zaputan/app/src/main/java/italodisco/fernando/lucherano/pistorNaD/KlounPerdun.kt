package italodisco.fernando.lucherano.pistorNaD

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.badlogic.gdx.scenes.scene2d.Actor
import italodisco.fernando.lucherano.JopaStarTue
import italodisco.fernando.lucherano.iopartew.opOPa
import italodisco.fernando.lucherano.iopartew.sandes.FraoEngel
import italodisco.fernando.lucherano.iopartew.sandes.KoloVo
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animHide
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animShow
import italodisco.fernando.lucherano.iopartew.pppp098.actor.setOnClickListener
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedScreen
import italodisco.fernando.lucherano.iopartew.sandes.pistro.paLetRa
import italodisco.fernando.lucherano.iopartew.pppp098.region

class KlounPerdun(override val game: opOPa): AdvancedScreen() {

    private val barbe = Actor()
    private val privatka = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Barboval.region)
        super.show()
        stageUI.root.animShow(KoloVo)
    }

    override fun paLetRa.addActorsOnStageUI() {
        addActors(barbe, privatka)
        barbe.apply {
            setBounds(101f, 70f,385f, 100f)
            setOnClickListener {
                stageUI.root.animHide(KoloVo) { game.YTARAT.navigate(
                    FraoEngel::class.java.name) }
            }
        }
        privatka.apply {
            setBounds(101f, 206f,385f, 100f)
            setOnClickListener {
                val intent = CustomTabsIntent.Builder().build()
                intent.launchUrl(game.activity, Uri.parse(JopaStarTue.webURL))
            }
        }
    }

}