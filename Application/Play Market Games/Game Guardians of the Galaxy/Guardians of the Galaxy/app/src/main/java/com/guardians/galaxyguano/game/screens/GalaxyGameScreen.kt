package com.guardians.galaxyguano.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.guardians.galaxyguano.game.LibGDXGame
import com.guardians.galaxyguano.game.box2d.AbstractBody
import com.guardians.galaxyguano.game.box2d.BodyId
import com.guardians.galaxyguano.game.box2d.WorldUtil
import com.guardians.galaxyguano.game.box2d.bodies.BAsteroid
import com.guardians.galaxyguano.game.box2d.bodies.BBall
import com.guardians.galaxyguano.game.box2d.bodies.BPlatform
import com.guardians.galaxyguano.game.box2d.bodiesGroup.BGBorders
import com.guardians.galaxyguano.game.utils.DEGTORAD
import com.guardians.galaxyguano.game.utils.HEIGHT_UI
import com.guardians.galaxyguano.game.utils.TIME_ANIM
import com.guardians.galaxyguano.game.utils.WIDTH_UI
import com.guardians.galaxyguano.game.utils.actor.animHide
import com.guardians.galaxyguano.game.utils.actor.setOnClickListener
import com.guardians.galaxyguano.game.utils.advanced.AdvancedBox2dScreen
import com.guardians.galaxyguano.game.utils.advanced.AdvancedStage
import com.guardians.galaxyguano.game.utils.font.FontParameter
import com.guardians.galaxyguano.game.utils.region
import com.guardians.galaxyguano.game.utils.runGDX
import com.guardians.galaxyguano.game.utils.toB2
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class GalaxyGameScreen(override val game: LibGDXGame): AdvancedBox2dScreen(WorldUtil()) {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(40)
    private val font40        = fontGenerator_KeaniaOneRegular.generateFont(fontParameter)

    // Actor
    private val asteroidLbl = Label("Asteroid: 0", Label.LabelStyle(font40, Color.WHITE))
    private val leftImg     = Image(game.allAssets.left)
    private val rightImg    = Image(game.allAssets.right)

    // BodyGroup
    private val bgBorders = BGBorders(this)

    // Body
    private val bPlatform = BPlatform(this)
    private val bBall     = BBall(this)

    // Field
    private val asteroidFlow    = MutableSharedFlow<BAsteroid>(replay = 10)
    private var counterAsteroid = 0

    override fun show() {
        setBackBackground(game.loaderAssets.FONIK.region)
        super.show()
    }

    override fun dispose() {
        bgBorders.destroy()
        super.dispose()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Asteroids()

        createB_Platform()
        createB_Ball()

        addAsteroidLbl()
        addBtnImg()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedStage.addAsteroidLbl() {
        addActor(asteroidLbl)
        asteroidLbl.setBounds(853f, 1019f, 214f, 49f)
        asteroidLbl.setAlignment(Align.center)
    }

    private fun AdvancedStage.addBtnImg() {
        addActors(leftImg, rightImg)
        leftImg.apply {
            setBounds(31f, 175f, 181f, 181f)
            setOnClickListener {
                bPlatform.body?.setLinearVelocity(-20f, 0f)
            }
        }
        rightImg.apply {
            setBounds(1708f, 175f, 181f, 181f)
            setOnClickListener {
                bPlatform.body?.setLinearVelocity(20f, 0f)
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

    private fun createB_Asteroids() {
        repeat(10) {
            BAsteroid(this).also { bA ->

                bA.addEffect()

                bA.renderBlockArray.add(AbstractBody.RenderBlock {
                    bA.body?.let {
                        if (it.position.y <= -2f) {
                            if (bA.atomicBoolean.getAndSet(false)) asteroidFlow.tryEmit(bA)
                        }
                    }
                })

                bA.bodyDef.gravityScale  = 0f
                bA.create(-200f, HEIGHT+200f, 160f, 160f)

                asteroidFlow.tryEmit(bA)
            }
        }

        coroutine?.launch {
            asteroidFlow.collect { bA ->
                runGDX {
                    bA.body?.apply {
                        setTransform(Vector2(-200f, HEIGHT+200f).toB2, 0f)

                        setLinearVelocity(0f, 0f)
                        gravityScale = 0f
                        isAwake = false
                    }
                }
            }
        }
        coroutine?.launch {
            asteroidFlow.collect { bItem ->
                delay((300L..700L).random())
                runGDX {
                    bItem.body?.apply {
                        setTransform((80..1840).random().toFloat().toB2, (HEIGHT+100f).toB2, (0..360).random() * DEGTORAD)
                        gravityScale = 1f
                        isAwake      = true
                    }
                }
                delay(100)
                bItem.atomicBoolean.set(true)
            }
        }
    }

    private fun createB_Platform() {
        bPlatform.create(779f, 44f, 362f, 39f)

        bPlatform.renderBlockArray.add(AbstractBody.RenderBlock {
            if (bPlatform.body!!.position.x <= 0 || bPlatform.body!!.position.x >= 1558f.toB2) bPlatform.body?.setLinearVelocity(0f, 0f)
        })
    }

    private fun createB_Ball() {
        bBall.create(918f, 224f, 83f, 83f)

        bBall.beginContactBlockArray.add(AbstractBody.ContactBlock {
            when(it.id) {
                BodyId.DOWN     -> {
                    stageUI.root.animHide(TIME_ANIM) { game.navigationManager.navigate(GalaxyGameScreen::class.java.name) }
                }
                BodyId.PLATFORM -> {
                    game.soundUtil.apply { play(PLATFORM, 15f) }
                    val velocity  = bBall.body?.linearVelocity ?: Vector2()
                    val direction = velocity.nor()
                    val impulse   = direction.scl(100f)
                    bBall.body?.apply { applyLinearImpulse(impulse, worldCenter, true) }
                }
                BodyId.ASTEROID     -> {
                    it as BAsteroid

                    it.startEffect()

                    game.soundUtil.apply { play(ASTEROID) }
                    if (it.atomicBoolean.getAndSet(false)) asteroidFlow.tryEmit(it)

                    bBall.body?.apply { applyLinearImpulse(Vector2(0f, 100f), worldCenter, true) }

                    counterAsteroid++
                    asteroidLbl.setText("Asteroid: $counterAsteroid")
                }
            }
        })
    }

}