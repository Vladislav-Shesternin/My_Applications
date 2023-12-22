package newyearpuz.lessons.forditky.game.screens

import newyearpuz.lessons.forditky.game.LibGDXGame
import newyearpuz.lessons.forditky.game.actors.button.AButton
import newyearpuz.lessons.forditky.game.utils.TIME_ANIM_SCREEN_ALPHA
import newyearpuz.lessons.forditky.game.utils.actor.animHide
import newyearpuz.lessons.forditky.game.utils.actor.animShow
import newyearpuz.lessons.forditky.game.utils.actor.setOnClickListener
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedScreen
import newyearpuz.lessons.forditky.game.utils.advanced.AdvancedStage
import newyearpuz.lessons.forditky.game.utils.region

var PLevel = 3
    private set

class PuzzleMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.back.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addMenus()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addMenus() {
        var ny = 1315f
        listOf(
            AButton(this@PuzzleMenuScreen, AButton.Static.Type._3),
            AButton(this@PuzzleMenuScreen, AButton.Static.Type._4),
            AButton(this@PuzzleMenuScreen, AButton.Static.Type._5),
            AButton(this@PuzzleMenuScreen, AButton.Static.Type.exit),
        ).onEachIndexed { index, aButton ->
            addActor(aButton)
            aButton.apply {
                setBounds(291f, ny, 497f, 279f)
                ny -= 50f+279

                setOnClickListener(game.soundUtil) {
                    PLevel = 3+index
                    navToScrNam(if (index==3) "ExIt" else PuzzleScreen::class.java.name)
                }
            }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navToScrNam(scrNam: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (scrNam == "ExIt") game.navigationManager.exit()
            else game.navigationManager.navigate(scrNam, PuzzleMenuScreen::class.java.name) }
    }


}