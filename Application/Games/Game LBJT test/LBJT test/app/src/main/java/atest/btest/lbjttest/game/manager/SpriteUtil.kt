package atest.btest.lbjttest.game.manager

import com.badlogic.gdx.graphics.g2d.TextureRegion

class AllAssets {
    private fun getRegion(name: String): TextureRegion =
        SpriteManager.EnumAtlas.GAME.data.atlas.findRegion(name)

    val C_DYNAMIC   = getRegion("c_dynamic")
    val C_KINEMATIC = getRegion("c_kinematic")
    val C_STATIC    = getRegion("c_static")
    val H_DYNAMIC   = getRegion("h_dynamic")
    val H_KINEMATIC = getRegion("h_kinematic")
    val H_STATIC    = getRegion("h_static")
    val V_DYNAMIC   = getRegion("v_dynamic")
    val V_KINEMATIC = getRegion("v_kinematic")
    val V_STATIC    = getRegion("v_static")
    val TEST_BTN    = getRegion("test_btn")
    val USER        = getRegion("user")
    val ANCHOR      = getRegion("anchor")
    val C_DYNAMIC_DIAGONAL   = getRegion("c_dynamic_diagonal")
    val C_DYNAMIC_HORIZONTAL = getRegion("c_dynamic_horizontal")
    val C_DYNAMIC_VERTICAL   = getRegion("c_dynamic_vertical")

    // val NUMBER_LIST = List(9) { getGameRegion("number ${it.inc()}") }

    val BACKGROUND   = SpriteManager.EnumTexture.BACKGROUND.data.texture
    val WHEEL_ARROWS = SpriteManager.EnumTexture.WHEEL_ARROWS.data.texture
    val GEAR         = SpriteManager.EnumTexture.GEAR.data.texture
    val DEGREES      = SpriteManager.EnumTexture.DEGREES.data.texture
    val METERS      = SpriteManager.EnumTexture.METERS.data.texture

}