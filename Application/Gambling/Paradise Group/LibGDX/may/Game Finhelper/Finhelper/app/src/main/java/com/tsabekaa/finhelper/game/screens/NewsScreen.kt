package com.tsabekaa.finhelper.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.tsabekaa.finhelper.game.actors.ANew
import com.tsabekaa.finhelper.game.actors.button.AButton
import com.tsabekaa.finhelper.game.actors.button.AButtonStyle
import com.tsabekaa.finhelper.game.actors.scroll.VerticalGroup
import com.tsabekaa.finhelper.game.manager.FontTTFManager
import com.tsabekaa.finhelper.game.manager.NavigationManager
import com.tsabekaa.finhelper.game.manager.SpriteManager
import com.tsabekaa.finhelper.game.musicUtil
import com.tsabekaa.finhelper.game.utils.GameColor
import com.tsabekaa.finhelper.game.utils.actor.disable
import com.tsabekaa.finhelper.game.utils.actor.setOnClickListener
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedGroup
import com.tsabekaa.finhelper.game.utils.advanced.AdvancedScreen
import com.tsabekaa.finhelper.game.utils.Layout.Menu as LM

class NewsScreen: AdvancedScreen() {

    private val backBtn    = Image(SpriteManager.GameRegion.BEKAPPA.region)
    private val newsLabel  = Label("Новости", Label.LabelStyle(FontTTFManager.BoldFont.font_50.font, Color.WHITE))
    private val verticalGroup = VerticalGroup(gap = 15f, startGap = 0f, endGap = 0f)
    private val scrollPane    = ScrollPane(verticalGroup)

    private val texts = listOf<String>(
        "Россия обновила рекорд по числу зараженных COVID-19.",
        "Россия запустила в производство новый военный самолет.",
        "Российские ученые открыли новый вид древних млекопитающих.",
        "Криптовалюта достигла нового исторического максимума.",
        "Биткоин преодолел отметку в $100 000.",
        "Рынок криптовалют показывает рост после коррекции.",
        "Банк Развитие расширяет географию обслуживания.",
        "ЭкспертБанк выступает спонсором благотворительной акции.",
        "Надежда Банк получает награду за лучший интернет-банк.",
        "Комбанк вводит новую услугу онлайн-кредитования.",
        "СтабилБанк объявляет о партнерстве с международным инвестором.",
        "Фондовые индексы достигают новых исторических максимумов.",
        "Криптовалютная биржа запускает торги фьючерсами на Ethereum.",
        "Акции технологических компаний снова растут после коррекции.",
        "Фондовая биржа объявляет о новом IPO крупной компании.",
        "Неизвестная компания разработала новую технологию очистки воды от примесей.",
        "Малоизвестный стартап получил инвестиции на развитие экологически чистых упаковок.",
        "Неизвестный производитель электромобилей объявил о запуске массового производства.",
        "Неизвестная компания представила прототип устройства для очистки воздуха в помещении.",
        "Новая малоизвестная компания выпустила первую версию программного обеспечения для управления личными финансами.",
    ).shuffled()

    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addBack()
        addName()
        addScroll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------
    private fun AdvancedGroup.addBack() {
        addActor(backBtn)
        backBtn.apply {
            setBounds(23f, 1117f, 120f, 120f)
            setOnClickListener { NavigationManager.back() }
        }
    }

    private fun AdvancedGroup.addName() {
        addActor(newsLabel)
        newsLabel.apply {
            setBounds(143f, 1117f, 461f, 120f)
            setAlignment(Align.center)
        }
    }

    private fun AdvancedGroup.addScroll() {
        addActor(scrollPane)
        scrollPane.setBounds(23f, 21f, 581f, 1035f)

        val regions = SpriteManager.ListRegion.NEWS.regionList.shuffled()

        texts.onEachIndexed { index, text ->
            verticalGroup.addActor(ANew(regions[index], text).apply { setSize(581f, 135f) })
        }
    }

}