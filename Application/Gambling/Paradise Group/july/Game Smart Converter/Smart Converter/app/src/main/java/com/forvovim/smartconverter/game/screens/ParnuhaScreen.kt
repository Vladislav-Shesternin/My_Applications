package com.forvovim.smartconverter.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.forvovim.smartconverter.game.actors.checkbox.ACheckBox
import com.forvovim.smartconverter.game.actors.checkbox.ACheckBoxStyle
import com.forvovim.smartconverter.game.game
import com.forvovim.smartconverter.game.manager.GameDataStoreManager
import com.forvovim.smartconverter.game.manager.NavigationManager
import com.forvovim.smartconverter.game.manager.SpriteManager
import com.forvovim.smartconverter.game.utils.actor.animHide
import com.forvovim.smartconverter.game.utils.actor.animShow
import com.forvovim.smartconverter.game.utils.actor.disable
import com.forvovim.smartconverter.game.utils.actor.enable
import com.forvovim.smartconverter.game.utils.actor.setOnClickListener
import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup
import com.forvovim.smartconverter.game.utils.advanced.AdvancedScreen
import com.forvovim.smartconverter.game.utils.runGDX
import kotlinx.coroutines.launch


var izmailPAPKA  = "https://fornoviherreromarta.github.io/Smart_Converter/pdfkdffdkdf"
var samshitMAMKA = "https://fornoviherreromarta.github.io/Smart_Converter/tkfgksmartfjgkjg"

class ParnuhaScreen: AdvancedScreen() {

    private val groupSuper = AdvancedGroup()
    private val kulinarImg = Image(SpriteManager.GameRegion.KULINAR.region)
    private val koshechImg = Image(SpriteManager.GameRegion.KOSHECHKA.region)
    private val pokaBox = ACheckBox(ACheckBoxStyle.porivokaLOKA)
    private val pokaBtn = Actor()

    private var isPasta = false


    override fun show() {
        super.show()
        mainGroup.addActor(groupSuper)
        groupSuper.apply {
            setBounds(615f, 615f, WIDTH, HEIGHT)
            addAction(Actions.alpha(0f))

            addActors(kulinarImg, koshechImg)
            kulinarImg.setBounds(34f, 605f, 547f, 470f)
            koshechImg.setBounds(27f, 46f, 559f, 200f)
            addVse()
        }.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addVse() {
        addActors(pokaBox, pokaBtn)
        pokaBox.apply {
            setBounds(27f, 195f,51f, 51f)
            setOnCheckListener {
                isPasta = it
                if (isPasta) pokaBtn.enable() else pokaBtn.disable()
            }
        }
        pokaBtn.apply {
            setBounds(27f, 46f,559f, 99f)
            disable()
            setOnClickListener {
                if (isPasta) {
                    disable()
                    coroutine.launch {
                        GameDataStoreManager.Piston.update { true }
                        mainGroup.animHide(0.7f) { runGDX { NavigationManager.navigate(YjePoznoIdiSpatkiScreen()) } }
                    }
                }
            }
        }

        val para  = Actor()
        val para2 = Actor()
        val tara  = Actor()
        val tara2 = Actor()
        addActors(para, para2, tara, tara2)
        para.apply {
            setBounds(430f, 219f, 144f, 47f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(izmailPAPKA))) }
        }
        para2.apply {
            setBounds(79f, 191f, 209f, 47f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(izmailPAPKA))) }
        }
        tara.apply {
            setBounds(307f, 168f, 236f, 47f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(samshitMAMKA))) }
        }
        tara2.apply {
            setBounds(66f, 152f, 236f, 34f)
            setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(samshitMAMKA))) }
        }
    }

}