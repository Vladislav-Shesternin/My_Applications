package fortunetiger.jogos.tighrino.game.screens

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.jogos.tighrino.game.LibGDXGame
import fortunetiger.jogos.tighrino.game.screens.level.ValLevel_1_Screen
import fortunetiger.jogos.tighrino.game.screens.level.ValLevel_2_Screen
import fortunetiger.jogos.tighrino.game.screens.level.ValLevel_3_Screen
import fortunetiger.jogos.tighrino.game.screens.level.ValLevel_4_Screen
import fortunetiger.jogos.tighrino.game.utils.TIME_ANIM
import fortunetiger.jogos.tighrino.game.utils.actor.animHide
import fortunetiger.jogos.tighrino.game.utils.actor.animShow
import fortunetiger.jogos.tighrino.game.utils.actor.setBounds
import fortunetiger.jogos.tighrino.game.utils.actor.setOnClickListener
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedScreen
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedStage
import fortunetiger.jogos.tighrino.game.utils.region

class ValLevelScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val levelImg = Image(game.allAssets.VAL_LEVEL).apply { color.a = 0f }

    private val listLevelScreenName = listOf(
        ValLevel_1_Screen::class.java.name,
        ValLevel_2_Screen::class.java.name,
        ValLevel_3_Screen::class.java.name,
        ValLevel_4_Screen::class.java.name,
    )

    override fun show() {
        super.show()
        setBackgrounds(game.loadingAssets.ValBackground.region)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addLevel()
        addBtns()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addLevel() {
        addActor(levelImg)
        levelImg.setBounds(0f, 272f, 1080f, 1594f)
        levelImg.animShow(TIME_ANIM)
    }

    private fun AdvancedStage.addBtns() {
        val positions = listOf(
            Vector2(51f, 1064f), Vector2(579f, 1064f),
            Vector2(51f, 364f), Vector2(579f, 364f),
        )

        positions.onEachIndexed { index, position ->
            val actor = Actor()
            addActor(actor)
            actor.setBounds(position, Vector2(432f, 573f))
            actor.setOnClickListener(game.soundUtil) {
                levelImg.animHide(TIME_ANIM) {
                    game.navigationManager.navigate(listLevelScreenName[index], ValLevelScreen::class.java.name)
                }
            }
        }
    }

}