package cryptomis.gazik.analoutiks.game.actors.scroll

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.bumptech.glide.Glide
import cryptomis.gazik.analoutiks.game.actors.label.SpinningLabel
import cryptomis.gazik.analoutiks.game.screens.CryptoItemScreen
import cryptomis.gazik.analoutiks.game.screens.CryptoListScreen
import cryptomis.gazik.analoutiks.game.screens.cryptoItem
import cryptomis.gazik.analoutiks.game.utils.CryptoUtil
import cryptomis.gazik.analoutiks.game.utils.TIME_ANIM_SCREEN_ALPHA
import cryptomis.gazik.analoutiks.game.utils.actor.animHide
import cryptomis.gazik.analoutiks.game.utils.actor.setOnTouchListener
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedGroup
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.toTexture
import java.util.Random

class CryptoGroup(
    override val screen: AdvancedScreen,
    val crypto: CryptoUtil.Crypto,
    labelStyle: LabelStyle,
    labelStyle2: LabelStyle,
): AdvancedGroup() {

    private val image   = Image(crypto.logo.toTexture())
    private val nameLbl = Label(crypto.name, labelStyle)
    private val nickLbl = Label(crypto.symbol, labelStyle2)

    override fun addActorsOnGroup() {
        addAndFillActor(Image(if (Random().nextBoolean()) screen.game.spriteUtil.gren else screen.game.spriteUtil.red))
        addActors(image, nameLbl, nickLbl)
        image.setBounds(35f, 20f, 104f, 104f)
        nameLbl.setBounds(153f, 79f, 300f, 23f)
        nickLbl.setBounds(153f, 43f, 300f, 20f)


        setOnTouchListener {
            cryptoItem = crypto
            screen.stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                screen.game.navigationManager.navigate(CryptoItemScreen::class.java.name,CryptoListScreen::class.java.name)
            }
        }
    }

    private fun String?.toTexture(): Texture {
        val t = Glide.with(screen.game.activity)
            .asFile()
            .load(this)
            .submit()
            .get().readBytes().toTexture()

        disposableSet.add(t)
        return t
    }



}