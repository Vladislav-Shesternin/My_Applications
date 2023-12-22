//package com.tigerfortune.jogo.game.box2d.bodies
//
//import com.badlogic.gdx.physics.box2d.BodyDef
//import com.badlogic.gdx.physics.box2d.FixtureDef
//import com.tigerfortune.jogo.game.actors.image.AImage
//import com.tigerfortune.jogo.game.box2d.AbstractBody
//import com.tigerfortune.jogo.game.box2d.BodyId
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedBox2dScreen
//import com.tigerfortune.jogo.game.utils.advanced.AdvancedGroup
//
//class BWin(override val screenBox2d: AdvancedBox2dScreen, private val type: Static.Type): AbstractBody() {
//    override val name       = "win"
//    override val bodyDef    = BodyDef().apply {
//        type = BodyDef.BodyType.StaticBody
//    }
//    override val fixtureDef = FixtureDef()
//
//    override var actor: AdvancedGroup? = AImage(screenBox2d, getRegionByType())
//
//    override var id = when(type) {
//        Static.Type.BOMB  -> BodyId.Game.BOMB
//        Static.Type.X2    -> BodyId.Game.X2
//        Static.Type.X5    -> BodyId.Game.X5
//    }
//
//    // ---------------------------------------------------
//    // Logic
//    // ---------------------------------------------------
//
//    private fun getRegionByType() = when (type) {
//        Static.Type.BOMB -> screenBox2d.game.gameAssets.BOMB
//        Static.Type.X2   -> screenBox2d.game.gameAssets.X2
//        Static.Type.X5   -> screenBox2d.game.gameAssets.X5
//    }
//
//    object Static {
//        enum class Type {
//            BOMB, X2, X5
//        }
//    }
//}