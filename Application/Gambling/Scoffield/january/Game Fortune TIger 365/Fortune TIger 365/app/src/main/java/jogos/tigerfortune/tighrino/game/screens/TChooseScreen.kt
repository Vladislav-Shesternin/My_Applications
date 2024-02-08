package jogos.tigerfortune.tighrino.game.screens

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import jogos.tigerfortune.tighrino.game.LibGDXGame
import jogos.tigerfortune.tighrino.game.actors.button.AButton
import jogos.tigerfortune.tighrino.game.actors.checkbox.ACheckBox
import jogos.tigerfortune.tighrino.game.utils.TIME_ANIM_SCREEN_ALPHA
import jogos.tigerfortune.tighrino.game.utils.actor.animHide
import jogos.tigerfortune.tighrino.game.utils.actor.animShow
import jogos.tigerfortune.tighrino.game.utils.actor.setOnClickListener
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedScreen
import jogos.tigerfortune.tighrino.game.utils.advanced.AdvancedStage
import jogos.tigerfortune.tighrino.game.utils.region

class TChooseScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var TIGER_TYPE = TigerType.TIGER_1
            private set
        var BACKGROUND_TYPE = BackgroundType.B1
            private set
    }

    private val tigerList = listOf(
        game.gameAssets.t1,
        game.gameAssets.t2,
    )

    private var characterIndex  = 0
    private var backgroundIndex = 0

    private val panel      = Image(game.gameAssets.F_CHOOSE)
    private val character  = Image(tigerList[characterIndex])
    private val background = Image(game.gameAssets.backgrounds[characterIndex])

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(game.gameAssets.B_MAIN.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(panel)
        panel.setBounds(49f, 45f, 983f, 1675f)

        addBack()
        addMusic()
        addCharacter()
        addBackground()
        addStart()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@TChooseScreen, AButton.Static.Type.EXIT)
        addActor(menu)
        menu.setBounds(44f, 1754f, 114f, 119f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@TChooseScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(925f, 1754f, 114f, 119f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addCharacter() {
        addActor(character)
        character.setBounds(403f, 949f, 273f, 293f)

        val back = Actor()
        val next = Actor()

        addActors(back, next)

        back.apply {
            setBounds(205f, 1079f, 103f, 107f)

            setOnClickListener(game.soundUtil) {
                characterIndex = if ((characterIndex - 1) < 0) tigerList.lastIndex else characterIndex - 1
                changeTiger()
            }
        }
        next.apply {
            setBounds(772f, 1079f, 103f, 107f)

            setOnClickListener(game.soundUtil) {
                characterIndex = if ((characterIndex + 1) > tigerList.lastIndex) 0 else characterIndex + 1
                changeTiger()
            }
        }
    }

    private fun AdvancedStage.addBackground() {
        addActor(background)
        background.setBounds(350f, 419f, 381f, 218f)

        val back = Actor()
        val next = Actor()

        addActors(back, next)

        back.apply {
            setBounds(205f, 488f, 103f, 107f)

            setOnClickListener(game.soundUtil) {
                backgroundIndex = if ((backgroundIndex - 1) < 0) game.gameAssets.backgrounds.lastIndex else backgroundIndex - 1
                changeBackground()
            }
        }
        next.apply {
            setBounds(772f, 488f, 103f, 107f)

            setOnClickListener(game.soundUtil) {
                backgroundIndex = if ((backgroundIndex + 1) > game.gameAssets.backgrounds.lastIndex) 0 else backgroundIndex + 1
                changeBackground()
            }
        }
    }

    private fun AdvancedStage.addStart() {
        val start = Actor()
        addActor(start)
        start.setBounds(313f, 45f, 454f, 139f)

        start.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(TGameScreen::class.java.name, TChooseScreen::class.java.name) }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun changeTiger() {
        character.drawable = TextureRegionDrawable(tigerList[characterIndex])
        TIGER_TYPE         = TigerType.values()[characterIndex]
    }

    private fun changeBackground() {
        background.drawable = TextureRegionDrawable(game.gameAssets.backgrounds[backgroundIndex])
        BACKGROUND_TYPE     = BackgroundType.values()[backgroundIndex]
    }

    // ---------------------------------------------------
    // classes
    // ---------------------------------------------------

    enum class TigerType {
        TIGER_1, TIGER_2
    }

    enum class BackgroundType {
        B1, B2, B3, B4
    }

}