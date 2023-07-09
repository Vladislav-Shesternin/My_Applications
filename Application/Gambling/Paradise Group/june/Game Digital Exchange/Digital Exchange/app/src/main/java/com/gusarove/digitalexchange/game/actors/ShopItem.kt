package com.gusarove.digitalexchange.game.actors

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.gusarove.digitalexchange.game.manager.FontTTFManager
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.GameColor
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup
import java.util.Random

class ShopItem: AdvancedGroup() {

    private val names = listOf(
        "Zenithor",
                "VirtuMax",
                "Crystalline",
                "Axsentia",
                "EclectiCore",
                "Novacor",
                "Elegancia",
                "Aurorite",
                "Spectraluxe",
                "Intensio",
                "Vitalink",
                "Fusionix",
                "Orionix",
                "Ethera",
                "Primavara",
                "Actuwell",
                "Galaktic",
                "Stellaris",
                "Impella",
                "Maxens",
                "Solero",
                "Nuancei",
                "Avrenta",
                "Radiantix",
                "Aesthetico",
                "Harmonyx",
                "Fantasia",
                "Quinta",
                "Grandora",
                "Wellessa",
                "Luminix",
                "Etherealis",
                "Synergico",
                "Zenovia",
                "Vivida",
                "Luminae",
                "InfiniCore",
                "Sereniti",
                "Vortexa",
                "Elementrix",
                "VelociX",
                "Zephyria",
                "Luminaryx",
                "Inspiro",
                "Avidora",
                "Equinoxia",
                "Celesta",
                "Illumea",
                "Enchanta",
                "Chromatix",
    )

    private val flag = Random().nextBoolean()

    private val icon = Image(if (flag) SpriteManager.GameRegion.PADAE.region else SpriteManager.GameRegion.ROSTE.region)
    private val name = Label(names.shuffled().first(), Label.LabelStyle(FontTTFManager.LeilaBold.font_35.font, Color.BLACK))
    private val date = Label("${day()} ${mon()}", Label.LabelStyle(FontTTFManager.LeilaRegular.font_35.font, Color.BLACK))
    private val suma = Label((if (flag) "-" else "+") + "$${numbers((1..3).shuffled().first())}." + numbers(2), Label.LabelStyle(FontTTFManager.LeilaBold.font_35.font, (if (flag) GameColor.falsek else GameColor.truesk))).apply { setAlignment(Align.right) }
    private val what = Label(what(), Label.LabelStyle(FontTTFManager.LeilaRegular.font_35.font, Color.BLACK)).apply { setAlignment(Align.right) }


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addActors(icon, name, date, suma, what)
            icon.setBounds(0f, 0f, 128f, 126f)
            name.setBounds(147f, 63f, 326f, 54f)
            date.setBounds(147f, 15f, 326f, 54f)
            suma.setBounds(511f, 63f, 292f, 54f)
            what.setBounds(511f, 15f, 292f, 54f)
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun r() = (0..9).shuffled().first()

    private fun day() = (1..31).shuffled().first()
    private fun mon() = listOf("June", "May", "March").shuffled().first()

    private fun what() = listOf("Subscription", "Bank Transfer", "Shop", "Electronics", "Karting", "Club", "Metro", "Charity", "Journey", "Directions", "Gym", "Fitness club", "Sauna", "Treadmill", "Restaurant",).shuffled().first()

    private fun numbers(countr: Int): String {
        var str = ""
        repeat(countr) {
            str += r().toString()
        }
        return str
    }

}