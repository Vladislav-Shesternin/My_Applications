package com.fortunetiger.carnival.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.fortunetiger.carnival.game.utils.actor.disable
import com.fortunetiger.carnival.game.utils.actor.enable
import com.fortunetiger.carnival.game.utils.actor.setOnClickListener
import com.fortunetiger.carnival.game.utils.advanced.AdvancedGroup
import com.fortunetiger.carnival.game.utils.advanced.AdvancedScreen
import com.fortunetiger.carnival.game.utils.runGDX

class AShar(override val screen: AdvancedScreen): AdvancedGroup() {

    private val sharImg = Image().apply { touchable = Touchable.disabled }
    private val boomImg = Image(screen.game.allAssets.boom).apply {
        touchable = Touchable.disabled
        color.a = 0f
    }

    var boomBlock: () -> Unit = {}

    override fun addActorsOnGroup() {
        addAndFillActor(sharImg)
        addActor(boomImg)
        boomImg.setBounds(-27f, 41f, 272f, 272f)

        setOnClickListener {
            runGDX {
                disable()
                boomBlock()

                sharImg.color.a = 0f
                boomImg.addAction(
                    Actions.sequence(
                        Actions.fadeIn(0.1f),
                        Actions.delay(0.1f),
                        Actions.fadeOut(0.1f),
                    )
                )
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(region: TextureRegion) {
        addAction(Actions.sequence(
            Actions.delay(0.4f),
            Actions.run {
                boomImg.clearActions()
                boomImg.color.a = 0f

                sharImg.color.a = 1f
                sharImg.drawable = TextureRegionDrawable(region)
                enable()
            }
        ))

    }

}