package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.audio.Music
import qbl.bisriymyach.QuickBall.LibGDXGame
import qbl.bisriymyach.QuickBall.tidams.sosisochki_na_grili
import qbl.bisriymyach.QuickBall.pitopilot.idi_naher
import qbl.bisriymyach.QuickBall.fastergan.suchka
import qbl.bisriymyach.QuickBall.tidams.zavtra_v_shskull
import qbl.bisriymyach.QuickBall.fastergan.mp489
import qbl.bisriymyach.QuickBall.tidams.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoaderScreen(override val game: LibGDXGame) : suchka() {

    private val progressFlow = MutableStateFlow(0f)
    private var isFinishLoading = false
    private var isFinishProgress = false
    private var isFinishAnim = false

    override fun show() {
        loadLoaderAssets()
        setBackBackground(game.loaderAssets.TABEX.mp489)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }

    override fun zavtra_v_shskull.addActorsOnStageUI() {
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadLoaderAssets() {
        with(game.uAns1) {
            loadableTextureList = mutableListOf(idi_naher.EnumTexture.TABEX.data)
            loadTexture()
        }
        game.flagmen.finishLoading()
        game.uAns1.initTexture()
    }

    private fun loadAssets() {
        with(game.uAns1) {
            loadableAtlasList = idi_naher.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = idi_naher.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
        with(game.sosipa) {
            loadableSoundList =
                sosisochki_na_grili.EnumSound.values().map { it.data }.toMutableList()
            load()
        }
    }

    private fun initAssets() {
        game.uAns1.initAtlasAndTexture()
        game.sosipa.init()
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.flagmen.update(16)) {
                isFinishLoading = true
                initAssets()
            }
            progressFlow.value = game.flagmen.progress
        }
    }

    private fun collectProgress() {
        coroutine?.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((10..15).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            game.activity.lottie.yuaA()

            game.flagmen.load("my-trip.ogg", Music::class.java)
            game.flagmen.finishLoading()
            game.MUSICALka = game.flagmen["my-trip.ogg", Music::class.java].apply {
                isLooping = true
                volume = 0.287f
                play()
            }

            game.navigationManager.navigate(Miski::class.java.name)
        }
    }


}