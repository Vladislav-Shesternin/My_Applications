package golov.lomaka.sudjoken.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import golov.lomaka.sudjoken.R
import golov.lomaka.sudjoken.game.ACheckBox
import golov.lomaka.sudjoken.game.ACheckBoxGroup
import golov.lomaka.sudjoken.game.LibGDXGame
import golov.lomaka.sudjoken.game.utils.GameColor
import golov.lomaka.sudjoken.game.utils.Layout
import golov.lomaka.sudjoken.game.utils.actor.animHiden
import golov.lomaka.sudjoken.game.utils.actor.animShown
import golov.lomaka.sudjoken.game.utils.actor.disable
import golov.lomaka.sudjoken.game.utils.actor.setBounds
import golov.lomaka.sudjoken.game.utils.actor.setOnClickListener
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedScreen
import golov.lomaka.sudjoken.game.utils.advanced.AdvancedStage
import golov.lomaka.sudjoken.game.utils.runGDX
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class CommongScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val cbGroupkan = ACheckBoxGroup()
    private val cnopkiList = List(9) { ACheckBox(this, ACheckBox.Type.values()[it]).apply { checkBoxGroup = cbGroupkan } }
    private val labelListe = List(81) { Label("", Label.LabelStyle(game.fontTTFUtil.fontInterRegular.font_38.font, GameColor.blure)) }
    private val time       = Label("01:00", Label.LabelStyle(game.fontTTFUtil.fontInterRegular.font_58.font, if (game.isWhite) Color.BLACK else Color.WHITE))

    // Fields
    private var NUMBER = 0
    private var counter = 0

    override fun show() {
        stageUI.root.animHiden()
        setUIBackground(if (game.isWhite) game.spriteUtil.GAME.WHITE_BACKGROUND else game.spriteUtil.GAME.BLACK_BACKGROUND)
        super.show()
        stageUI.root.animShown(0.3f)
        game.activity.setNavigationBarColor(if (game.isWhite) R.color.white else R.color.balack)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addListCnopok()
        addListLabel()
        val actorik = Actor()
        addActor(actorik)
        actorik.apply {
            setBounds(136f, 105f, 352f, 66f)
            setOnClickListener { stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(CommongScreen(game)) } }
        }


        addActor(time)
        time.apply {
            setBounds(144f, 1126f, 318f, 120f)
            setAlignment(Align.center)
        }

        var timer = 60
        coroutine?.launch {
            while (isActive && timer > 0) {
                delay(1000)
                timer--
                runGDX { time.setText("00:$timer") }
                if (timer == 0) {
                    cancel()
                    stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(CommongScreen(game)) }
                }
            }
        }

        val actorw = Actor()
        addActor(actorw)
        actorw.apply {
            setBounds(511f, 1151f, 89f, 89f)
            setOnClickListener { stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(SettingsScreen(game), CommongScreen(game)) } }
        }

    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addListCnopok() {
        addActors(cnopkiList)
        cnopkiList.onEachIndexed { index, aCheckBox ->
            aCheckBox.setBounds(Layout.Position.list[index], Vector2(58f, 58f))
            aCheckBox.setOnCheckListener { if (it) NUMBER = index.inc() }
        }

        cnopkiList.first().check()
    }

    private fun AdvancedStage.addListLabel() {
        addActors(labelListe)

        val coefList = (0..100)
        val numbList = (1..9)

        var nx = 12f
        var ny = 942f
        labelListe.onEachIndexed { index, label ->
            label.setBounds(nx, ny, 64f, 64f)
            nx += 66f
            if (index.inc() % 9 == 0) {
                nx = 12f
                ny -= 66f
            }

            label.apply {
                setAlignment(Align.center)
                setOnClickListener {
                    setText(NUMBER)
                    disable()
                    counter++
                    if (counter == 81) stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(CommongScreen(game)) }
                }
            }

            // 15%
            if (coefList.random() <= 15) {
                label.setText(numbList.random())
                label.disable()
                counter++
                if (counter == 81) stageUI.root.animHiden(0.3f) { game.navigationManager.navigate(CommongScreen(game)) }
            }

        }

    }

}