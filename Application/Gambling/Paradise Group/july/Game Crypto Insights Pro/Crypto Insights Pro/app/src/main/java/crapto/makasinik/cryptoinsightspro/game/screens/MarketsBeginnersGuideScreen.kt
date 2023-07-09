package crapto.makasinik.cryptoinsightspro.game.screens

import android.content.Intent
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import crapto.makasinik.cryptoinsightspro.BuildConfig
import crapto.makasinik.cryptoinsightspro.R
import crapto.makasinik.cryptoinsightspro.game.actors.SuperScrollPane
import crapto.makasinik.cryptoinsightspro.game.game
import crapto.makasinik.cryptoinsightspro.game.manager.NavigationManager
import crapto.makasinik.cryptoinsightspro.game.manager.SpriteManager
import crapto.makasinik.cryptoinsightspro.game.utils.Layout
import crapto.makasinik.cryptoinsightspro.game.utils.actor.animAlpha0
import crapto.makasinik.cryptoinsightspro.game.utils.actor.animSUSAlpha1
import crapto.makasinik.cryptoinsightspro.game.utils.actor.setBounds
import crapto.makasinik.cryptoinsightspro.game.utils.actor.setOnClickListener
import crapto.makasinik.cryptoinsightspro.game.utils.advanced.AdvancedGroup
import crapto.makasinik.cryptoinsightspro.game.utils.advanced.AdvancedScreen
import crapto.makasinik.cryptoinsightspro.game.utils.runGDX
import kotlinx.coroutines.launch

class MarketsBeginnersGuideScreen: AdvancedScreen() {

    private val profileImg = Image(SpriteManager.IgraRegion.PROFILE_SEARCH_QUAR.region)
    private val beginerImg = Image(SpriteManager.IgraRegion.BEGINNERS_GUIDE.region)
    private val marketsImg = Image(SpriteManager.IgraRegion.MARKETS.region)
    private val spSuper    = SuperScrollPane()
    private val menuImg    = Image(SpriteManager.IgraRegion.MENU_LEFT.region)

    //private val balLbl    = Label("$${numStr(10,99, 1)},000", Label.LabelStyle(FontTTFManager.GilMed.font_70.font, Color.WHITE))


    override fun AdvancedGroup.addActorsOnGroup() {
        coroutine.launch {
            runGDX {
                addImages()
                addMenu()
                addProfMenu()
            }
            profileImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            beginerImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            marketsImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            spSuper.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
            menuImg.animSUSAlpha1(0.187f, Interpolation.pow3OutInverse)
        }
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedGroup.addImages() {
        addActors(profileImg, beginerImg, marketsImg, spSuper)
        profileImg.apply {
            setBounds(Layout.profile)
            animAlpha0()
        }
        beginerImg.apply {
            setBounds(0f, 1272f, 680f, 121f)
            animAlpha0()
        }
        marketsImg.apply {
            setBounds(35f, 1102f, 607f, 132f)
            animAlpha0()
        }
        spSuper.apply {
            setBounds(18f, 96f, 641f, 968f)
            animAlpha0()
        }
    }

    private fun AdvancedGroup.addMenu() {
        addActor(menuImg)
        menuImg.apply {
            setBounds(Layout.menu)
            animAlpha0()
        }

        val c1 = Actor()
        val c2 = Actor()
        val c3 = Actor()
        addActors(c1,c2,c3)
//        c1.apply {
//            setBounds(301f, 7f, 89f, 89f)
//            setOnClickListener {
//                stageUI.root.animAlpha0(0.5f) {
//                    NavigationManager.navigate(MoyBalansScreen(), VasiliyScreen())
//                }
//            }
//        }
        c2.apply {
            setBounds(301f, 7f, 89f, 89f)
            setOnClickListener {

            }
        }
        c3.apply {
            setBounds(477f, 7f, 89f, 89f)
            setOnClickListener { stageUI.root.animAlpha0(0.4f) { NavigationManager.navigate(TotalBalanceProfileScreen(), MarketsBeginnersGuideScreen()) } }
        }
    }

    private fun AdvancedGroup.addProfMenu() {
        val c2 = Actor()
        val c3 = Actor()
        addActors(c2,c3)
        c2.apply {
            setBounds(500f, 1434f, 47f, 47f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
        c2.apply {
            setBounds(595f, 1434f, 47f, 47f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
    }

    // ---------------------------------------------------
    // Anim
    // ---------------------------------------------------

//    private suspend fun animMenu() = CompletableDeferred<Unit>().also { continuation ->
//        runGDX {
//            menuImg.addAction(
//                Actions.sequence(
//                    Actions.moveTo(10f, -3f, 0.33f, Interpolation.swing),
//                    Actions.run { continuation.complete(Unit) }
//                ))
//
//        }
//    }.await()

}