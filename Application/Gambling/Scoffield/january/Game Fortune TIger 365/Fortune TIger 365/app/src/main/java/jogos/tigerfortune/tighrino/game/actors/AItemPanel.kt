package jogos.tigerfortune.tighrino.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedGroup
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.font.FontParameter

class AItemPanel(override val screen: AdvancedScreen): AdvancedGroup() {

    private val fontParameter = FontParameter().setCharacters(FontParameter.CharType.NUMBERS).setSize(48)
    private val font          = screen.fontGeneratorAngry.generateFont(fontParameter)

    private val itemImg  = Image()
    private val countLbl = Label("0", Label.LabelStyle(font, Color.WHITE))

    var counter = 0
        private set

    override fun addActorsOnGroup() {
        addItemImage()
        addCountLabel()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun addItemImage() {
        addActor(itemImg)
        itemImg.setBounds(103f, 7f, 50f, 50f)
    }

    private fun addCountLabel() {
        addActor(countLbl)
        countLbl.setBounds(0f, 0f, 78f, 53f)
        countLbl.setAlignment(Align.center)
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun update(count: Int, region: TextureRegion) {
        counter = count
        countLbl.setText(count)
        itemImg.drawable = TextureRegionDrawable(region)
    }

    fun caught() {
        if (counter > 0) {
            counter--
            countLbl.setText(counter)
        }

    }

}