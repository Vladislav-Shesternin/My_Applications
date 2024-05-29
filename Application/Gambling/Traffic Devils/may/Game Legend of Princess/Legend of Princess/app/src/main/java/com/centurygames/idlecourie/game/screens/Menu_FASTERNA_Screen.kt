package com.centurygames.idlecourie.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecourie.game.HelloMotoGame
import com.centurygames.idlecourie.game.actors.button.AButton
import com.centurygames.idlecourie.game.utils.actor.animHide
import com.centurygames.idlecourie.game.utils.actor.animShow
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecourie.game.utils.advanced.AdvancedStage
import com.centurygames.idlecourie.game.utils.region
import com.centurygames.idlecourie.game.utils.Timek

class Menu_FASTERNA_Screen(override val game: HelloMotoGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.gudomidron.farmer.region)
        super.show()
        stageUI.root.animShow(Timek)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val piricessa = Image(game.valhalla.piricessssa).apply { setBounds(136f, 0f, 809f, 883f) }
        val d = piricessa
        addActor(d)

        addNuShoTut()
    }

    private fun AdvancedStage.addNuShoTut() {
        val btnP = AButton(this@Menu_FASTERNA_Screen, AButton.Static.Type.Play)
        val btnS = AButton(this@Menu_FASTERNA_Screen, AButton.Static.Type.Settings)
        addActor(btnP)
        addActor(btnS)

        btnP.apply {
            setBounds(141f, 1375f, 798f, 262f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.navigate(Process_GALINTVAGEN_Screen::class.java.name, Menu_FASTERNA_Screen::class.java.name)
                }
            }
        }
        btnS.apply {
            setBounds(141f, 1039f, 798f, 262f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.navigate(Settings_BUBA_Screen::class.java.name, Menu_FASTERNA_Screen::class.java.name)
                }
            }
        }
    }

}