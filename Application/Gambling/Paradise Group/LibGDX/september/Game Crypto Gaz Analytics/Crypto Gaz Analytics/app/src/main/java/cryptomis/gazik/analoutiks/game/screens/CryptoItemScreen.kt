package cryptomis.gazik.analoutiks.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
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

var cryptoItem: CryptoUtil.Crypto? = null

class CryptoItemScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Inco_Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()

    val labelStyle = Label.LabelStyle(generator.generateFont(parameter.setCharacters(CharType.ALL).setSize(37)), Color.WHITE)
    val labelStyle2 = Label.LabelStyle(generator.generateFont(parameter.setCharacters(CharType.ALL).setSize(33)), Color.WHITE)
    val labelStyle3 = Label.LabelStyle(generator.generateFont(parameter.setCharacters(CharType.ALL).setSize(51)), Color.WHITE)

    private val imgak        = Image(cryptoItem?.logo?.toTexture())
    private val nameLbl      = Label("${cryptoItem?.name.toString()} (${cryptoItem?.symbol.toString()})", labelStyle)
    private val priceTextLbl = Label("Price:", labelStyle2)
    private val priceLbl      = SpinningLabel(this, cryptoItem?.price.toString(), labelStyle3)
    private val imgBack       = Image(game.spriteUtil.nazd)
    private val grapape       = Image(game.spriteUtil.listikGGG.random())

    override fun show() {
        stageUI.root.animHide()
        //setUIBackground(drawerUtil.getRegion(Color.BLUE))
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(imgak, nameLbl, priceTextLbl, priceLbl, imgBack, grapape)
        imgak.setBounds(243f, 1124f, 261f, 261f)
        nameLbl.setBounds(97f, 1503f, 555f, 45f)
        nameLbl.setAlignment(Align.center)
        priceTextLbl.setBounds(328f, 1067f, 89f, 40f)
        priceLbl.setBounds(35f, 1013f, 679f, 55f)
        imgBack.setBounds(41f, 114f, 665f, 108f)
        imgBack.apply {
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {  game.navigationManager.back() }
            }
        }
        grapape.setBounds(41f, 346f, 665f, 447f)

    }

    private fun String?.toTexture(): Texture {
        val t = Glide.with(game.activity)
            .asFile()
            .load(this)
            .submit()
            .get().readBytes().toTexture()

        disposableSet.add(t)
        return t
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        cryptoItem = null
    }

}