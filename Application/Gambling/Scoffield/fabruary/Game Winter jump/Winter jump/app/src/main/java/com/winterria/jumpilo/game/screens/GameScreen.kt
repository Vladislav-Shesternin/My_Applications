package com.winterria.jumpilo.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.winterria.jumpilo.game.LibGDXGame
import com.winterria.jumpilo.game.box2d.AbstractBody
import com.winterria.jumpilo.game.box2d.BodyId
import com.winterria.jumpilo.game.box2d.WorldUtil
import com.winterria.jumpilo.game.box2d.bodies.BMount
import com.winterria.jumpilo.game.box2d.bodies.BSnow
import com.winterria.jumpilo.game.box2d.bodies.BStar
import com.winterria.jumpilo.game.box2d.bodiesGroup.BGBorders
import com.winterria.jumpilo.game.utils.HEIGHT_UI
import com.winterria.jumpilo.game.utils.WIDTH_UI
import com.winterria.jumpilo.game.utils.actor.setOnClickListener
import com.winterria.jumpilo.game.utils.advanced.AdvancedBox2dScreen
import com.winterria.jumpilo.game.utils.advanced.AdvancedStage
import com.winterria.jumpilo.game.utils.region
import com.winterria.jumpilo.game.utils.runGDX
import com.winterria.jumpilo.game.utils.toB2
import com.winterria.jumpilo.util.log

class GameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val jumpImg = Image(game.allAssets.jump)

    // Body
    private val bSnow      = BSnow(this)
    private val bMountList = List(5) { BMount(this) }
    private val bStarList  = List(2) { BStar(this) }

    // BodyGroup
    private val bgBorders  = BGBorders(this)

    override fun show() {
        setBackBackground(game.loaderAssets.WINTER.region)
        super.show()
    }

    override fun dispose() {
        bgBorders.destroy()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Snow()
        createB_Mount()
        createB_Star()

        addJumpImg()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addJumpImg() {
        addActor(jumpImg)
        jumpImg.apply {
            setBounds(795f, 27f, 330f, 105f)
            setOnClickListener {
                game.soundUtil.apply { play(JUMP_C) }
                bSnow.body?.apply {
                    applyLinearImpulse(Vector2(0f, 200f), worldCenter, true)
                }
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

    private fun createB_Snow() {
        bSnow.create(875f, 455f, 170f, 170f)

        bSnow.beginContactBlockArray.add(AbstractBody.ContactBlock {
            when(it.id) {
                BodyId.BORDERS      -> {
                    game.soundUtil.apply { play(PUNCH_BOXING) }
                }
                BodyId.HILL      -> {
                    game.soundUtil.apply { play(JUMP_C) }

                    //val velocity  = bSnow.body?.linearVelocity ?: Vector2()
                    //val direction = velocity.nor()
                    //val impulse   = direction.scl(500f)
                    //bSnow.body?.apply { applyLinearImpulse(impulse, worldCenter, true) }
                }
                BodyId.Winter.STAR -> {
                    runGDX {
                        game.soundUtil.apply { play(CORRECT) }

                        it.body?.setTransform(getStarPos().toB2, 0f)
                    }
                }
            }
        })
    }

    private fun createB_Mount() {
        val listX = listOf(79f, 493f, 846f, 1213f, 1755f)
        bMountList.onEachIndexed { index, bMount -> bMount.create(listX[index], 192f, 89f, 33f) }
    }

    private val starSize = Vector2(170f, 170f)

    private fun createB_Star() {
        bStarList.onEach { triangle -> triangle.create(getStarPos(), starSize) }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private val tmpVec = Vector2()

    private fun getStarPos() = tmpVec.set(
        (85..1800).random().toFloat(),
        (300..950).random().toFloat(),
    )

}