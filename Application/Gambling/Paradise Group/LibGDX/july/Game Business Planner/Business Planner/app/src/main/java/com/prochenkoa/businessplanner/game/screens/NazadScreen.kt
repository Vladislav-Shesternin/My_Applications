package com.prochenkoa.businessplanner.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.prochenkoa.businessplanner.game.actors.VerList
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBox
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBoxGroup
import com.prochenkoa.businessplanner.game.actors.checkbox.ACheckBoxStyle
import com.prochenkoa.businessplanner.game.manager.FontTTFManager
import com.prochenkoa.businessplanner.game.manager.NavigationManager
import com.prochenkoa.businessplanner.game.manager.SpriteManager
import com.prochenkoa.businessplanner.game.utils.GameColor
import com.prochenkoa.businessplanner.game.utils.actor.animHide
import com.prochenkoa.businessplanner.game.utils.actor.animShow
import com.prochenkoa.businessplanner.game.utils.actor.setOnClickListener
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedScreen
import com.prochenkoa.businessplanner.game.utils.advanced.AdvancedStage
import com.prochenkoa.businessplanner.game.utils.region


val listFor_AndreyZapolnalo = mutableListOf<String>()

var imgIndex = 0

class NazadScreen: AdvancedScreen() {

    val naVsakSluchai = "Добро пожаловать в мир нашей уникальной автошампуньной компании! Мы предлагаем качественные автошампуни, специально разработанные для бережного и эффективного очищения вашего автомобиля.\n" +
            "Наша автошампуньная линейка обеспечивает мощное удаление грязи, жира, следов насекомых и других загрязнений, сохраняя при этом блеск и защищая кузов вашего автомобиля. Мы гордимся использованием высококачественных компонентов, которые делают наши продукты безопасными для кузова и окружающей среды.\n" +
            "Мы постоянно стремимся к инновациям и исследуем новые формулы, чтобы удовлетворить потребности наших клиентов. Наша команда экспертов в области автохимии постоянно совершенствует наши продукты, чтобы обеспечить вам идеальный результат."


    val lisika = listOf(
        "Уборка квартир",
        "Производство автошампуння",
        "Консалтинговые услуги",
        "Запуск интернет-магазина",
        "Агентство переводов",
        "Организация мероприятий",
        "Сезонный бизнес",
        "Услуги фотографа",
    )

    private val listPPP by lazy { listOf(
        SpriteManager.EnumTexture.p1.data.texture.region,
        SpriteManager.EnumTexture.p2.data.texture.region,
        SpriteManager.EnumTexture.p3.data.texture.region,
        SpriteManager.EnumTexture.p4.data.texture.region,
        SpriteManager.EnumTexture.p5.data.texture.region,
        SpriteManager.EnumTexture.p6.data.texture.region,
        SpriteManager.EnumTexture.p7.data.texture.region,
        SpriteManager.EnumTexture.p8.data.texture.region,
    ) }

    private val ximensImg = Image(SpriteManager.GameRegion.XIMES.region)
    private val nazvakaka by lazy { Label(lisika[imgIndex], Label.LabelStyle(FontTTFManager.Semibold.font_41.font, Color.BLACK)) }
    private val texeta by lazy { Label(if (listFor_AndreyZapolnalo.isEmpty()) naVsakSluchai else listFor_AndreyZapolnalo.random(), Label.LabelStyle(FontTTFManager.Regular.font_32.font, Color.valueOf("939393"))).apply {
        setAlignment(Align.topLeft)
        wrap = true
    } }
    private val sp = ScrollPane(texeta)

    private val imagek = Image(listPPP[imgIndex])


    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(sp)
        sp.setBounds(41f, 0f, 657f, 828f)
        addActor(imagek)
        imagek.setBounds(0f, 955f, 739f, 540f)
        addRetig()
        root.animShow(0.5f)
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.addRetig() {
        addActor(ximensImg)
        ximensImg.setBounds(0f, 1462f, 740f, 181f)
        val back = Actor()
        addActor(back)
        back.setBounds(0f, 1489f,144f, 144f)
        back.setOnClickListener { stageUI.root.animHide(0.5f) { NavigationManager.back() } }
        addActor(nazvakaka)
        nazvakaka.setBounds(41f, 846f, 698f, 68f)
    }

}