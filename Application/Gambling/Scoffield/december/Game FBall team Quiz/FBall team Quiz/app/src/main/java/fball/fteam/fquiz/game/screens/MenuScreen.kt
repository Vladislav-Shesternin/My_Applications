package fball.fteam.fquiz.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import fball.fteam.fquiz.game.LibGDXGame
import fball.fteam.fquiz.game.utils.actor.animHide
import fball.fteam.fquiz.game.utils.actor.animShow
import fball.fteam.fquiz.game.utils.actor.setOnClickListener
import fball.fteam.fquiz.game.utils.advanced.AdvancedScreen
import fball.fteam.fquiz.game.utils.advanced.AdvancedStage
import fball.fteam.fquiz.game.utils.language.Language
import fball.fteam.fquiz.game.utils.region

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val menuImg = Image(if (game.languageUtil.languageFlow.value == Language.UK) assets.btnrs_uk else assets.btnrs )

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.pole.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addMenu() {
        addActor(menuImg)
        menuImg.setBounds(460f, 134f, 480f, 432f)

        var ny = 438f
        repeat(3) { index ->
            Actor().also { a ->
                addActor(a)
                a.apply {
                    setBounds(460f, ny, 480f, 128f)
                    ny -= 24f+128f

                    setOnClickListener { navTo(index) }
                }
            }
        }
    }

    private fun navTo(index: Int) {
        when (index) {
            0 -> navigate(QuizScreen::class.java.name)
            1 -> navigate(LanguageScreen::class.java.name)
            2 -> navigate("exit")
            else -> {}
        }
    }

    private fun navigate(namestr: String) {
        stageUI.root.animHide(0.3f) {
            if (namestr=="exit") game.navigationManager.exit()
            else game.navigationManager.navigate(namestr, this::class.java.name)
        }
    }

}