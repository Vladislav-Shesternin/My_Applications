package com.hsr.bkm.mobile.game.screens

import com.badlogic.gdx.Input
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.hsr.bkm.mobile.MainActivity
import com.hsr.bkm.mobile.game.actors.NewsItem
import com.hsr.bkm.mobile.game.actors.VerticalGroup
import com.hsr.bkm.mobile.game.actors.button.AButton
import com.hsr.bkm.mobile.game.actors.button.AButtonStyle
import com.hsr.bkm.mobile.game.manager.SpriteManager
import com.hsr.bkm.mobile.game.utils.Completer
import com.hsr.bkm.mobile.game.utils.actor.disable
import com.hsr.bkm.mobile.game.utils.actor.enable
import com.hsr.bkm.mobile.game.utils.actor.setBounds
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedGroup
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedScreen
import com.hsr.bkm.mobile.game.utils.runGDX
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.hsr.bkm.mobile.game.utils.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val panel    = Image(SpriteManager.GameRegion.PANEL.region)
    private val home     = AButton(AButtonStyle.home)
    private val favorit  = AButton(AButtonStyle.favorit)

    private val verticalGroup   = VerticalGroup(VerticalGroup.Direction.DOWN)
    private val scrollPane      = ScrollPane(verticalGroup)

    private val verticalGroup2 = VerticalGroup(VerticalGroup.Direction.DOWN)
    private val scrollPane2    = ScrollPane(verticalGroup2)


    override fun AdvancedGroup.addActorsOnGroup() {
        MainActivity.lottie.showLoader()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        addPanel()
        addHome()
        addFavorit()

        addScrollPane()
        addScrollPane2()
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addPanel() {
        addActor(panel)
        panel.setBounds(LG.panel)
    }

    private fun AdvancedGroup.addHome() {
        addActor(home)
        home.setBounds(LG.home)

        home.setOnClickListener { showHome() }
    }

    private fun AdvancedGroup.addFavorit() {
        addActor(favorit)
        favorit.setBounds(LG.favorit)

        favorit.setOnClickListener { showFavorit() }
    }

    private fun AdvancedGroup.addScrollPane() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 120f, 700f, 1280f)

        val completer = Completer(coroutine, news.size) { /*log("END LOAD News")*/ }
        var flag = true
        var head: String? = null
        coroutine.launch {
            news.onEachIndexed { index, n ->
                if (index % 10 == 0) {
                    delay(4_000)
                    if (flag) {
                        flag = false
                        MainActivity.lottie.hideLoader()
                    }
                }
                coroutine.launch {
                    with(n) {
                        runGDX {
                            head = if (headline?.first() == ':') headline.replaceFirst(":", "") else headline
                            NewsItem(head, image, url) { completer.complete() }.also {
                                it.setBounds(20f, 589f, 660f, 554f)
                                verticalGroup.addActor(it)

                                it.favoritBlock = {
                                    it.favoritBtn.disable()
                                    it.favoritBtn.addAction(Actions.alpha(0f))
                                    val b = NewsItem(it.headline, it.imageUrl, it.url, it.endBlock)
                                    with(b) {
                                        favoritBtn.disable()
                                        favoritBtn.addAction(Actions.alpha(0f))
                                        setBounds(20f, 589f, 660f, 554f)
                                        verticalGroup2.addActor(this)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun AdvancedGroup.addScrollPane2() {
        addActor(scrollPane2)
        scrollPane2.apply {
            disable()
            addAction(Actions.alpha(0f))
            setBounds(0f, 120f, 700f, 1280f)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun showFavorit() {
        scrollPane.apply {
            disable()
            addAction(Actions.alpha(0f))
        }
        scrollPane2.apply {
            enable()
            addAction(Actions.alpha(1f))
        }
    }

    private fun showHome() {
        scrollPane2.apply {
            disable()
            addAction(Actions.alpha(0f))
        }
        scrollPane.apply {
            enable()
            addAction(Actions.alpha(1f))
        }
    }

}