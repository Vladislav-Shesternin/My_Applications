package com.veldan.gamebox2d.game.screens

import android.graphics.drawable.NinePatchDrawable
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.QueryCallback
import com.badlogic.gdx.physics.box2d.joints.MouseJoint
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.veldan.gamebox2d.game.actors.LoaderGroup
import com.veldan.gamebox2d.game.box2d.AbstractBody
import com.veldan.gamebox2d.game.box2d.AbstractBodyGroup
import com.veldan.gamebox2d.game.box2d.AbstractJoint
import com.veldan.gamebox2d.game.box2d.WorldUtil
import com.veldan.gamebox2d.game.box2d.bodies.BCircle
import com.veldan.gamebox2d.game.box2d.bodies.BPlatform
import com.veldan.gamebox2d.game.box2d.bodiesGroup.BGBorders
import com.veldan.gamebox2d.game.manager.SpriteManager
import com.veldan.gamebox2d.game.utils.actor.disable
import com.veldan.gamebox2d.game.utils.advanced.AdvancedBox2dScreen
import com.veldan.gamebox2d.game.utils.advanced.AdvancedStage
import com.veldan.gamebox2d.game.utils.runGDX
import com.veldan.gamebox2d.game.utils.toB2
import com.veldan.gamebox2d.util.log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch


class GameScreen: AdvancedBox2dScreen(WorldUtil()) {

    // Actor
    private val user = Image(SpriteManager.GameRegion.USER.region)

    // Body
    private val bPlatform = BPlatform(this)
    private val bCircle   = BCircle(this)

    // Joint
    private val jMouse = AbstractJoint<MouseJoint>(this)

    // BodyGroup
    private val bgBorders = BGBorders(this)

    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.GRID.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        createBG_Borders()
        createB_Platform()
        createB_Circle()

        addUser()

        mainGroup.addListener(getInputListenerForMouseJoint())
        mainGroup.addListener(getInputListenerForUser())
    }

    override fun dispose() {
        super.dispose()
        listOf<AbstractBodyGroup>(bgBorders)
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addUser() {
        addActor(user)
        user.apply {
            disable()
            setSize(85f, 105f)
            addAction(Actions.alpha(0f))
        }


        // TEXT

//        val fontFamily = FontFamily(arrayOf(
//            Font(FontTTFManager.JosefinRegularFont.font_75.font).setName("JosefinRegularFont_75"),
//            Font(FontTTFManager.JosefinBoldFont.font_75.font).setName("JosefinBoldFont_75"),
//            Font(FontTTFManager.RobotoRegularFont.font_75.font).setName("RobotoRegularFont_75"),
//        ))
//
//        val font = Font().apply { setFamily(fontFamily) }
//
//        val label = TypingLabel("Hello My name is Veldan", font)
//
//        addActor(label)
//        label.setBounds(36f, 478f, 1327f, 187f)
//        label.alignment = Align.center
//        label.wrap = true
//        label.debug()


        // Nine 9.paths

//        val nine = Image(SpriteManager.EnumAtlas.GAME.data.atlas.createPatch("nine"))
//        addActor(nine)
//        nine.setBounds(165f, 303f, 67f, 99f)

    }

    // ------------------------------------------------------------------------
    // Create Body
    // ------------------------------------------------------------------------

    private fun createB_Platform() {
        bPlatform.create(526f, 163f, 348f, 53f)
    }

    private fun createB_Circle() {
        bCircle.create(613f, 217f, 173f, 173f)
    }

    // ------------------------------------------------------------------------
    // Create Body Group
    // ------------------------------------------------------------------------

    private fun createBG_Borders() {
        bgBorders.create(Vector2(0f, 0f), Vector2(WIDTH, HEIGHT))
    }

    // ---------------------------------------------------
    // Create Joint
    // ---------------------------------------------------

    private fun createJ_Distance() {

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getInputListenerForMouseJoint() = object : InputListener() {

        var hitAbstractBody: AbstractBody? = null
        val touchPointInBox = Vector2()
        val callback        = QueryCallback { fixture ->
            if (fixture.testPoint(touchPointInBox)) {
                hitAbstractBody = fixture.body?.userData as AbstractBody
                return@QueryCallback false
            }
            return@QueryCallback true
        }

        override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
            touchPointInBox.set(Vector2(x, y).toB2)
            hitAbstractBody = bCircle
            worldUtil.world.QueryAABB(callback,
                touchPointInBox.x - 0.01f,
                touchPointInBox.y - 0.01f,
                touchPointInBox.x + 0.01f,
                touchPointInBox.y + 0.01f)

            hitAbstractBody?.let {
                jMouse.create(MouseJointDef().apply {
                    bodyA = bgBorders.bDown.body
                    bodyB = it.body
                    collideConnected = true

                    target.set(bodyB.position)
                    maxForce = 1000 * bodyB.mass
                })
            }
            return true
        }

        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            jMouse.joint?.target = Vector2(x, y).toB2
        }

        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            jMouse.startDestroy()
        }
    }

    private fun getInputListenerForUser() = object : InputListener() {
        override fun touchDown(event: InputEvent?,x: Float,y: Float,pointer: Int,button: Int): Boolean {
            user.apply {
                clearActions()
                addAction(Actions.alpha(1f))
                this.x = x - 28f
                this.y = y - 103f
            }
            return true
        }
        override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
            user.apply {
                this.x = x - 28f
                this.y = y - 103f
            }
        }
        override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
            user.addAction(Actions.fadeOut(0.3f))
        }
    }

}