package com.forvovim.smartconverter.game.actors

 import com.badlogic.gdx.graphics.Color
 import com.badlogic.gdx.scenes.scene2d.ui.Image
 import com.badlogic.gdx.scenes.scene2d.ui.Label
 import com.badlogic.gdx.utils.Align
 import com.forvovim.smartconverter.game.manager.FontTTFManager
 import com.forvovim.smartconverter.game.manager.SpriteManager
 import com.forvovim.smartconverter.game.utils.GameColor
 import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup
 import com.forvovim.smartconverter.game.utils.namekased
 import com.forvovim.smartconverter.game.utils.numStr
 import java.util.Random

class Artem(
    val iok: Int,
): AdvancedGroup() {

    private val flag = Image(SpriteManager.ListRegion.FLAGES.regionList[iok])
    private val valu = Label(namekased[iok], Label.LabelStyle(FontTTFManager.GilSemBold.font_32.font, Color.BLACK))
    private val huina = Label(numStr(10, 325, 1).toString()+"."+ numStr(0,9,2), Label.LabelStyle(FontTTFManager.GilReg.font_27.font, if (Random().nextBoolean()) GameColor.Sdod else GameColor.lodsdf ))

    init {
        addActors(flag, valu, huina)
        flag.setBounds(0f, 0f, 102f, 66f)
        valu.setBounds(130f, 12f, 120f, 40f)
        huina.setBounds(377f, 16f, 169f, 33f)
        huina.setAlignment(Align.right)
    }

}