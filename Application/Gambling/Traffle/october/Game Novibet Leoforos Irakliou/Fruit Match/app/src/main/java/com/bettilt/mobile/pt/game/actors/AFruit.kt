package com.bettilt.mobile.pt.game.actors

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.AtomicQueue
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedGroup
import com.bettilt.mobile.pt.game.utils.advanced.AdvancedScreen

class AFruit(
    override val screen: AdvancedScreen,
    val region: TextureRegion,
): AdvancedGroup() {

    private val image = Image(region)
    private val glued = Image(screen.game.spriteUtil.GLUED)
    private val bom   = Image(screen.game.spriteUtil.BOM)

    private var isAnimGlued = false

    override fun addActorsOnGroup() {
        addAndFillActor(image)
        addGlued()
        addAndFillActor(bom)

        image.setOrigin(Align.center)
        bom.apply {
            setOrigin(Align.center)
            addAction(Actions.parallel(
                Actions.alpha(0f),
                Actions.scaleTo(0f, 0f)
            ))
        }
    }

    private fun addGlued() {
        addActor(glued)
        glued.setBounds(-16f, -16f, 157f, 157f)
        glued.apply {
            setOrigin(Align.center)
            addAction(Actions.scaleTo(0f, 0f))
        }
    }

    fun animGlued() {
        if (isAnimGlued.not()) {
            isAnimGlued = true

            glued.addAction(Actions.forever(Actions.sequence(
                Actions.scaleTo(1f, 1f, 0.5f, Interpolation.smooth),
                Actions.scaleTo(0.6f, 0.6f, 0.3f, Interpolation.smooth),
            )))
        }
    }

    fun animBom(block: () -> Unit) {
        glued.clearActions()
        glued.addAction(Actions.sequence(
            Actions.scaleTo(0f, 0f, 0.3f),
            Actions.run {
                image.addAction(Actions.scaleTo(0f, 0f, 0.3f))
                bom.addAction(Actions.sequence(
                    Actions.parallel(
                        Actions.fadeIn(0.4f),
                        Actions.scaleTo(1f, 1f,0.4f),
                        Actions.run { screen.game.soundUtil.apply { play(BOM) } }
                    ),
                    Actions.delay(0.1f),
                    Actions.fadeOut(0.3f),
                    Actions.scaleTo(0f, 0f, 0.3f),

                    Actions.run(block)
                ))
            }
        ))
    }


}