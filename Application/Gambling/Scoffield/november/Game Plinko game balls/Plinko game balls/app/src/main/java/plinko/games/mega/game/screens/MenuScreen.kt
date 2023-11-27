package plinko.games.mega.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import plinko.games.mega.game.LibGDXGame
import plinko.games.mega.game.manager.MusicManager
import plinko.games.mega.game.manager.SoundManager
import plinko.games.mega.game.manager.SpriteManager
import plinko.games.mega.game.utils.TIME_ANIM_SCREEN_ALPHA
import plinko.games.mega.game.utils.actor.animHide
import plinko.games.mega.game.utils.actor.setBounds
import plinko.games.mega.game.utils.advanced.AdvancedScreen
import plinko.games.mega.game.utils.advanced.AdvancedStage
import plinko.games.mega.game.utils.font.FontParameter
import plinko.games.mega.game.utils.font.FontParameter.CharType
import plinko.games.mega.game.utils.runGDX
import plinko.games.mega.util.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import plinko.games.mega.game.actors.AKomete
import plinko.games.mega.game.actors.AMusor
import plinko.games.mega.game.actors.button.AButtonTextDef
import plinko.games.mega.game.actors.progress.AProgress
import plinko.games.mega.game.utils.HEIGHT_UI
import plinko.games.mega.game.utils.WIDTH_UI
import plinko.games.mega.game.utils.actor.animShow
import plinko.games.mega.game.utils.actor.setOnClickListener
import plinko.games.mega.game.utils.region
import java.util.Random
import plinko.games.mega.game.utils.Layout.Splash as LS

class MenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val parameter = FontParameter()


    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.splashAssets.BACGROUND.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPalanet()
        addKomete()
        addMusor()
        addMenu()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addPalanet() {
        val palanet = Image(game.splashAssets.PALANET)
        addActor(palanet)
        palanet.setBounds(LS.palanet)
    }

    private fun AdvancedStage.addKomete() {
        val palanet = AKomete(this@MenuScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMusor() {
        val palanet = AMusor(this@MenuScreen)
        addActor(palanet)
        palanet.setBounds(0f, 0f, WIDTH, HEIGHT)
    }

    private fun AdvancedStage.addMenu() {
        val menu = Image(game.gameAssets.MENU)
        addActor(menu)
        menu.setBounds(126f, 310f, 828f, 1298f)

        val texts = listOf(
            "PLAY",
            "LEVEL",
            "RULES",
            "SETTINGS",
            "EXIT",
        )

        val texts2 = listOf(
            if (Random().nextBoolean()) GameScreen_1::class.java.name else GameScreen_2::class.java.name,
            LevelScreen::class.java.name,
            RulesScreen::class.java.name,
            SettingsScreen::class.java.name,
            "EXIT",
        )

        val ls   = Label.LabelStyle(fontGeneratorMachineGunk.generateFont(parameter.setCharacters(CharType.LATIN).setSize(98)), Color.WHITE)

        var ny = 1198f

        repeat(5) {
            val btn = AButtonTextDef(this@MenuScreen, texts[it], ls)
            addActor(btn)
            btn.setBounds(281f, ny, 519f, 152f)
            ny -= 186f

            btn.setOnClickListener { navigateGo(texts2[it]) }
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun navigateGo(id: String) {
        stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
            if (id=="EXIT") game.navigationManager.exit()
            else game.navigationManager.navigate(id, this::class.java.name)
        }
    }


}