package com.playin.paganis.game.screens

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.playin.paganis.game.actors.scroll.HorizontalGroup
import com.playin.paganis.game.manager.NavigationManager
import com.playin.paganis.game.manager.SpriteManager
import com.playin.paganis.game.utils.actor.setBounds
import com.playin.paganis.game.utils.advanced.AdvancedGroup
import com.playin.paganis.game.utils.advanced.AdvancedScreen
import com.playin.paganis.game.utils.Layout.List as LL

class ListScreen: AdvancedScreen() {

    companion object {
        lateinit var globalSlot: Slot
            private set
    }

    private val nameImage        = Image(SpriteManager.GameRegion.NAMES.region)
    private val horizontalGroupP = HorizontalGroup(44f)
    private val scrollP          = ScrollPane(horizontalGroupP)
    private val horizontalGroupL = HorizontalGroup(44f)
    private val scrollL          = ScrollPane(horizontalGroupL)
    private val horizontalGroupN = HorizontalGroup(44f)
    private val scrollN          = ScrollPane(horizontalGroupN)

    private val listSlotP = listOf(
        Slot(
            SpriteManager.ListRegion.P_ICON.regionList[0],
            SpriteManager.ListRegion.P_GAME.regionList[0],
            "Power of Zorro is a slot game based on the legendary masked swordsman and defender of the people, Zorro. The game features classic symbols such as masks, whips, and swords, as well as images of Zorro himself. Players can enjoy a variety of bonus features, including free spins, multipliers, and a special Zorro bonus game where they can help the hero uncover hidden treasures. With its thrilling music and immersive graphics, Power of Zorro is sure to be a hit with fans of both the legendary hero and slot games alike."
        ),
        Slot(
            SpriteManager.ListRegion.P_ICON.regionList[1],
            SpriteManager.ListRegion.P_GAME.regionList[1],
            "Aviator, developed by Spribe, takes online gambling to a whole new level with its innovative format. The game is built around a linear crash mechanic that quickly became popular with players due to its simplicity and reliability. However, Aviator is the \"black sheep\" in the world of such slots, which draws you into the game with its unique features and tools."
        ),
        Slot(
            SpriteManager.ListRegion.P_ICON.regionList[2],
            SpriteManager.ListRegion.P_GAME.regionList[2],
            "Pompeii Gold: Rapid Link is a slot game set in ancient Pompeii, just before the infamous eruption of Mount Vesuvius. The game features symbols such as helmets, swords, and chariots, as well as images of the city's famous architecture. Players can also collect gold coins to trigger the Rapid Link feature, where they can win one of four jackpots, including the massive Grand Jackpot. With its exciting gameplay and stunning visuals, Pompeii Gold: Rapid Link is sure to transport players back in time to the glory days of ancient Pompeii."
        ),
        Slot(
            SpriteManager.ListRegion.P_ICON.regionList[3],
            SpriteManager.ListRegion.P_GAME.regionList[3],
            "The Gates of Olympus slot machine from the game developer Pragmatic Play is powered by the Pay Anywhere engine. One of the main features was the progressive multiplier feature, which has a cumulative feature in the bonus round.\n" +
                    "\n" +
                    "Gates of Olympus can be compared to some extent with the Sweet Bonanza slot machine, but the maximum multiplier here is only x5000. And this is not a minus of the slot, but on the contrary, the potential winnings have become more accessible to an ordinary player. Below, you can get acquainted with the functionality of the slot and bonus features."
        ),
        Slot(
            SpriteManager.ListRegion.P_ICON.regionList[4],
            SpriteManager.ListRegion.P_GAME.regionList[4],
            "Story Of Medusa II - The Golden Era is a slot game based on the Greek myth of Medusa, the snake-haired Gorgon. The game is set against a backdrop of ancient ruins and features symbols such as helmets, shields, and swords, as well as images of the terrifying Medusa herself. Players can also trigger the Golden Era feature, where they can enjoy free spins with increased chances of winning. With its epic soundtrack and immersive graphics, Story Of Medusa II - The Golden Era is the perfect game for anyone who loves Greek mythology and the excitement of slot games."
        ),
    )
    private val listSlotL = listOf(
        Slot(
            SpriteManager.ListRegion.L_ICON.regionList[0],
            SpriteManager.ListRegion.L_GAME.regionList[0],
            "UFOs, aliens and strange phenomena in the sky are now more heard than ever: unidentified flying objects have been shot down in both the US and Canada. Yggdrasil has taken a humorous approach to the theme, which is not uncommon in the world of online slots, and Giganimals Gigablox seems to be about farm animals trying to invade an alien planet in their little flying saucers. On every spin, you get Gigablox symbols , which are able to fill the entire screen with the same character. Low value symbols, which are like the battery indicator on your mobile phone, are collected into UFO counters. This eventually triggers the bonus round where the trigger symbol becomes wild and you can turn all 4 low value symbols into wild in the same way. The potential of 20,000x your stake is solid enough, but it's probably not enough to keep the game from becoming a thing of the past anytime soon."
        ),
        Slot(
            SpriteManager.ListRegion.L_ICON.regionList[1],
            SpriteManager.ListRegion.L_GAME.regionList[1],
            "These days more and more games are being developed around Big Time Gaming`s Megaways mechanic, and it's getting really hard to count how many slots have been released using this super popular mechanic. Providers not only develop new games, but also give a second life to old slots, improving them with the mentioned Megaways mechanics. So it's time to update another cult and influential slot - this time we are talking about Gonzo`s Quest. The feature of 2020 was the re-release of classic slots in the form of sequels or updates with the introduced Megaways mechanics. In the middle of 2020, the turn came to Gonzo`s Quest. This update allowed fans from all over the world to play their favorite slot, but now with more winnings! The original version of Gonzo's Quest was released back in 2011, but the slot almost immediately became a cult and one of the favorites among players around the world. 10 years have passed since the first release, and the developers decided to revive the famous franchise. The remake is definitely promising, as it came about as a collaboration between two giants of the gaming industry - NetEnt and Red Tiger. We promise that the slot will not disappoint anyone, and given the update in the form of Megaways mechanics, this remake will more than meet the expectations of all players."
        ),
        Slot(
            SpriteManager.ListRegion.L_ICON.regionList[2],
            SpriteManager.ListRegion.L_GAME.regionList[2],
            "Russian Warship is a fun and exciting arcade-style game where players have to race against time to collect as many coins as possible before the ship sails away. The game features a vibrant and colorful seaside backdrop, with the ship ready to set sail in the distance. Players must quickly navigate through the different levels and avoid obstacles in their path to collect coins and increase their score. With its simple yet addictive gameplay, PPS is the perfect game for anyone who loves the thrill of the chase and the excitement of arcade-style games."
        ),
        Slot(
            SpriteManager.ListRegion.L_ICON.regionList[3],
            SpriteManager.ListRegion.L_GAME.regionList[3],
            "Hot Fruits 20 is a classic-style slot game that features traditional fruit symbols such as lemons, oranges, plums, and cherries. The game has 5 reels and 20 paylines, giving players plenty of chances to hit winning combinations. The fiery red backdrop and the flames surrounding the symbols add to the excitement and intensity of the game. With its simple gameplay and nostalgic design, Hot Fruits 20 is the perfect game for anyone who loves the classic feel of traditional slot machines."
        ),
        Slot(
            SpriteManager.ListRegion.L_ICON.regionList[4],
            SpriteManager.ListRegion.L_GAME.regionList[4],
            "Artefacts - Vault of Fortune is a thrilling slot game that takes players on an adventure to unlock the secrets of a treasure-filled vault. The game features symbols of ancient artefacts such as vases, masks, and gold coins, as well as a variety of precious gemstones. Players can trigger the free spins feature by landing three or more scatter symbols, and during the free spins, they can collect keys to unlock additional reels and increase their chances of winning big. With its high-quality graphics, immersive soundtrack, and exciting gameplay, Artefacts - Vault of Fortune is the perfect game for anyone who loves adventure and treasure hunting."
        ),
    )
    private val listSlotN = listOf(
        Slot(
            SpriteManager.ListRegion.N_ICON.regionList[0],
            SpriteManager.ListRegion.N_GAME.regionList[0],
            "Loteria el Barril is a colorful and exciting slot game inspired by the traditional Mexican game of Loteria. The game features symbols such as bright flowers, vibrant birds, and classic Loteria cards, including \"El Barril\" (the barrel) itself. With its 5 reels and 20 paylines, players have plenty of opportunities to hit winning combinations and trigger exciting bonuses, including a \"pick and win\" game where they can uncover instant cash prizes. The game's festive soundtrack and bright visuals create a lively and engaging atmosphere, making Loteria el Barril a must-play for anyone who loves the vibrant culture of Mexico and the excitement of slot machines."
        ),
        Slot(
            SpriteManager.ListRegion.N_ICON.regionList[1],
            SpriteManager.ListRegion.N_GAME.regionList[1],
            "Lamp of Infinity is a mystical and enchanting slot game that takes players on a journey to a magical world filled with treasures and wonders. The game's symbols include mystical lamps, golden coins, and enchanting jewels, as well as a genie that can grant players their wishes with special bonuses and features. With 5 reels and 25 paylines, players have plenty of chances to hit big wins, especially when they trigger the game's free spins bonus round. The game's immersive soundtrack and high-quality graphics create an enchanting atmosphere, making Lamp of Infinity the perfect game for anyone who loves the mystical and the magical."
        ),
        Slot(
            SpriteManager.ListRegion.N_ICON.regionList[2],
            SpriteManager.ListRegion.N_GAME.regionList[2],
            "Geese with Attitude is a fun and quirky slot game that features a cast of mischievous and feisty geese. The game's symbols include the geese themselves, as well as other farmyard animals such as pigs, cows, and chickens. With 5 reels and 20 paylines, players have plenty of opportunities to hit winning combinations and trigger special features, including a \"wild\" symbol that can substitute for other symbols to create winning lines. The game's humorous soundtrack and colorful graphics create a playful and entertaining atmosphere, making Geese with Attitude the perfect game for anyone who loves animals and wants to add a bit of fun to their slot gaming experience."
        ),
        Slot(
            SpriteManager.ListRegion.N_ICON.regionList[3],
            SpriteManager.ListRegion.N_GAME.regionList[3],
            "Big Catch Bonanza is an exciting slot game that takes players on a fishing adventure to catch the biggest fish and win big prizes. The game features symbols such as fishing rods, lures, and a variety of fish species, including salmon, trout, and bass. With 5 reels and 25 paylines, players have plenty of opportunities to hit winning combinations and trigger special features, such as a \"pick and win\" bonus game where they can catch instant cash prizes. The game's aquatic-themed soundtrack and vibrant graphics create an immersive and exciting atmosphere, making Big Catch Bonanza the perfect game for anyone who loves fishing or wants to try their luck at catching a big win."
        ),
        Slot(
            SpriteManager.ListRegion.N_ICON.regionList[4],
            SpriteManager.ListRegion.N_GAME.regionList[4],
            "Power of Zorro is a slot game based on the legendary masked swordsman and defender of the people, Zorro. The game features classic symbols such as masks, whips, and swords, as well as images of Zorro himself. Players can enjoy a variety of bonus features, including free spins, multipliers, and a special Zorro bonus game where they can help the hero uncover hidden treasures. With its thrilling music and immersive graphics, Power of Zorro is sure to be a hit with fans of both the legendary hero and slot games alike."
        ),
    )


    override fun show() {
        setBackgrounds(SpriteManager.SplashRegion.BAKKKICH.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addNames()
        addScroll()
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addNames() {
        addActor(nameImage)
        nameImage.setBounds(LL.names)
    }

    private fun AdvancedGroup.addScroll() {
        addActors(scrollP, scrollL, scrollN)
        scrollP.setBounds(LL.scrollP, LL.scrollSize)
        scrollL.setBounds(LL.scrollL, LL.scrollSize)
        scrollN.setBounds(LL.scrollN, LL.scrollSize)

        listSlotP.onEach { slot ->
            horizontalGroupP.addActor(Image(slot.iconRegion).apply {
                setSize(LL.slotW, LL.slotH)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        globalSlot = slot
                        NavigationManager.navigate(SlotScreen(), ListScreen())
                    }
                })
            })
        }
        listSlotL.onEach { slot ->
            horizontalGroupL.addActor(Image(slot.iconRegion).apply {
                setSize(LL.slotW, LL.slotH)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        globalSlot = slot
                        NavigationManager.navigate(SlotScreen(), ListScreen())
                    }
                })
            })
        }
        listSlotN.onEach { slot ->
            horizontalGroupN.addActor(Image(slot.iconRegion).apply {
                setSize(LL.slotW, LL.slotH)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        globalSlot = slot
                        NavigationManager.navigate(SlotScreen(), ListScreen())
                    }
                })
            })
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    data class Slot(
        val iconRegion: TextureRegion,
        val slotRegion: TextureRegion,
        val text: String,
    )

}