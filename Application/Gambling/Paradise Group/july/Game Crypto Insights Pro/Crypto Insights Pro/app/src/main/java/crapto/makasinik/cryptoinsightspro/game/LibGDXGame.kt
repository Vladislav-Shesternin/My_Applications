package crapto.makasinik.cryptoinsightspro.game

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.ScreenUtils
import crapto.makasinik.cryptoinsightspro.MainActivity
import crapto.makasinik.cryptoinsightspro.game.manager.NavigationManager
import crapto.makasinik.cryptoinsightspro.game.screens.SaploshScreen
import crapto.makasinik.cryptoinsightspro.game.utils.GameColor
import crapto.makasinik.cryptoinsightspro.game.utils.advanced.AdvancedGame

lateinit var game: LibGDXGame private set
var colorPupka = GameColor.puplik

class LibGDXGame(val activity: MainActivity) : AdvancedGame() {

    lateinit var assetManager: AssetManager private set

    override fun create() {
        game         = this
        assetManager = AssetManager()

        NavigationManager.navigate(SaploshScreen())
    }

    override fun render() {
        ScreenUtils.clear(colorPupka)
        super.render()
    }

    override fun dispose() {
        super.dispose()
        assetManager.dispose()
    }

}