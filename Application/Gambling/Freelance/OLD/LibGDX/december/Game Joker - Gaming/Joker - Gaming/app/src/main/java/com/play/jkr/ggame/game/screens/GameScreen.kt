package com.play.jkr.ggame.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.play.jkr.ggame.game.actors.Card
import com.play.jkr.ggame.game.manager.NavigationManager
import com.play.jkr.ggame.game.manager.SpriteManager
import com.play.jkr.ggame.game.util.advanced.AdvancedScreen
import com.play.jkr.ggame.game.util.advanced.AdvancedStage
import com.play.jkr.ggame.game.util.disable
import com.play.jkr.ggame.game.util.enable
import com.play.jkr.ggame.game.util.listeners.toClickable
import com.play.jkr.ggame.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val dataList by lazy { List(20) { index ->
        Data(
            if (index >= 10) index - 9 else index.inc(),
            if (index >= 10) SpriteManager.ListRegion.ELEMENTS.regionList[index - 10] else SpriteManager.ListRegion.ELEMENTS.regionList[index]
        )
    } }
    private val cardList: List<TextureRegion> by lazy {
        (List(10) { SpriteManager.ListRegion.CARDS.regionList[it] }
                + (List(10) { SpriteManager.ListRegion.CARDS.regionList[it] })).shuffled()
    }

    var firstCard : Card? = null
    var secondCard: Card? = null
    var firstData : Data? = null
    var secondData: Data? = null

    var countPair = 0



    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStageUI() {

        dataList.shuffled().onEachIndexed { index, data ->
            Card().also { card ->
                addActor(card)
                card.card.drawable = TextureRegionDrawable(cardList[index])
                card.image.drawable = TextureRegionDrawable(data.region)

                with(LG.list[index]) {
                    card.setBounds(x, y, LG.size.x, LG.size.y)
                }

                card.toClickable().setOnClickListener {
                    card.disable()
                    card.open {
                        if (firstCard == null) {
                            firstCard = card
                            firstData = data
                        } else {
                            stageUI.root.disable()
                            secondCard = card
                            secondData = data

                            if (firstData?.id != secondData?.id) {
                                firstCard?.close()
                                secondCard?.close {
                                    firstCard?.enable()
                                    secondCard?.enable()
                                    firstCard = null
                                    secondCard = null
                                    stageUI.root.enable()
                                }
                            } else {
                                stageUI.root.enable()
                                firstCard?.disable()
                                secondCard?.disable()

                                firstCard = null
                                secondCard = null

                                if (++countPair == 10) NavigationManager.navigate(GameScreen())
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