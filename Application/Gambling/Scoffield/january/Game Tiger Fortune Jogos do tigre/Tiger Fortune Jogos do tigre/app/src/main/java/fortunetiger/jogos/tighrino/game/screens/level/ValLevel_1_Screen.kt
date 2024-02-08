package fortunetiger.jogos.tighrino.game.screens.level

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.Align
import fortunetiger.jogos.tighrino.game.LibGDXGame
import fortunetiger.jogos.tighrino.game.actors.AThing
import fortunetiger.jogos.tighrino.game.screens.ValResultScreen
import fortunetiger.jogos.tighrino.game.utils.TIME_ANIM
import fortunetiger.jogos.tighrino.game.utils.actor.disable
import fortunetiger.jogos.tighrino.game.utils.actor.enable
import fortunetiger.jogos.tighrino.game.utils.actor.setOnClickListener
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedGroup
import fortunetiger.jogos.tighrino.game.utils.advanced.AdvancedStage
import fortunetiger.jogos.tighrino.game.utils.runGDX
import fortunetiger.jogos.tighrino.game.utils.toMS
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class ValLevel_1_Screen(_game: LibGDXGame) : ILevelScreen(_game, Static.ELevel.L1) {

    private val things1  = List(4) { AThing(this, game.allAssets.listThing[indexList[0]]) }
    private val things2  = List(4) { AThing(this, game.allAssets.listThing[indexList[1]]) }
    private val things3  = List(4) { AThing(this, game.allAssets.listThing[indexList[2]]) }
    private val things4  = List(4) { AThing(this, game.allAssets.listThing[indexList[3]]) }
    private val thingsAll = (things1 + things2 + things3 + things4).shuffled()

    override fun AdvancedStage.addActorsOnStage() {
        runGDX {
            addActor(tmpGroup)
            tmpGroup.setBounds(119f, 286f, 849f, 1124f)
        }
        coroutine?.launch(Dispatchers.Default) { tmpGroup.addThings() }
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private suspend fun AdvancedGroup.addThings() {
        var nx = 117f
        var ny = 871f

        var counter = 0

        var thing1: AThing? = null
        var thing2: AThing? = null

        var tmpActions1: Action? = null
        var tmpActions2: Action? = null

        thingsAll.onEachIndexed { index, aThing ->
            runGDX {
                addActor(aThing)

                aThing.setBounds(nx, ny, 140f, 140f)
                nx += 24f + 140f
                if (index.inc() % 4 == 0) {
                    nx = 117f
                    ny -= 74f + 140f
                }
                aThing.setOrigin(Align.center)
                aThing.setOnClickListener {
                    counter++

                    if (counter > 2) return@setOnClickListener

                    game.soundUtil.apply { play(THING_TOUCH) }

                    aThing.disable()
                    aThing.selected()

                    if (counter == 1) {
                        thing1 = aThing
                    } else {
                        thing2 = aThing

                        thing1!!.apply {
                            unselected()
                            toFront()
                            enable()
                        }
                        thing2!!.apply {
                            unselected()
                            toFront()
                            enable()
                        }

                        tmpActions1 = Actions.moveTo(thing2!!.x, thing2!!.y, 0.4f)
                        tmpActions2 = Actions.sequence(
                            Actions.moveTo(thing1!!.x, thing1!!.y, 0.4f),
                            Actions.run {
                                counter = 0

                                if (
                                    things1.all { it.y == things1.first().y } &&
                                    things2.all { it.y == things2.first().y } &&
                                    things3.all { it.y == things3.first().y } &&
                                    things4.all { it.y == things4.first().y }
                                ) {
                                    stopTimer()
                                    ValResultScreen.apply {
                                        levelScreen = level
                                        isWin = true
                                    }
                                    game.navigationManager.navigate(ValResultScreen::class.java.name)
                                }
                            }
                        )

                        thing1!!.addAction(tmpActions1)
                        thing2!!.addAction(tmpActions2)

                    }
                }
            }
            delay(80)
        }
    }

}