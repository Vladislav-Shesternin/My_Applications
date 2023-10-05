package monska.makkers.conver.currinci.game.screens

import android.annotation.SuppressLint
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import monska.makkers.conver.currinci.game.LibGDXGame
import monska.makkers.conver.currinci.game.actors.label.SpinningLabel
import monska.makkers.conver.currinci.game.utils.GameColor
import monska.makkers.conver.currinci.game.utils.TIME_ANIM_SCREEN_ALPHA
import monska.makkers.conver.currinci.game.utils.actor.animHide
import monska.makkers.conver.currinci.game.utils.actor.animShow
import monska.makkers.conver.currinci.game.utils.actor.setOnClickListener
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedScreen
import monska.makkers.conver.currinci.game.utils.advanced.AdvancedStage
import monska.makkers.conver.currinci.game.utils.font.CharType
import monska.makkers.conver.currinci.game.utils.font.FontPath
import monska.makkers.conver.currinci.game.utils.font.setCharacters
import monska.makkers.conver.currinci.game.utils.font.setLinear
import monska.makkers.conver.currinci.game.utils.font.setSize
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

var fulkaNamke = ""
var fulkaCoste = 0.0

class UtemScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val generator = FreeTypeFontGenerator(Gdx.files.internal(FontPath.Jaldi))
    private val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()
    private val font86    = generator.generateFont(parameter.setCharacters(CharType.LATIN).setSize(86))
    private val font46    = generator.generateFont(parameter.setCharacters(CharType.LATIN).setSize(86))
    private val font86Num = generator.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+".").setSize(75))
    private val font37    = generator.generateFont(parameter.setCharacters("DatePM"+CharType.NUMBERS.chars+CharType.SYMBOLS.chars).setSize(22))

    private val iamge       = Image(game.spriteUtil.usd_to)
    private val UAH         = Label(fulkaNamke.drop(3), Label.LabelStyle(font86, Color.WHITE)).apply { setAlignment(Align.center) }
    private val fullNameLbl = SpinningLabel(this, valutaNamesta?.currencies?.get(UAH.text.toString()) ?: UAH.text.toString(), Label.LabelStyle(font46, GameColor.devid))
    private val fullCostLbl = SpinningLabel(this, fulkaCoste.toString(), Label.LabelStyle(font86Num, Color.WHITE))
    private val fullDataLbl = SpinningLabel(this, toDDDAta(), Label.LabelStyle(font37, GameColor.serik))

    private val imgBack = Image(game.spriteUtil.nazad)

    override fun show() {
        stageUI.root.animHide()
        //setUIBackground(drawerUtil.getRegion(Color.BLUE))
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(iamge, UAH, fullNameLbl, imgBack, fullNameLbl, fullCostLbl, fullDataLbl)

        iamge.setBounds(27f, 877f, 567f, 319f)
        UAH.setBounds(57f, 926f, 480f, 73f)
        fullNameLbl.setBounds(20f, 779f, 588f, 56f)
        fullCostLbl.setBounds(31f, 662f, 560f, 92f)
        fullDataLbl.setBounds(43f, 607f, 536f, 31f)

        imgBack.setBounds(34f, 112f, 552f, 89f)
        imgBack.apply {
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun toDDDAta(): String {
        val timeStampDate = Date((valuta?.timestamp?.toLong() ?: 0L) * 1000)
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss a")
        return "(Date: ${dateFormat.format(timeStampDate)} PM)"
    }

    override fun dispose() {
        super.dispose()
        generator.dispose()
        font86.dispose()
    }

}