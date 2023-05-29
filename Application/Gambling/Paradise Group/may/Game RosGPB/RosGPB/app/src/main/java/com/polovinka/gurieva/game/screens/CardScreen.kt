package com.polovinka.gurieva.game.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.polovinka.gurieva.game.actors.ABurger
import com.polovinka.gurieva.game.actors.ACard
import com.polovinka.gurieva.game.actors.ACardSettingsScroll
import com.polovinka.gurieva.game.actors.button.AButton
import com.polovinka.gurieva.game.actors.button.AButtonStyle
import com.polovinka.gurieva.game.manager.SpriteManager
import com.polovinka.gurieva.game.musicUtil
import com.polovinka.gurieva.game.utils.actor.disable
import com.polovinka.gurieva.game.utils.actor.enable
import com.polovinka.gurieva.game.utils.actor.setOnClickListener
import com.polovinka.gurieva.game.utils.advanced.AdvancedGroup
import com.polovinka.gurieva.game.utils.advanced.AdvancedScreen
import com.polovinka.gurieva.game.utils.advanced.AdvancedStage

class CardScreen: AdvancedScreen() {

    companion object {
        private var isFirstOpen = true
    }

    private val card    = ACard()
    private val scroll  = ACardSettingsScroll()
    private val menuBtn = AButton(AButtonStyle.miua)
    private val burger  = ABurger()

    override fun show() {
        if (isFirstOpen) {
            isFirstOpen = false
            musicUtil.apply { music = MAIN.apply { isLooping = true } }
        }

        setBackgrounds(SpriteManager.SplashRegion.BA_KG.region)
        super.show()
    }
    override fun AdvancedGroup.addActorsOnGroup() {
        addCard()
        addScroll()
        addMenu()

        stageUI.addBurger()
    }


    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addCard() {
        addActor(card)

        card.setBounds(40f, 1064f, 683f, 429f)
    }

    private fun AdvancedGroup.addScroll() {
        addActor(scroll)

        scroll.apply {
            setBounds(40f, 307f, 683f, 661f)

            colorBlock   = { card.setColor(it)   }
            idBlock      = { card.setId(it)      }
            wifiBlock    = { card.setWifi(it)    }
            surnameBlock = { card.setSurname(it) }
            nameBlock    = { card.setName(it)    }
            numeBlock    = { card.setNume(it)    }
        }
    }

    private fun AdvancedGroup.addMenu() {
        addActor(menuBtn)

        menuBtn.apply {
            setBounds(305f, 114f, 152f, 89f)
            setOnClickListener {
                mainGroup.disable()
                showBurger()
            }
        }
    }

    private fun AdvancedStage.addBurger() {
        addActor(burger)

        burger.apply {
            setBounds(-763f, 0f, 763f, 1589f)
            burgerImage.setOnClickListener {
                hideBurger()
                mainGroup.enable()
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun showBurger() {
        burger.addAction(Actions.moveTo(0f, 0f, 0.3f))
    }

    private fun hideBurger() {
        burger.addAction(Actions.moveTo(-763f, 0f, 0.3f))
    }

}