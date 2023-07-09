package crapto.quantem.ao.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import crapto.quantem.ao.game.manager.FontTTFManager
import crapto.quantem.ao.game.manager.NavigationManager
import crapto.quantem.ao.game.manager.SpriteManager
import crapto.quantem.ao.game.utils.actor.setOnClickListener
import crapto.quantem.ao.game.utils.advanced.AdvancedGroup
import crapto.quantem.ao.game.utils.advanced.AdvancedScreen


class MuScreen: AdvancedScreen() {

//    private val aboutText = "Introducing CryptoSafe - a comprehensive and user-friendly cryptocurrency wallet designed to provide you with a seamless and secure experience in managing your digital assets.\n" +
//            "\n" +
//            "  With CryptoSafe, you can effortlessly store, send, and receive various cryptocurrencies, ensuring that your funds are always within your control.\n" +
//            "  Our app supports a wide range of popular cryptocurrencies, allowing you to diversify your portfolio and stay ahead in the dynamic world of digital currencies.\n" +
//            "\n" +
//            "  Security is our utmost priority. \n" +
//            "  CryptoSafe employs state-of-the-art encryption techniques to safeguard your private keys and transactions, providing you with peace of mind knowing that your assets are protected against unauthorized access. Our wallet is designed with a multi-layered security infrastructure, incorporating features like biometric authentication, PIN code protection, and advanced encryption algorithms."
//
//    private val label      = Label(aboutText, Label.LabelStyle(FontTTFManager.Regular.font_31.font, Color.WHITE))
//    //private val scrollPane = ScrollPane(label)
    private val back       = Image(SpriteManager.GameRegion.BACK.region)


    override fun AdvancedGroup.addActorsOnGroup() {
        //addActor(scrollPane)
        //scrollPane.setBounds(16f, 47f, 645f, 1368f)

        val vav = Image(SpriteManager.GameRegion.VAVKA.region)
        addActor(vav)
        vav.setBounds(16f, 47f, 645f, 1368f)
//        label.setAlignment(Align.topLeft)
//        label.wrap = true


        addActor(back)
        back.apply {
            setBounds(277f, 61f, 156f, 60f)
            setOnClickListener { NavigationManager.back() }
        }
    }

}