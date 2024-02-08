package fortunetiger.com.tighrino.game.screens.common

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import fortunetiger.com.tighrino.game.LibGDXGame
import fortunetiger.com.tighrino.game.screens.level.IncasEasyScreen
import fortunetiger.com.tighrino.game.screens.level.IncasHardScreen
import fortunetiger.com.tighrino.game.screens.level.IncasNormalScreen
import fortunetiger.com.tighrino.game.utils.actor.setOnClickListener
import fortunetiger.com.tighrino.game.utils.advanced.AdvancedGroup

class IncasLevelScreen(_game: LibGDXGame): ICommonScreen(_game, Static.TypeScreen.Level) {

    private val levelImg = Image(game.allAssets.enh)

    private val levels = listOf(
        IncasEasyScreen::class.java.name,
        IncasNormalScreen::class.java.name,
        IncasHardScreen::class.java.name,
    )

    override fun AdvancedGroup.addActorsOnTmpGroup() {
        addLevelImg()
    }

    // ---------------------------------------------------
    // Add Actor
    // ---------------------------------------------------

    private fun AdvancedGroup.addLevelImg() {
        addActor(levelImg)
        levelImg.setBounds(307f, 585f, 494f, 574f)

        var ny = 1001f

        levels.onEach { nameScreen ->
            val btn = Actor()
            addActor(btn)
            btn.setBounds(307f, ny, 494f, 158f)
            ny -= 50f + 158f

            btn.setOnClickListener(game.soundUtil) {
                game.navigationManager.navigate(nameScreen, IncasLevelScreen::class.java.name)
            }
        }
    }

}