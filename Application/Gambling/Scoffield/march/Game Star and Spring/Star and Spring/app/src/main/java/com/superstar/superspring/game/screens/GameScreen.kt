package com.superstar.superspring.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.superstar.superspring.game.LibGDXGame
import com.superstar.superspring.game.box2d.AbstractBody
import com.superstar.superspring.game.box2d.BodyId
import com.superstar.superspring.game.box2d.WorldUtil
import com.superstar.superspring.game.box2d.bodies.BBatut
import com.superstar.superspring.game.box2d.bodies.BSpring
import com.superstar.superspring.game.box2d.bodies.BStar
import com.superstar.superspring.game.box2d.bodiesGroup.BGBorders
import com.superstar.superspring.game.utils.HEIGHT_UI
import com.superstar.superspring.game.utils.TIME_ANIM
import com.superstar.superspring.game.utils.WIDTH_UI
import com.superstar.superspring.game.utils.actor.animHide
import com.superstar.superspring.game.utils.actor.animShow
import com.superstar.superspring.game.utils.actor.setOnClickListener
import com.superstar.superspring.game.utils.advanced.AdvancedBox2dScreen
import com.superstar.superspring.game.utils.advanced.AdvancedStage
import com.superstar.superspring.game.utils.font.FontParameter
import com.superstar.superspring.game.utils.region
import com.superstar.superspring.game.utils.runGDX
import com.superstar.superspring.game.utils.toB2

class GameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    private val params = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(87)
    private val font = fontGenerator_Baloo.generateFont(params)

    // Actor
    private val label = Label("0", Label.LabelStyle(font, Color.valueOf("FCC715")))
    private val left = Actor()
    private val right = Actor()

    // Body
    private val bBatut  = BBatut(this)
    private val bSpring = BSpring(this)
    private val bStar   = BStar(this)

    // BodyGroup
    private val bgBorders = BGBorders(this)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.loaderAssets.BACKGROUND.region, game.allAssets.GAME_B.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM)
    }

    override fun dispose() {
        bgBorders.destroy()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addLabel()
        addBtns()

        createBG_Borders()

        createB_Batut()
        createB_Spring()
        createB_Star()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addLabel() {
        addActor(label)
        label.setBounds(510f, 15f, 100f, 91f)
    }

    private fun AdvancedStage.addBtns() {
        addActors(left, right)
        left.apply {
            setBounds(80f, 63f, 154f, 154f)
            setOnClickListener(game.soundUtil) {
                bSpring.body?.apply { applyLinearImpulse(Vector2(-5f, 10f), worldCenter, true) }
            }
        }
        right.apply {
            setBounds(845f, 63f, 154f, 154f)
            setOnClickListener(game.soundUtil) {
                bSpring.body?.apply { applyLinearImpulse(Vector2(5f, 10f), worldCenter, true) }
            }
        }
    }

    // ---------------------------------------------------
    // create BodyGroup
    // ---------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(0f, 0f, WIDTH_UI, HEIGHT_UI)
    }

    // ---------------------------------------------------
    // create Body
    // ---------------------------------------------------

    private fun createB_Batut() {
        bBatut.create(286f, -340f, 507f, 507f)

        bBatut.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.SPRING) {
                val velocity = bSpring.body?.linearVelocity ?: Vector2()
                val direction = velocity.nor()
                val impulse = direction.scl(10f)
                bSpring.body?.apply { applyLinearImpulse(impulse, worldCenter, true) }
            }
        })
    }

    private fun createB_Spring() {
        bSpring.create(476f, 416f, 128f, 259f)
    }

    private fun randomX() = (0..946).random().toFloat()
    private fun randomY() = (374..1780).random().toFloat()

    private fun createB_Star() {
        bStar.create(randomX(), randomY(), 134f, 132f)

        var counter = 0

        bStar.beginContactBlockArray.add(AbstractBody.ContactBlock {
            if (it.id == BodyId.SPRING) {
                runGDX {
                    game.soundUtil.apply { play(star, 20f) }

                    bStar.body!!.setTransform(randomX().toB2, randomY().toB2, 0f)
                    counter++
                    MenuScreen.MAXIMUM = counter
                    label.setText(counter)
                }
            }
        })
    }

}