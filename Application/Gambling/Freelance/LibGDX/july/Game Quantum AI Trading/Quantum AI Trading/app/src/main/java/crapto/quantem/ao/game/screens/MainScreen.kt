package crapto.quantem.ao.game.screens

import android.content.Intent
import android.net.Uri
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.google.android.play.core.review.ReviewManagerFactory
import crapto.quantem.ao.BuildConfig.APPLICATION_ID
import crapto.quantem.ao.R
import crapto.quantem.ao.appContext
import crapto.quantem.ao.game.actors.BitCoin
import crapto.quantem.ao.game.actors.Settings
import crapto.quantem.ao.game.actors.scroll.VerticalGroup
import crapto.quantem.ao.game.game
import crapto.quantem.ao.game.manager.FontTTFManager
import crapto.quantem.ao.game.manager.NavigationManager
import crapto.quantem.ao.game.manager.SpriteManager
import crapto.quantem.ao.game.utils.GameColor
import crapto.quantem.ao.game.utils.actor.disable
import crapto.quantem.ao.game.utils.actor.enable
import crapto.quantem.ao.game.utils.actor.setOnClickListener
import crapto.quantem.ao.game.utils.advanced.AdvancedGroup
import crapto.quantem.ao.game.utils.advanced.AdvancedScreen
import crapto.quantem.ao.game.utils.advanced.AdvancedStage
import crapto.quantem.ao.util.log

class MainScreen: AdvancedScreen() {

    // MainGroup
    private val accountImage by lazy { Image(SpriteManager.ListRegion.ACCOUNT.regionList[NavigationManager.key ?: 0]) }
    private val balanceLabel = Label("${number(1,50, 1)}." + number(0, 9, 4) + " ETH", Label.LabelStyle(FontTTFManager.Regular.font_81.font, GameColor.tial))
    private val percentLabel = Label("$${number(1,25, 1)}," + number(0, 9, 3), Label.LabelStyle(FontTTFManager.Regular.font_31.font, Color.WHITE))
    private val bonusLabel   = Label("+${number(0,5, 1)}." + number(0, 9, 1) + "%", Label.LabelStyle(FontTTFManager.Regular.font_31.font, GameColor.gren))
    private val palankaImage = Image(SpriteManager.GameRegion.PALAN.region)
    private val vertGroup    = VerticalGroup(57f, startGap = 0f, direction = VerticalGroup.Direction.UP)
    private val vscrollPane  = ScrollPane(vertGroup)
    private val addToken     = Image(SpriteManager.GameRegion.ADD_TOKEN.region)

    // Stage
    private val home = Image(SpriteManager.GameRegion.HOMI.region)
    private val mark = Image(SpriteManager.GameRegion.MARKEK.region)
    private val sett = Image(SpriteManager.GameRegion.SETTICKKK.region)
    private val settings = Settings()


    override fun show() {
        setBackgrounds(SpriteManager.GameRegion.HOME.region)
        super.show()
    }

    override fun AdvancedGroup.addActorsOnGroup() {
        addAcc()
        addBalance()
        addPalanka()
        addVerScr()

        stageUI.apply {
            addMenu()
            addSettings()
        }
    }

    // ---------------------------------------------------
    // Add Actors on Main Group
    // ---------------------------------------------------

    private fun AdvancedGroup.addAcc() {
        addActor(accountImage)
        accountImage.setBounds(16f, 1283f, 331f, 74f)
    }

    private fun AdvancedGroup.addBalance() {
        addActors(balanceLabel, percentLabel, bonusLabel)
        balanceLabel.apply {
            setBounds(19f, 1076f, 638f, 145f)
            setAlignment(Align.center)
        }
        percentLabel.apply {
            setBounds(115f, 1061f, 235f, 30f)
            setAlignment(Align.right)
        }
        bonusLabel.apply {
            setBounds(379f, 1061f, 168f, 30f)
        }
    }

    private fun AdvancedGroup.addPalanka() {
        addActor(palankaImage)
        palankaImage.setBounds(16f, 765f, 594f, 242f)

        val actorSent  = Actor()
        val actorTerms = Actor()
        val actorRate  = Actor()

        addActors(actorSent, actorTerms, actorRate)

        actorSent.apply {
            setBounds(68f, 950f, 145f, 57f)
            setOnClickListener {
                val text = "Скачивай: ${game.activity.getString(R.string.app_name)}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_SUBJECT, text)
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "https://play.google.com/store/apps/details?id=$APPLICATION_ID"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Отправить:")
                game.activity.startActivity(shareIntent)
            }
        }
        actorTerms.apply {
            setBounds(248f, 950f, 192f, 57f)
            //setOnClickListener { game.activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://golovkoevgenij835.github.io/CryptoSafe/tdfodfo"))) }
        }
        actorRate.apply {
            setBounds(475f, 950f, 135f, 57f)
            setOnClickListener {
                val manager = ReviewManagerFactory.create(appContext)

                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val reviewInfo = task.result

                        val flow = manager.launchReviewFlow(game.activity, reviewInfo)
                        flow.addOnCompleteListener { log("ReviewManagerFactory Complete") }
                    } else log(task.exception?.message ?: "ReviewManagerFactory Error")

                }
            }
        }

    }

    private fun AdvancedGroup.addVerScr() {
        addActor(vscrollPane)
        vscrollPane.setBounds(16f, 232f, 651f, 513f)

        addActor(addToken)
        addToken.apply {
            setBounds(281f, 458f, 148f, 40f)
            setOnClickListener {
                vertGroup.addActor(BitCoin().apply { setSize(649f, 76f) })
            }
        }

        repeat(5) { vertGroup.addActor(BitCoin().apply { setSize(649f, 76f) }) }
    }

    // ---------------------------------------------------
    // Add Actors on Stage
    // ---------------------------------------------------

    private fun AdvancedStage.addMenu() {
        addActors(home, mark, sett)
        home.apply {
            setBounds(16f, 92f, 99f, 118f)
            setOnClickListener {
                drawable = TextureRegionDrawable(SpriteManager.GameRegion.HOMI.region)
                sett.drawable = TextureRegionDrawable(SpriteManager.GameRegion.SETTICKKK.region)
                settings.addAction(Actions.fadeOut(0.5f))
                mainGroup.addAction(Actions.fadeIn(0.5f))
                settings.disable()
            }
        }
        mark.apply {
            setBounds(286f, 94f, 112f, 112f)
            setOnClickListener { NavigationManager.navigate(MuScreen(), MainScreen()) }
        }
        sett.apply {
            setBounds(526f, 95f, 135f, 106f)
            setOnClickListener {
                drawable = TextureRegionDrawable(SpriteManager.GameRegion.SETTI.region)
                home.drawable = TextureRegionDrawable(SpriteManager.GameRegion.HOM_DEF.region)
                settings.addAction(Actions.fadeIn(0.5f))
                mainGroup.addAction(Actions.fadeOut(0.5f))
                settings.enable()
            }
        }
    }

    private fun AdvancedStage.addSettings() {
        addActor(settings)
        settings.setBounds(16f, 391f, 624f, 929f)
        settings.disable()
        settings.addAction(Actions.alpha(0f))
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    private fun number(min: Int, max: Int, count: Int): Long {
        var numStr = ""
        repeat(count) { numStr += (min..max).shuffled().first() }
        return numStr.toLong()
    }

}