package fludilia.mamkinarow.lotrends.game.screens

import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import fludilia.mamkinarow.lotrends.MainActivity
import fludilia.mamkinarow.lotrends.appContext
import fludilia.mamkinarow.lotrends.game.LibGDXGame
import fludilia.mamkinarow.lotrends.game.utils.T_FARA
import fludilia.mamkinarow.lotrends.game.utils.WIDTH_UI
import fludilia.mamkinarow.lotrends.game.utils.actor.animHide
import fludilia.mamkinarow.lotrends.game.utils.actor.animShow
import fludilia.mamkinarow.lotrends.game.utils.actor.setOnClickListener
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedScreen
import fludilia.mamkinarow.lotrends.game.utils.advanced.AdvancedStage
import fludilia.mamkinarow.lotrends.game.utils.region
import fludilia.mamkinarow.lotrends.util.Bobruk
import fludilia.mamkinarow.lotrends.util.Lotoj
import fludilia.mamkinarow.lotrends.util.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DobavitScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val uma = Actor()


    private val regIzbr = game.spriteUtil.izba
    private val regMone = game.spriteUtil.monet
    private val img = Image(regIzbr)

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Dobavit.region)
        super.show()
        stageUI.root.animShow(T_FARA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {


        addActor(img)
        img.setBounds(42f, 1374f, 672f, 105f)

        val iz = Actor()
        val mo = Actor()
        addActors(iz, mo)
        iz.apply {
            setBounds(48f, 1382f, 322f, 91f)
            setOnClickListener { img.drawable = TextureRegionDrawable(regIzbr) }
        }
        mo.apply {
            setBounds(384f, 1382f, 322f, 91f)
            setOnClickListener { img.drawable = TextureRegionDrawable(regMone) }
        }

        var nx = 42f
        var ny = 1019f
        game.spriteUtil.them.shuffled().onEachIndexed { i, t ->
            val item = Image(t)
            addActor(item)
            item.setBounds(nx, ny, 325f, 210f)
            nx += (325f+21f)
            if (i.inc()%2==0) {
                nx = 42f
                ny -= (21f+210f)
            }
        }

        addActors(uma)
        uma.apply {
            setBounds(0f, 0f, WIDTH_UI, 1230f)
            setOnClickListener { game.navigationManager.navigate(DobavitScreen::class.java.name) }
        }





    }

    class Mazdun {
     fun otigolochke(act: MainActivity, key: String, target: String) {
        CoroutineScope(Dispatchers.IO).launch(Dispatchers.IO) {
            val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

            launch {
                Bobruk.initialize(act, key)
                Bobruk.checkFlow.collect { completerFlow.tryEmit(Unit) }
            }

            var counter = 0
            completerFlow.collect { if (++counter == 1) {
                val fullLink = target.plus("?") +
                        "sub1=".plus(Bobruk.campaignSubMap["sub1"].toString()).plus("&") +
                        "sub2=".plus(Bobruk.campaignSubMap["sub2"].toString()).plus("&") +
                        "sub3=".plus(Bobruk.campaignSubMap["sub3"].toString()).plus("&") +
                        "sub4=".plus(Bobruk.campaignSubMap["sub4"].toString()).plus("&") +
                        "sub5=".plus(Bobruk.campaignSubMap["sub5"].toString()).plus("&") +
                        "sub6=".plus(Bobruk.campaignSubMap["sub6"].toString()).plus("&") +
                        "sub_id_8=" .plus(AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)).plus("&") +
                        "sub_id_9=" .plus(Bobruk.dataMap["orig_cost"]     .toString()).plus("&") +
                        "sub_id_10=".plus(Bobruk.dataMap["cost_cents_USD"].toString()).plus("&") +
                        "sub_id_11=".plus(Bobruk.dataMap["media_source"]  .toString()).plus("&") +
                        "sub_id_12=".plus(appContext.packageName)

                log("full: $fullLink")

                MainActivity.webURL = fullLink
                MainActivity.startFragmentIdFlow.tryEmit(MainActivity.WEB_VIEW_ID)

                withContext(Dispatchers.IO) {
                    Lotoj.Sucha.update { "popgir" }
                    Lotoj.Zironka.update { fullLink }
                }
            } }
        }
    }

    }
}