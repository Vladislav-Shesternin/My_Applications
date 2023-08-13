package com.tsabekaa.finhelper.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.actors.ABank
import com.tsabekaa.finhelper.game.actors.scroll.HorizontalGroup
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.musicUtil
import com.tsabekaa.finhelper.game.utils.actor.disable
import com.tsabekaa.finhelper.game.utils.actor.setOnClickListener
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedScreen
import com.tsabekaa.finhelper.game.utils.Layout.Menu as LM


var reg: TextureRegion? = null
var nam: String = ""
var indeXXX  = 0
class ListBankScreen: AdvancedScreen() {

    private val catalogImage    = Image(SpriteManager.GameRegion.KATALOG.region)
    private val horizontalGroup = HorizontalGroup(26f)
    private val scrollPane      = ScrollPane(horizontalGroup)

    private val names = listOf<String>(
        "Прогрессо",
        "Финсервис",
        "Экспертбанк",
        "Инвестбанк",
        "Финстратег",
        "Бизнесразвитие",
        "Стабилбанк",
        "Экономбанк",
        "Кредитсфера",
        "Финэксперт",
        "Развитиефинанс",
        "Инновбанк",
        "Надеждабанк",
        "Финцентр",
        "Комбанк",
        "Благосостояниебанк",
    )

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addCatalog()
        addScroll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addCatalog() {
        addActor(catalogImage)
        catalogImage.setBounds(108f, 944f, 407f, 160f)
    }

    private fun AdvancedGroup.addScroll() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 231f, 623f, 607f)

        names.onEachIndexed { index, name ->
            horizontalGroup.addActor(ABank(SpriteManager.ListRegion.ITEMS.regionList[index], name).apply {
                setSize(517f, 607f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        reg = SpriteManager.ListRegion.ITEMS.regionList[index]
                        nam = name
                        indeXXX = index
                        NavigationManager.navigate(DetailBankScreen(), ListBankScreen())
                    }
                })
            })
        }
    }

}