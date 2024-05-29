package com.centurygames.idlecourie.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.centurygames.idlecourie.game.HelloMotoGame
import com.centurygames.idlecourie.game.actors.button.AButton
import com.centurygames.idlecourie.game.actors.checkbox.ACheckBox
import com.centurygames.idlecourie.game.utils.Timek
import com.centurygames.idlecourie.game.utils.actor.animHide
import com.centurygames.idlecourie.game.utils.actor.animShow
import com.centurygames.idlecourie.game.utils.actor.setBounds
import com.centurygames.idlecourie.game.utils.actor.setOnClickListener
import com.centurygames.idlecourie.game.utils.advanced.AdvancedScreen
import com.centurygames.idlecourie.game.utils.advanced.AdvancedStage
import com.centurygames.idlecourie.game.utils.region

class Rules_fdgDHHDH_Screen(override val game: HelloMotoGame): AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackBackground(game.gudomidron.farmer.region)
        super.show()
        stageUI.root.animShow(Timek)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        val back = AButton(this@Rules_fdgDHHDH_Screen, AButton.Static.Type.back)
        addActor(back)
        back.apply {
            setBounds(476f, 883f, 128f, 128f)
            setOnClickListener {
                stageUI.root.animHide(Timek) {
                    game.navigationManager.back()
                }
            }
        }

        addActor(Image(game.valhalla.piricessssa).apply { setBounds(136f, 0f, 809f, 883f) })
        addActor(Image(game.valhalla.onboard).apply { setBounds(126f, 1045f, 828f, 801f) })
    }

}