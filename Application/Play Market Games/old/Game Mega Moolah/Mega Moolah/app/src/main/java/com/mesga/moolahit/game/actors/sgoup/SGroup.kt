package com.mesga.moolahit.game.actors.sgoup

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.mesga.moolahit.game.manager.SpriteManager
import com.mesga.moolahit.game.util.advanced.AdvancedGroup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import com.mesga.moolahit.game.util.Layout.S as LS

class SGroup: AdvancedGroup() {

    companion object {
        const val S_COUNT = 6
    }

    private val sList = List(S_COUNT) { Image() }


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActorsOnGroup()
            start()
        }
    }



    private fun addActorsOnGroup() {
        addSList()
    }

    private fun addSList() {
        var newY = LS.s.y
        sList.onEach {
            addActor(it)
            with(LS.s) {
                it.setBounds(x, newY, w, h)
                newY += h + vs
            }
        }
    }

    private fun start() {
        coroutineMain.launch(Dispatchers.Default) {
            while (isActive) {
                sList.onEach {
                    Gdx.app.postRunnable {
                        it.drawable = TextureRegionDrawable(SpriteManager.ListRegion.S.regionList.random())
                    }
                }
                delay(250)
            }
        }
    }


}