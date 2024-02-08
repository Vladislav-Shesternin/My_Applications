package com.hsr.bkm.mobile.game.screens

import android.annotation.SuppressLint
import com.hsr.bkm.mobile.MainActivity
import com.hsr.bkm.mobile.game.actors.button.AButtonStyle
import com.hsr.bkm.mobile.game.actors.button.AButtonText
import com.hsr.bkm.mobile.game.actors.label.ALabelStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.hsr.bkm.mobile.game.game
import com.hsr.bkm.mobile.game.manager.FontTTFManager
import com.hsr.bkm.mobile.game.manager.NavigationManager
import com.hsr.bkm.mobile.game.manager.SpriteManager
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedGroup
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedScreen
import com.hsr.bkm.mobile.util.data.News
import com.hsr.bkm.mobile.util.log

class MenuScreen : AdvancedScreen() {

     private val news = AButtonText("News", AButtonStyle.def, ALabelStyle.style(ALabelStyle.Inter.Regular._63))
     private val exit = AButtonText("Exit", AButtonStyle.def, ALabelStyle.style(ALabelStyle.Inter.Regular._63))


    override fun show() {
        MainActivity.lottie.hideLoader()
        setBackBackground(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }


    override fun AdvancedGroup.addActorsOnGroup() {
        addBTTT()
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedGroup.addBTTT() {
        addActors(news, exit)
        news.apply {
            setBounds(131f, 760f, 437f, 198f)
            setOnClickListener { NavigationManager.navigate(GameScreen(), MenuScreen()) }
        }
        exit.apply {
            setBounds(131f, 515f, 437f, 198f)
            setOnClickListener { NavigationManager.exit() }
        }
    }


}