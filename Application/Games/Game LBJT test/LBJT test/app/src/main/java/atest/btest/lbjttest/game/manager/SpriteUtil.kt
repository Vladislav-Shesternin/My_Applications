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

    // val NUMBER_LIST = List(9) { getGameRegion("number ${it.inc()}") }

    val BACKGROUND = SpriteManager.EnumTexture.BACKGROUND.data.texture
}