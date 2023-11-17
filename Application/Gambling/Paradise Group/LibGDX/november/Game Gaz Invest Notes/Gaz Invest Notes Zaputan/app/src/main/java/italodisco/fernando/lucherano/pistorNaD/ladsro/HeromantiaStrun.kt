package italodisco.fernando.lucherano.pistorNaD.ladsro

import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import italodisco.fernando.lucherano.iopartew.opOPa
import italodisco.fernando.lucherano.iopartew.sandes.KoloVo
import italodisco.fernando.lucherano.iopartew.pppp098.actor.animHide
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedScreen
import italodisco.fernando.lucherano.iopartew.sandes.pistro.paLetRa
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import italodisco.fernando.lucherano.SpriteManagerUUU
import italodisco.fernando.lucherano.iopartew.pppp098.actor.setOnClickListener
import italodisco.fernando.lucherano.pistorNaD.DSM
import italodisco.fernando.lucherano.pistorNaD.KlounPerdun
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class HeromantiaStrun(override val game: opOPa) : AdvancedScreen() {

    protected val yyyyyUUU     = MutableStateFlow(0f)
    private var ydud  = false
             protected var anton = false
    private var uuu89     = false


    override fun show() {
        super.show()
                          bRa()
        popa()
    }

    override fun render(delta: Float) {
        super.render(delta)
        ui8()
        ui9()
    }

    override fun paLetRa.addActorsOnStageUI() {
        uuu89 = true
    }


    private fun bRa() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManagerUUU.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManagerUUU.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun iuo() {
        game.spriteManager.initAtlasAndTexture()
    }

    private fun ui8() {
        if (ydud.not()) {
            if (game.IADASDIUSADH.update(15)) {
                ydud = true
                iuo()
            }
            yyyyyUUU.value = game.IADASDIUSADH.progress
        }
    }

    private fun popa() {
        coroutine?.launch {
            var progress = 0
            yyyyyUUU.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
//                    runGDX { progressLabel.setText("$progress%") }
//                    if (progress % 25 == 0) log("progress = $progress%")
                    if (progress == 100) anton = true

                    delay((8..17).random().toLong())
                }
            }
        }
    }

    private fun ui9() {
        if (anton && uuu89) {
            uuu89 = false

            game.activity.lottie.hideLoader()

            coroutine?.launch(Dispatchers.IO) {
                if (DSM.IsDialog.get() == true) {
                    stageUI.root.animHide(KoloVo) {
                        game.YTARAT.navigate(KlounPerdun::class.java.name)
                    }
                } else addP1P2()

            }
        }
    }

    private fun addP1P2() {
        val p1 = Image(game.spriteUtil.P1)
        val p2 = Image(game.spriteUtil.P2)

        stageUI.addActors(p1,p2)
        p2.apply {
            touchable = Touchable.disabled
            setBounds(68f, 520f, 453f, 270f)
            addAction(Actions.alpha(0f))
            setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch { DSM.IsDialog.update { true } }

                stageUI.root.animHide(KoloVo) {
                    game.YTARAT.navigate(KlounPerdun::class.java.name)
                }
            }
        }

        p1.apply {
            setBounds(68f, 520f, 453f, 270f)
            setOnClickListener {
                p1.addAction(Actions.sequence(
                    Actions.fadeOut(0.3f),
                    Actions.run {
                        touchable = Touchable.disabled
                        p2.addAction(Actions.fadeIn(0.3f))
                        p2.touchable = Touchable.enabled
                    }
                ))
            }
        }




    }

}