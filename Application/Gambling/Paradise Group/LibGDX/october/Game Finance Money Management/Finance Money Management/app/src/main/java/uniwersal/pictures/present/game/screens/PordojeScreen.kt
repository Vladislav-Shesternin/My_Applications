package uniwersal.pictures.present.game.screens

import com.appsflyer.AppsFlyerLib
import com.badlogic.gdx.scenes.scene2d.Actor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uniwersal.pictures.present.MainActivity
import uniwersal.pictures.present.appContext
import uniwersal.pictures.present.game.LibGDXGame
import uniwersal.pictures.present.game.utils.Ttime
import uniwersal.pictures.present.game.utils.actor.animHide
import uniwersal.pictures.present.game.utils.actor.animShow
import uniwersal.pictures.present.game.utils.actor.setOnClickListener
import uniwersal.pictures.present.game.utils.advanced.AdvancedScreen
import uniwersal.pictures.present.game.utils.advanced.AdvancedStage
import uniwersal.pictures.present.game.utils.region
import uniwersal.pictures.present.util.AppsflyerUtil
import uniwersal.pictures.present.util.DataStoreManager
import uniwersal.pictures.present.util.log

class PordojeScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val uma = Actor()

    override fun show() {
        stageUI.root.animHide()
        setBackground(game.spriteUtil.Pordoje.region)
        super.show()
        stageUI.root.animShow(Ttime)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActors(uma)
        uma.apply {
            setBounds(40f, 142f,642f, 104f)
            setOnClickListener {
                stageUI.root.animHide(Ttime) {
                    game.navigationManager.navigate(PrajkeScreen::class.java.name)
                }
            }
        }
    }

    class Done(val aaa: MainActivity){

        private val coroutine = CoroutineScope(Dispatchers.IO)

        inner class Pisol {
            fun generateAndOpenLink(key: String, target: String) {
                coroutine.launch {
                    val completerFlow = MutableSharedFlow<Unit>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

                    launch {
                        AppsflyerUtil.initialize(aaa, key)
                        AppsflyerUtil.checkFlow.collect { completerFlow.tryEmit(Unit) }
                    }

                    var counter = 0
                    completerFlow.collect { if (++counter == 1) {
                        val fullLink = target.plus("?") +
                                "sub1=".plus(AppsflyerUtil.campaignSubMap["sub1"].toString()).plus("&") +
                                "sub2=".plus(AppsflyerUtil.campaignSubMap["sub2"].toString()).plus("&") +
                                "sub3=".plus(AppsflyerUtil.campaignSubMap["sub3"].toString()).plus("&") +
                                "sub4=".plus(AppsflyerUtil.campaignSubMap["sub4"].toString()).plus("&") +
                                "sub5=".plus(AppsflyerUtil.campaignSubMap["sub5"].toString()).plus("&") +
                                "sub6=".plus(AppsflyerUtil.campaignSubMap["sub6"].toString()).plus("&") +
                                "sub_id_8=" .plus(AppsFlyerLib.getInstance().getAppsFlyerUID(appContext)).plus("&") +
                                "sub_id_9=" .plus(AppsflyerUtil.dataMap["orig_cost"]     .toString()).plus("&") +
                                "sub_id_10=".plus(AppsflyerUtil.dataMap["cost_cents_USD"].toString()).plus("&") +
                                "sub_id_11=".plus(AppsflyerUtil.dataMap["media_source"]  .toString()).plus("&") +
                                "sub_id_12=".plus(appContext.packageName)

                        log("full: $fullLink")

                        MainActivity.strekoza = fullLink
                        MainActivity.poilo.tryEmit(MainActivity.brehna)

                        withContext(Dispatchers.IO) {
                            DataStoreManager.Key.update { "Potate" }
                            DataStoreManager.Link.update { fullLink }
                        }
                    } }
                }
            }
        }
    }


}