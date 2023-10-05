package cryptomis.gazik.analoutiks.game.actors.scroll

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import cryptomis.gazik.analoutiks.game.manager.SpriteManager
import cryptomis.gazik.analoutiks.game.manager.util.SpriteUtil
import cryptomis.gazik.analoutiks.game.utils.CryptoUtil
import cryptomis.gazik.analoutiks.game.utils.GameColor
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedGroup
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.font.CharType
import cryptomis.gazik.analoutiks.game.utils.font.FontPath
import cryptomis.gazik.analoutiks.game.utils.font.setCharacters
import cryptomis.gazik.analoutiks.game.utils.font.setSize
import cryptomis.gazik.analoutiks.game.utils.runGDX
import cryptomis.gazik.analoutiks.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CryptoScrollPane(override val screen: AdvancedScreen) : AdvancedGroup() {

    private val verticalGroup = VerticalGroup(screen,21f,alignment = VerticalGroup.Alignment.TOP,direction = VerticalGroup.Direction.DOWN)
    private val scrollPane    = ScrollPane(verticalGroup)

    private val incon = Image(screen.game.spriteUtil.title)

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Inco_Regular))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()

    private val ls = Label.LabelStyle(generator.generateFont(parameter.setCharacters(CharType.ALL).setSize(33)), Color.WHITE)
    private val ls2 = Label.LabelStyle(generator.generateFont(parameter.setCharacters(CharType.ALL).setSize(23)), GameColor.grenee)

    override fun addActorsOnGroup() {
        addActor(incon)
        incon.setBounds(41f, 1448f, 357f, 100f)

        addAndFillActor(scrollPane)

        screen.game.activity.lottie.showLoader()
        CryptoUtil.cryptoList.onEach {
            verticalGroup.addActor(
                CryptoGroup(screen, it, ls, ls2).apply { setSize(663f, 145f) }
            )
        }
        screen.game.activity.lottie.hideLoader()
    }

    override fun dispose() {
        super.dispose()
        verticalGroup.dispose()
        generator.dispose()

    }

}