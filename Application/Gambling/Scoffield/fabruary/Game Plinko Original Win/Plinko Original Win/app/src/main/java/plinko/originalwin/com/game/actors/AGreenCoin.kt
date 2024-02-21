package plinko.originalwin.com.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import plinko.originalwin.com.game.utils.actor.animHide
import plinko.originalwin.com.game.utils.actor.animShow
import plinko.originalwin.com.game.utils.advanced.AdvancedGroup
import plinko.originalwin.com.game.utils.advanced.AdvancedScreen
import plinko.originalwin.com.game.utils.font.FontParameter

class AGreenCoin(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS.chars + "+").setSize(20)
    private val font          = screen.fontGenerator_ZenDots_Regular.generateFont(fontParameter)

    private val coinImg  = Image(screen.game.allAssets.green_coin)
    private val textLbl  = Label("", Label.LabelStyle(font, Color.WHITE))

    override fun addActorsOnGroup() {
        addAndFillActor(coinImg)
        addTextLbl()
    }

    private fun addTextLbl() {
        textLbl.color.a = 0f
        addActor(textLbl)
        textLbl.setBounds(8f, 13f, 31f, 24f)
        textLbl.setAlignment(Align.center)
    }

    fun animTake() {
        coinImg.animHide(0.1f)
        textLbl.animShow(0.1f)
        textLbl.addAction(Actions.sequence(
            Actions.moveTo(8f, 50f, 0.3f, Interpolation.slowFast),
            Actions.fadeOut(0.2f),
            Actions.moveTo(8f, 13f)
        ))
    }

    fun animHideCoin() {
        coinImg.animHide(0.1f)
    }

    fun animStart() {
        coinImg.animShow(0.1f)
    }



    fun setValue(value: Int) {
        textLbl.setText("+$value")
    }

}