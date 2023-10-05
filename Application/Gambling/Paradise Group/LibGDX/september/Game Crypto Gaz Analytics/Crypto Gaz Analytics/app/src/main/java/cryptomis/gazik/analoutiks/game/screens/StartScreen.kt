package cryptomis.gazik.analoutiks.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.bumptech.glide.Glide
import cryptomis.gazik.analoutiks.game.LibGDXGame
import cryptomis.gazik.analoutiks.game.actors.label.SpinningLabel
import cryptomis.gazik.analoutiks.game.actors.scroll.CryptoScrollPane
import cryptomis.gazik.analoutiks.game.manager.util.SpriteUtil
import cryptomis.gazik.analoutiks.game.utils.CryptoUtil
import cryptomis.gazik.analoutiks.game.utils.TIME_ANIM_SCREEN_ALPHA
import cryptomis.gazik.analoutiks.game.utils.actor.animHide
import cryptomis.gazik.analoutiks.game.utils.actor.animShow
import cryptomis.gazik.analoutiks.game.utils.actor.setOnClickListener
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedStage
import cryptomis.gazik.analoutiks.game.utils.font.CharType
import cryptomis.gazik.analoutiks.game.utils.font.FontPath
import cryptomis.gazik.analoutiks.game.utils.font.setCharacters
import cryptomis.gazik.analoutiks.game.utils.font.setSize
import cryptomis.gazik.analoutiks.game.utils.region
import cryptomis.gazik.analoutiks.game.utils.toTexture
import cryptomis.gazik.analoutiks.util.log

class StartScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val actore = Actor()

    override fun show() {



        stageUI.root.animHide()
        setUIBackground(SpriteUtil.CommonAssets().BACKGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(actore)
        actore.apply {
            setBounds(42f, 230f,664f, 108f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {  game.navigationManager.navigate(CryptoListScreen::class.java.name) }
            }
        }
    }

}