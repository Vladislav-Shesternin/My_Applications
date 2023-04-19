package com.velicolepno.olimp.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.velicolepno.olimp.game.actors.Gold
import com.velicolepno.olimp.game.actors.Grey
import com.velicolepno.olimp.game.actors.OC
import com.velicolepno.olimp.game.actors.button.AButton
import com.velicolepno.olimp.game.manager.NavigationManager
import com.velicolepno.olimp.game.manager.SpriteManager
import com.velicolepno.olimp.game.util.Layout
import com.velicolepno.olimp.game.util.advanced.AdvancedGroup
import com.velicolepno.olimp.game.util.advanced.AdvancedScreen
import com.velicolepno.olimp.game.util.advanced.AdvancedStage
import com.velicolepno.olimp.game.util.disable
import com.velicolepno.olimp.game.util.enable
import com.velicolepno.olimp.game.util.listeners.toClickable
import com.velicolepno.olimp.util.log
import com.velicolepno.olimp.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val dataList by lazy { List(20) { index ->
        Data(
            if (index >= 10) index - 9 else index.inc(),
            if (index >= 10) SpriteManager.ListRegion.ELEMENTS.regionList[index - 10] else SpriteManager.ListRegion.ELEMENTS.regionList[index]
        )
    } }

    var firstGroup : AdvancedGroup? = null
    var secondGroup: AdvancedGroup? = null
    var firstOC : OC? = null
    var secondOC: OC? = null
    var firstData : Data? = null
    var secondData: Data? = null

    var countPair = 0



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        val pos = Vector2(LG.grey.x, LG.grey.y)
        var newY = LG.gold.y
        var newX = LG.gold.x

        dataList.shuffled().onEachIndexed { index, data ->
            if (index <= 3) {
                Gold().also { gold ->
                    addActor(gold)
                    gold.image.drawable = TextureRegionDrawable(data.region)

                    with(LG.gold) {
                        gold.setBounds(newX, newY, w, h)
                        newX += w + hs
                    }

                    if (index == 1) {
                        newY = LG.y
                        newX = LG.gold.x
                    }

                    gold.toClickable().setOnClickListener {
                        gold.disable()
                        gold.open {
                            if (firstOC == null) {
                                firstGroup = gold
                                firstOC = gold
                                firstData = data
                            } else {
                                stageUI.root.disable()
                                secondGroup = gold
                                secondOC = gold
                                secondData = data

                                if (firstData?.id != secondData?.id) {
                                    firstOC?.close()
                                    secondOC?.close {
                                        firstGroup?.enable()
                                        secondGroup?.enable()
                                        firstOC = null
                                        secondOC = null
                                        stageUI.root.enable()
                                    }
                                } else {
                                    stageUI.root.enable()
                                    firstGroup?.disable()
                                    secondGroup?.disable()

                                    firstOC = null
                                    secondOC = null

                                    if (++countPair == 10) NavigationManager.navigate(GameScreen())
                                }
                            }
                        }

                    }

                }
            } else {
                Grey().also { grey ->
                    addActor(grey)
                    grey.image.drawable = TextureRegionDrawable(data.region)

                    when (index.inc()) {
                        10  -> pos.set(LG.g2)
                        16 -> pos.set(LG.g3)
                    }

                    with(LG.grey) {
                        grey.setBounds(pos.x, pos.y, w, h)
                        pos.x += w + hs
                    }

                    grey.toClickable().setOnClickListener {
                        grey.disable()
                        grey.open {
                            if (firstOC == null) {
                                firstGroup = grey
                                firstOC = grey
                                firstData = data
                            } else {
                                stageUI.root.disable()
                                secondGroup = grey
                                secondOC = grey
                                secondData = data

                                if (firstData?.id != secondData?.id) {
                                    firstOC?.close()
                                    secondOC?.close {
                                        firstGroup?.enable()
                                        secondGroup?.enable()
                                        firstOC = null
                                        secondOC = null
                                        stageUI.root.enable()
                                    }
                                } else {
                                    stageUI.root.enable()
                                    firstGroup?.disable()
                                    secondGroup?.disable()

                                    firstOC = null
                                    secondOC = null

                                    if (++countPair == 10) NavigationManager.navigate(GameScreen())
                                }
                            }
                        }

                    }

                }
            }
        }

    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    data class Data(
        val id: Int,
        val region: TextureRegion,
    )

}