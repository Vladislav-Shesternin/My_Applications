package fortune.tiger.avia.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import fortune.tiger.avia.game.LibGDXGame
import fortune.tiger.avia.game.actors.PanelBigi
import fortune.tiger.avia.game.actors.Pipka
import fortune.tiger.avia.game.actors.checkbox.ACheckBox
import fortune.tiger.avia.game.actors.image.AImageWithId
import fortune.tiger.avia.game.actors.image.AMovableImage2
import fortune.tiger.avia.game.actors.progress.ATimer
import fortune.tiger.avia.game.utils.Layout
import fortune.tiger.avia.game.utils.TIME_ANIM_SCREEN_ALPHA
import fortune.tiger.avia.game.utils.actor.animHide
import fortune.tiger.avia.game.utils.actor.animShow
import fortune.tiger.avia.game.utils.actor.disable
import fortune.tiger.avia.game.utils.actor.setBounds
import fortune.tiger.avia.game.utils.actor.setOnClickListener
import fortune.tiger.avia.game.utils.advanced.AdvancedGroup
import fortune.tiger.avia.game.utils.advanced.AdvancedScreen
import fortune.tiger.avia.game.utils.advanced.AdvancedStage
import fortune.tiger.avia.game.utils.region
import fortune.tiger.avia.game.utils.runGDX
import fortune.tiger.avia.util.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Random

class IgrabelnaGraScreen2(override val game: LibGDXGame) : AdvancedScreen() {

    private val winCounter = MutableStateFlow(0)
    private val faiCounter = MutableStateFlow(0)

    private val domImg             = Image(game.gameAssets.DOMIK)
    private val panelBigi          = PanelBigi(this)
    private val itemImgList        = List(6) { AImageWithId(this) }
    private val pipkaList          = List(6) { Pipka(this) }
    private val itemImgMovableList = List(6) { AMovableImage2(this, winCounter, faiCounter) }
    private val nextImg            = Image(game.gameAssets.NTXT)
    private val stars              = Image(game.gameAssets.STAR_TRIPLE)
    private val timer              = ATimer(this).apply { color.a = 0f }
    private val cbPause            = ACheckBox(this, ACheckBox.Static.Type.PAUSE)

    // Field
    private val regions = game.gameAssets.ITEM_LIST.shuffled()
    private val ids     = listOf(0,1,2,3,4,5).shuffled()

    private val starsRegions = listOf(
        game.gameAssets.STAR_TWO,
        game.gameAssets.STAR_ONE,
        game.gameAssets.STAR_ZERO,
    )

    override fun show() {
        setBackgrounds(game.gameAssets.IGRABELNAGRAIMG.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addPanelBigi()
        addStars()
        addTimer()
        addPause()
        addNextImg()
        addDom()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addDom() {
        addActor(domImg)
        domImg.apply {
            setBounds(60f, 1725f, 156f, 164f)
            setOnClickListener(game.soundUtil) {
                stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                    game.navigationManager.back()
                }
            }
        }
    }

    private fun AdvancedStage.addPanelBigi() {
        addActor(panelBigi)
        panelBigi.apply {
            setBounds(19f, 656f, 1042f, 607f)
            addImgList()
        }
    }

    private fun AdvancedGroup.addImgList() {
        itemImgList.onEachIndexed { index, img ->
            addActor(img)
            img.id       = ids[index]
            img.drawable = TextureRegionDrawable(regions[index])
            img.setBounds(Layout.Igrushes.ids2[index], Vector2(200f, 200f))
        }
    }

    private fun AdvancedStage.addNextImg() {
        addActor(nextImg)
        nextImg.apply {
            setBounds(295f, 58f, 489f, 211f)
            setOnClickListener(game.soundUtil) {
                disable()
                animHide(0.7f)

                cbPause.enable()
                timer.animShow(0.7f) { timer.startTimer {
                    faiCounter.value = 3
                } }

                panelBigi.apply {
                    disable()
                    animHide(0.7f) { addPipkaList() }
                }
            }
        }
    }

    private fun AdvancedStage.addPipkaList() {
        coroutine?.launch {
            pipkaList.onEachIndexed { index, img ->
                runGDX {
                    img.color.a = 0f
                    img.id      = ids[index]
                    addActor(img)
                    img.setBounds(Layout.Igrushes.pipla2[index], Vector2(292f, 384f))
                }
                runGDX { img.animShow(0.3f) }
                delay(200)
            }
            runGDX {
                log("pod = ${pipkaList.map { it.id }}")
                addMovableImgList()
            }

        }
    }

    private fun AdvancedStage.addMovableImgList() {
        val pos = Layout.Igrushes.movable2.shuffled()

        itemImgMovableList.onEachIndexed { index, img ->
            img.color.a = 0f
            addActor(img)
            img.id       = ids[index]
            log("i = ${img.id}")
            img.drawable = itemImgList[index].drawable
            img.setBounds(pos[index], Vector2(200f, 200f))

            img.pipkaList = pipkaList.toMutableList()
            img.animShow(0.5f)
        }

        log("mov = ${itemImgMovableList.map { it.id }}")
    }

    private fun AdvancedStage.addStars() {
        addActor(stars)
        stars.setBounds(264f, 1690f, 551f, 127f)
        coroutine?.launch {
            launch {
                winCounter.collect { win ->
                    runGDX {
                        if (win == 6) {
                            log("WIN")
                            addResult(true)
                        }
                    }
                }
            }
            launch {
                faiCounter.collect { fail ->
                    runGDX {
                        stars.drawable = TextureRegionDrawable(starsRegions[fail-1])
                        if (fail == 3) {
                            log("FAIL")
                            addResult(false)
                        }
                    }
                }
            }
        }
    }

    private fun AdvancedStage.addTimer() {
        addActor(timer)
        timer.setBounds(322f, 1453f, 435f, 111f)
    }

    private fun AdvancedStage.addPause() {
        addActor(cbPause)
        cbPause.setBounds(864f, 1725f, 156f, 164f)
        cbPause.disable()

        cbPause.setOnCheckListener { timer.isPause = it }
    }

    private var v = true
    private fun AdvancedStage.addResult(flag: Boolean) {
        if (!v) return
        v = false
        timer.isPause    = true
        winCounter.value = 0

        if (flag) game.soundUtil.apply { play(STAR_PEREMOGA) } else game.soundUtil.apply { play(STRS_PROIGRAL) }

        val strRegions = listOf(
            game.gameAssets.VICTORY,
            game.gameAssets.GOODJOB,
            game.gameAssets.STEEPLY,
            game.gameAssets.LOSE,
        )

        val backgroundRed = Image(drawerUtil.getRegion(if (flag) Color.valueOf("55FF2A").apply { a = 0.42f } else Color.valueOf("FF352A").apply { a = 0.42f })).apply { color.a = 0f }
        addAndFillActor(backgroundRed)
        backgroundRed.animShow(TIME_ANIM_SCREEN_ALPHA)

        val starik = Image(strRegions[faiCounter.value])
        addActor(starik)
        starik.setBounds(86f, 649f, 907f, 623f)

        faiCounter.value = 0

        val domImg = Image(game.gameAssets.DOMIK)
        addActor(domImg)
        domImg.apply {
            setBounds(60f, 1725f, 156f, 164f)
            setOnClickListener(game.soundUtil) { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.back() } }
        }

        if (flag) {
            val nextImg = Image(game.gameAssets.NTXT).apply { color.a = 0f }
            addActor(nextImg)
            nextImg.animShow(TIME_ANIM_SCREEN_ALPHA)
            nextImg.apply {
                setBounds(295f, 58f, 489f, 211f)
                setOnClickListener(game.soundUtil) { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(if (Random().nextBoolean()) IgrabelnaGraScreen::class.java.name else IgrabelnaGraScreen2::class.java.name) } }
            }
        } else {
            val restartImg = Image(game.gameAssets.RESTART).apply { color.a = 0f }
            addActor(restartImg)
            restartImg.animShow(TIME_ANIM_SCREEN_ALPHA)
            restartImg.apply {
                setBounds(233f, 42f, 613f, 233f)
                setOnClickListener(game.soundUtil) { stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) { game.navigationManager.navigate(IgrabelnaGraScreen2::class.java.name) } }
            }
        }

    }

}