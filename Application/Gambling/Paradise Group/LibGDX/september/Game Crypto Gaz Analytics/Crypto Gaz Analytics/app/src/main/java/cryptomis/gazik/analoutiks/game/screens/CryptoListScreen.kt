package cryptomis.gazik.analoutiks.game.screens

import com.badlogic.gdx.graphics.Color
import cryptomis.gazik.analoutiks.game.LibGDXGame
import cryptomis.gazik.analoutiks.game.actors.scroll.CryptoScrollPane
import cryptomis.gazik.analoutiks.game.utils.TIME_ANIM_SCREEN_ALPHA
import cryptomis.gazik.analoutiks.game.utils.actor.animHide
import cryptomis.gazik.analoutiks.game.utils.actor.animShow
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedScreen
import cryptomis.gazik.analoutiks.game.utils.advanced.AdvancedStage
import cryptomis.gazik.analoutiks.util.log

class CryptoListScreen(override val game: LibGDXGame): AdvancedScreen() {

    private val cryptoScrollPane = CryptoScrollPane(this)

    override fun show() {
        stageUI.root.animHide()
//        setUIBackground(drawerUtil.getRegion(Color.BLUE))
        super.show()
        stageUI.root.animShow(TIME_ANIM_SCREEN_ALPHA)
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addActor(cryptoScrollPane)
        cryptoScrollPane.setBounds(42f, 0f, 663f, 1407f)
    }

}