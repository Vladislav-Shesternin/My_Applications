package avia.adventure.wings.game.screens

import avia.adventure.wings.game.LibGDXGame
import avia.adventure.wings.game.actors.button.AButton
import avia.adventure.wings.game.actors.checkbox.ACheckBox
import avia.adventure.wings.game.utils.TIME_ANIM_SCREEN_ALPHA
import avia.adventure.wings.game.utils.actor.animHide
import avia.adventure.wings.game.utils.actor.animShow
import avia.adventure.wings.game.utils.actor.setOnClickListener
import avia.adventure.wings.game.utils.advanced.AdvancedScreen
import avia.adventure.wings.game.utils.advanced.AdvancedStage
import avia.adventure.wings.game.utils.region
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image

class OriginalShopScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        var AVIA = AviaType._800
            private set
    }


    override fun show() {
        stageUI.root.animHide()
        setUIBackground(game.gameAssets.MainBackground.region)
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addBack()
        addMusic()
        addAvias()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addBack() {
        val menu = AButton(this@OriginalShopScreen, AButton.Static.Type.MENU)
        addActor(menu)
        menu.setBounds(22f, 1281f, 99f, 99f)

        menu.setOnClickListener(game.soundUtil) {
            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() }
        }
    }

    private fun AdvancedStage.addMusic() {
        val music = ACheckBox(this@OriginalShopScreen, ACheckBox.Static.Type.MUSIC)
        addActor(music)
        music.setBounds(520f, 1281f, 98f, 98f)

        game.musicUtil.music?.let { mmm ->
            if (mmm.isPlaying.not()) music.check(false)

            music.setOnCheckListener { if (it) mmm.pause() else mmm.play() }
        }
    }

    private fun AdvancedStage.addAvias() {
        val shop = Image(game.gameAssets.shop)
        addActor(shop)
        shop.setBounds(50f, 161f, 546f, 1128f)

        var nx = 50f
        var ny = 498f

        repeat(4) { i ->
            Actor().also { a ->
                addActor(a)
                a.setBounds(nx, ny, 232f, 316f)
                nx += 74f+232f
                if (i.inc() % 2 == 0) {
                    nx = 50f
                    ny -= 21f+316f
                }
                a.setOnClickListener(game.soundUtil) {
                    AVIA = AviaType.values()[i]
                    stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                        game.navigationManager.navigate(OriginalGameScreen::class.java.name, OriginalShopScreen::class.java.name)
                    }
                }
            }
        }
    }

    // ---------------------------------------------------
    // classes
    // ---------------------------------------------------

    enum class AviaType(val avia_index: Int) {
        _800(1), _1500(2), _2000(3), _3500(4)
    }

}