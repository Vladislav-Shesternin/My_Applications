package pro.ultimate.saver.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import pro.ultimate.saver.game.LibGDXGame
import pro.ultimate.saver.game.manager.CalendarGroup
import pro.ultimate.saver.game.manager.ZaplataGroup
import pro.ultimate.saver.game.utils.TIME_ANIM_SCREEN_ALPHA
import pro.ultimate.saver.game.utils.actor.animHide
import pro.ultimate.saver.game.utils.actor.animShow
import pro.ultimate.saver.game.utils.actor.disable
import pro.ultimate.saver.game.utils.actor.enable
import pro.ultimate.saver.game.utils.actor.setOnClickListener
import pro.ultimate.saver.game.utils.advanced.AdvancedScreen
import pro.ultimate.saver.game.utils.advanced.AdvancedStage
import pro.ultimate.saver.game.utils.font.CharType
import pro.ultimate.saver.game.utils.font.FontPath
import pro.ultimate.saver.game.utils.font.setCharacters
import pro.ultimate.saver.game.utils.font.setLinear
import pro.ultimate.saver.game.utils.font.setSize
import pro.ultimate.saver.game.utils.numStr
import pro.ultimate.saver.game.utils.region

class HuedrochevoScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val barbe12 = Actor()
    private val barbe21 = Actor()

    private val regPos  = game.spriteUtil.postuplen
    private val regRas  = game.spriteUtil.rshodiki
    private val imgPP   = Image(regPos)

    private val generatorRG = FreeTypeFontGenerator(Gdx.files.internal(FontPath.RG))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font14 = generatorRG.generateFont(parameter.setCharacters(CharType.ALL).setSize(14))

    private val lblVedSum = Label("Введите сумму", Label.LabelStyle(font14, Color.valueOf("909090")))
    private val lblVibCat = Label("Выберите категорию", Label.LabelStyle(font14, Color.valueOf("909090")))
    private val lblSegodn = Label("Сегодня", Label.LabelStyle(font14, Color.valueOf("909090")))
    private val lblNalikm = Label("Наличка", Label.LabelStyle(font14, Color.valueOf("909090")))

    private val calendar = CalendarGroup(this)
    private val zaplata  = ZaplataGroup(this)
    private val nalicha  = NalichkaGroup(this)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Postuplenia.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        pisePleTovo()
        addPoRos()
        addLblb()
        addCalendar()
        addZaplata()
        addNalicha()
    }

    override fun dispose() {
        super.dispose()
        generatorRG.dispose()
        font14.dispose()
    }

    private fun AdvancedStage.pisePleTovo( ) {
        addActors(barbe12, barbe21)
        barbe12.apply {
            setBounds(6f, 697f,59f, 48f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            }
        }
        barbe21.apply {
            setBounds(20f, 50f,320f, 52f)
            setOnClickListener {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
            }
        }
    }

    private fun AdvancedStage.addPoRos() {
        addActor(imgPP)
        imgPP.setBounds(20f, 625f, 320f, 40f)
        val pos = Actor()
        val ros = Actor()
        addActors(pos, ros)
        pos.apply {
            setBounds(20f, 625f, 157f, 40f)
            setOnClickListener { imgPP.drawable = TextureRegionDrawable(regPos) }
        }
        ros.apply {
            setBounds(183f, 625f, 157f, 40f)
            setOnClickListener { imgPP.drawable = TextureRegionDrawable(regRas) }
        }
    }

    private fun AdvancedStage.addLblb() {
        addActors(lblVedSum, lblVibCat, lblSegodn, lblNalikm)
        lblVedSum.apply {
            setBounds(80f, 568f, 245f, 10f)
            setOnClickListener {
                animHide(0.3f)
                lblVedSum.apply {
                    style.fontColor = Color.WHITE
                    setText("${numStr(100, 10_000,1)}Q")
                    animShow(0.5f)
                }
            }
        }
        lblVibCat.apply {
            setBounds(80f, 521f, 245f, 10f)
            setOnClickListener {
                animHide(0.3f)
                zaplata.animShow(0.3f)
                zaplata.enable()
            }
        }
        lblSegodn.apply {
            setBounds(80f, 474f, 245f, 10f)
            setOnClickListener {
                animHide(0.3f)
                calendar.animShow(0.3f)
                calendar.enable()
            }
        }
        lblNalikm.apply {
            setBounds(80f, 427f, 245f, 10f)
            setOnClickListener {
                animHide(0.3f)
                nalicha.animShow(0.3f)
                nalicha.enable()
            }
        }
    }

    private fun AdvancedStage.addCalendar() {
        addActor(calendar)
        calendar.apply {
            setBounds(11f, 342f, 337f, 334f)
            disable()
            animHide()
            bbbbblock = {
                lblSegodn.apply {
                    style.fontColor = Color.WHITE
                    setText("${numStr(1, 31,1)} ${dateList.random()} 2023")
                    animShow(0.5f)
                }
            }
        }
    }

    private fun AdvancedStage.addZaplata() {
        addActor(zaplata)
        zaplata.apply {
            setBounds(11f, 342f, 337f, 334f)
            disable()
            animHide()
            bbbbblock = {
                lblVibCat.apply {
                    style.fontColor = Color.WHITE
                    setText(zapList.random())
                    animShow(0.5f)
                }
            }
        }
    }

    private fun AdvancedStage.addNalicha() {
        addActor(nalicha)
        nalicha.apply {
            setBounds(11f, 342f, 337f, 334f)
            disable()
            animHide()
            bbbbblock = {
                lblNalikm.apply {
                    style.fontColor = Color.WHITE
                    setText(zapList.random())
                    animShow(0.5f)
                }
            }
        }
    }

}