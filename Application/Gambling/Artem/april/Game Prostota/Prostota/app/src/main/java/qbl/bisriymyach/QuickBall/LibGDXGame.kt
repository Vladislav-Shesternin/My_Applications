package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.ScreenUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import qbl.bisriymyach.QuickBall.fastergan.imporer
import qbl.bisriymyach.QuickBall.pitopilot.neirofemka
import qbl.bisriymyach.QuickBall.tidams.sosisochki_na_grili
import qbl.bisriymyach.QuickBall.pitopilot.idi_naher
import qbl.bisriymyach.QuickBall.tidams.mim
import qbl.bisriymyach.QuickBall.fastergan.giorg
import qbl.bisriymyach.QuickBall.hotvils.LoaderScreen
import qbl.bisriymyach.QuickBall.fastergan.fortone
import qbl.bisriymyach.QuickBall.fastergan.hshshshJ
import qbl.bisriymyach.QuickBall.tidams.log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class LibGDXGame(val activity: Potifin) : aceton() {

    lateinit var flagmen: AssetManager
        private set
    lateinit var navigationManager: neirofemka private set
    lateinit var uAns1: idi_naher private set
    lateinit var sosipa: sosisochki_na_grili private set

    val soundUtil by lazy { mim() }

    var MUSICALka: Music? = null

    private val date = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    val currentDate = formatter.format(date)

    var isBABAna = true

    override fun create() {
        uuu777a.launch(Dispatchers.IO) {
            isBABAna = imporer.Date.get() != currentDate
        }

        navigationManager = neirofemka(this)
        flagmen = AssetManager()





        uAns1 = idi_naher(flagmen)
        sosipa = sosisochki_na_grili(flagmen)

        navigationManager.navigate(LoaderScreen::class.java.name)
    }

    private val backgroundColor = Color.BLACK
    val loaderAssets by lazy { giorg.Delog() }
    val allAssets by lazy { giorg.beliver() }

    val yauau = mutableSetOf<Disposable>()

    val uuu777a = CoroutineScope(Dispatchers.Default)
    val iaiusjdf7 = fortone(uuu777a)

    override fun render() {
        ScreenUtils.clear(backgroundColor)
        super.render()
    }

    override fun dispose() {
        try {
            log("dispose LibGDXGame")
            uuu777a.cancel()
            yauau.hshshshJ()
            hshshshJ(flagmen)
            super.dispose()
        } catch (e: Exception) {
            log("exception: ${e.message}")
        }
    }

}