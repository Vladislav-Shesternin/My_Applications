package com.picadilla.beepbeepvroo.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.picadilla.beepbeepvroo.game.LidaGame
import com.picadilla.beepbeepvroo.game.utils.actor.animHide
import com.picadilla.beepbeepvroo.game.utils.actor.animShow
import com.picadilla.beepbeepvroo.game.utils.actor.setOnClickListener
import com.picadilla.beepbeepvroo.game.utils.advanced.AdvancedScreen
import com.picadilla.beepbeepvroo.game.utils.advanced.AdvancedStage
import com.picadilla.beepbeepvroo.game.utils.region
import com.picadilla.beepbeepvroo.game.utils.TIMI_TERNER

class EManuelScreen(override val game: LidaGame): AdvancedScreen() {

    // Actor
    private val aPlay  = Image(game.simagoche.prely)
    private val aSette = Image(game.simagoche.stronsiy)
    private val aRules = Image(game.simagoche.rubeliy)

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.dgop.meduna.region)
        super.show()
        stageUI.root.animShow(TIMI_TERNER)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(Image(game.simagoche.recordiy).apply {
            setBounds(403f, 242f, 276f, 247f)
        })

        addDunce()

        val record = Actor()
        addActor(record)
        record.apply {
            setBounds(333f, 396f, 414f, 125f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIMI_TERNER) {
                    game.navigationManager.navigate(WeRcordeScreen::class.java.name, EManuelScreen::class.java.name)
                }
            }
        }
        val exit = Actor()
        addActor(exit)
        exit.apply {
            setBounds(333f, 204f, 414f, 125f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIMI_TERNER) {
                    game.navigationManager.exit()
                }
            }
        }
    }



    // ------------------------------------------------------------------------

    private fun AdvancedStage.addDunce() {
        addActors(aPlay, aSette, aRules)

        aPlay.apply {
            setBounds(289f, 1168f, 502f, 497f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIMI_TERNER) {
                    game.navigationManager.navigate(GalinkaScreen::class.java.name, EManuelScreen::class.java.name)
                }
            }
        }
        aSette.apply {
            setBounds(128f, 706f, 365f, 361f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIMI_TERNER) {
                    game.navigationManager.navigate(MedsikScreen::class.java.name, EManuelScreen::class.java.name)
                }
            }
        }
        aRules.apply {
            setBounds(587f, 706f, 365f, 361f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIMI_TERNER) {
                    game.navigationManager.navigate(LoRusScreen::class.java.name, EManuelScreen::class.java.name)
                }
            }
        }
    }

}