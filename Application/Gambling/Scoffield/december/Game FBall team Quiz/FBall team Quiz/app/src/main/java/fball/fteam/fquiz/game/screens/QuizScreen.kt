package fball.fteam.fquiz.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import fball.fteam.fquiz.R
import fball.fteam.fquiz.game.LibGDXGame
import fball.fteam.fquiz.game.actors.ASelectedImg
import fball.fteam.fquiz.game.utils.actor.animHide
import fball.fteam.fquiz.game.utils.actor.animShow
import fball.fteam.fquiz.game.utils.actor.disable
import fball.fteam.fquiz.game.utils.actor.setOnClickListener
import fball.fteam.fquiz.game.utils.advanced.AdvancedScreen
import fball.fteam.fquiz.game.utils.advanced.AdvancedStage
import fball.fteam.fquiz.game.utils.font.FontParameter
import fball.fteam.fquiz.game.utils.language.Language
import fball.fteam.fquiz.game.utils.region
import fball.fteam.fquiz.util.log
import java.util.Random

class QuizScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val stringeses = listOf(
        game.languageUtil.getStringResource(R.string.manchester_united),
        game.languageUtil.getStringResource(R.string.chelsea),
        game.languageUtil.getStringResource(R.string.manchester_city),
        game.languageUtil.getStringResource(R.string.liverpool),
        game.languageUtil.getStringResource(R.string.wolverhampton),
        game.languageUtil.getStringResource(R.string.leicester_city),
        game.languageUtil.getStringResource(R.string.brighton),
        game.languageUtil.getStringResource(R.string.west_bromwich),
        game.languageUtil.getStringResource(R.string.west_ham),
        game.languageUtil.getStringResource(R.string.southampton),
        game.languageUtil.getStringResource(R.string.milan),
        game.languageUtil.getStringResource(R.string.torino),
    )

    private val beckImg = Image(assets.beck)
    private val pisokImg = Image(assets.pisok)

    private val fontP = FontParameter().setCharacters(FontParameter.CharType.ALL).setSize(96)
    private val font  = if (game.languageUtil.languageFlow.value == Language.UK) fontSemBol.generateFont(fontP) else fontGeneratorInikaRegular.generateFont(fontP)

    private val lbl = Label(stringeses.random(), Label.LabelStyle(font, Color.WHITE))
    private val imgs = List(3) { ASelectedImg(this) }
    private val comd = assets.pipe.shuffled().take(3)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.pole.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBeck()
        addPisok()
        addLbl()
        addImgs()
        addComda()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBeck() {
        addActor(beckImg)
        beckImg.setBounds(0f, 625f, 86f, 75f)
        beckImg.setOnClickListener { stageUI.root.animHide(0.3f) { game.navigationManager.back() } }
    }

    private fun AdvancedStage.addPisok() {
        addActor(pisokImg)
        pisokImg.setBounds(182f, 378f, 1035f, 218f)
    }

    private fun AdvancedStage.addLbl() {
        addActor(lbl)
        lbl.setBounds(182f, 418f, 1035f, 138f)
        lbl.setAlignment(Align.center)
    }

    private fun AdvancedStage.addImgs() {
        var nx = 182f
        imgs.onEach {
            addActor(it)
            it.setBounds(nx, 29f, 293f, 293f)
            nx += 78f+293f
            it.setOnClickListener { aa ->
                if (Random().nextBoolean()) it.win() else it.fil()
                stageUI.root.disable()
                aa.addAction(Actions.sequence(
                    Actions.delay(0.5f),
                    Actions.run { game.navigationManager.navigate(this@QuizScreen::class.java.name) }
                ))
            }
        }
    }

    private fun AdvancedStage.addComda() {
        var nx = 249f
        comd.onEach { aa ->
            Image(aa).also {
                addActor(it)
                it.setBounds(nx, 95f, 160f, 160f)
                nx += 211f + 160f
                it.disable()
            }
        }
    }

}