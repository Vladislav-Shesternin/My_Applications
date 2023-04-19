package com.veldan.sportslots.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import com.veldan.sportslots.assets.SpriteManager
import com.veldan.sportslots.utils.setBoundsFigmaY
import kotlinx.coroutines.delay

class MoneyBonus: Image(SpriteManager.CollectionSpriteList.PRICE_LIST.textureDataList[5].texture) {



    init {
        setOrigin(Align.center)
        addAction(Actions.parallel(
            Actions.fadeOut(0f),
            Actions.scaleTo(0f, 0f)
        ))
        setBoundsFigmaY(190f, 543f, 320f, 87f)
    }



    suspend fun show(): Int {
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.fadeIn(2f),
                    Actions.scaleTo(1f, 1f),
                    Actions.rotateTo(360f, 2f)
                ),
                Actions.delay(1f),
                Actions.parallel(
                    Actions.fadeOut(1f),
                    Actions.scaleTo(0f, 0f, 1f)
                ),
            )
        )
        delay(4100)
        return 12_000
    }

}