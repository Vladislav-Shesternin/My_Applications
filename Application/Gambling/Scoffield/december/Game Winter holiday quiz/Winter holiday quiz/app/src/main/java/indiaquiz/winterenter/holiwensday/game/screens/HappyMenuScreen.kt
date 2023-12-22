package indiaquiz.winterenter.holiwensday.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Image
import indiaquiz.winterenter.holiwensday.game.LibGDXGame
import indiaquiz.winterenter.holiwensday.game.actors.ACheckBox
import indiaquiz.winterenter.holiwensday.game.utils.actor.animHide
import indiaquiz.winterenter.holiwensday.game.utils.actor.animShow
import indiaquiz.winterenter.holiwensday.game.utils.actor.disable
import indiaquiz.winterenter.holiwensday.game.utils.actor.setOnClickListener
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedScreen
import indiaquiz.winterenter.holiwensday.game.utils.advanced.AdvancedStage
import indiaquiz.winterenter.holiwensday.game.utils.region

class HappyMenuScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val assets = game.assets

    private val makeMerryImg = Image(assets.make_merry)
    private val musicPImg    = Image(assets.btn_mini)
    private val exitPImg     = Image(assets.btn_mini)
    private val musicCb      = ACheckBox(this, ACheckBox.Static.Type.Music)
    private val exitImg      = Image(assets.exit)

    override fun show() {
        stageUI.root.animHide()
        setBackgrounds(assets.Menu.region)
        super.show()
        stageUI.root.animShow(0.3f)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addButtons()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addButtons() {
        addActor(makeMerryImg)
        makeMerryImg.apply {
            setBounds(629f, 289f, 243f, 120f)
            setOnClickListener { navToStr(HappyQuizScreen::class.java.name) }
        }

        addActors(musicPImg, exitPImg)
        musicPImg.apply {
            setBounds(425f, 310f, 156f, 77f)
            setOnClickListener {
                if (musicCb.checkFlow.value) {
                    musicCb.uncheck()
                    game.musicUtil.music?.play()
                } else {
                    musicCb.check()
                    game.musicUtil.music?.pause()
                }
            }
        }
        exitPImg.apply {
            setBounds(920f, 310f, 156f, 77f)
            setOnClickListener { navToStr("happy") }
        }

        addActors(musicCb, exitImg)
        musicCb.apply {
            game.musicUtil.music?.let { if (it.isPlaying.not()) check(false) }
            setBounds(478f, 324f, 50f, 50f)
            disable()
        }
        exitImg.apply {
            setBounds(975f, 326f, 46f, 46f)
            disable()
        }
    }

    private fun navToStr(nameString: String) {
        stageUI.root.animHide(0.3f) {
            if (nameString=="happy") game.navigationManager.exit()
            else game.navigationManager.navigate(nameString, this::class.java.name)
        }
    }

}