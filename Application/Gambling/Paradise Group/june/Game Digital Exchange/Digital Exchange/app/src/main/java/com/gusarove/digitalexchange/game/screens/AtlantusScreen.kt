package com.gusarove.digitalexchange.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.gusarove.digitalexchange.game.manager.FontTTFManager
import com.gusarove.digitalexchange.game.manager.NavigationManager
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.actor.setOnClickListener
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedScreen


class AtlantusScreen: AdvancedScreen() {

    private val aboutText = "Добро пожаловать в Цифровой Обменник - уникальное приложение, которое открывает перед вами волнующий мир криптовалют. У нас вы найдете не только самую актуальную статистику, но и захватывающий анализ, чтобы помочь вам принимать осознанные решения и достичь финансового успеха.\n" +
            "\n" +
            "  Что делает Цифровой Обменник особенным? Во-первых, наша обширная база данных содержит информацию о широком спектре криптовалют, от известных гигантов до новейших перспективных проектов. Мы предоставляем вам доступ к самым точным данным о ценах, объемах торгов, капитализации и других ключевых показателях.\n" +
            "\n" +
            "  Но это только начало! С помощью наших графиков и диаграмм, вы сможете проникнуться атмосферой криптовалютных рынков и разгадать их тайны. Анализируйте тренды, обнаруживайте перспективные возможности и принимайте решения, основанные на основательных данных.\n" +
            "\n" +
            "  Мы также понимаем, что каждый инвестор уникален. Поэтому мы предлагаем настраиваемые уведомления, которые помогут вам быть в курсе важных изменений. Получайте мгновенные уведомления о прорывных событиях или значимых колебаниях цен, чтобы всегда быть на шаг впереди."

    private val label      = Label(aboutText, Label.LabelStyle(FontTTFManager.Roboto.font_50.font, Color.BLACK))
    private val scrollPane = ScrollPane(label)
    private val back       = Image(SpriteManager.GameRegion.BK.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        addActor(scrollPane)
        scrollPane.setBounds(41f, 29f, 785f, 1653f)

        label.setAlignment(Align.center, Align.top)
        label.wrap = true


        addActor(back)
        back.apply {
            setBounds(579f, 46f, 259f, 259f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}