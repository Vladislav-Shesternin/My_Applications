package mst.mysteryof.antientegyptua.game.screens

import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mst.mysteryof.antientegyptua.game.LibGDXGame
import mst.mysteryof.antientegyptua.game.manager.SpriteManager
import mst.mysteryof.antientegyptua.game.utils.GameColor
import mst.mysteryof.antientegyptua.game.utils.TIME_ANIM_SCREEN_ALPHA
import mst.mysteryof.antientegyptua.game.utils.actor.animHide
import mst.mysteryof.antientegyptua.game.utils.actor.setBounds
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedScreen
import mst.mysteryof.antientegyptua.game.utils.advanced.AdvancedStage
import mst.mysteryof.antientegyptua.game.utils.font.FontParameter
import mst.mysteryof.antientegyptua.game.utils.font.FontParameter.CharType
import mst.mysteryof.antientegyptua.game.utils.region
import mst.mysteryof.antientegyptua.game.utils.runGDX
import mst.mysteryof.antientegyptua.util.log
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import mst.mysteryof.antientegyptua.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    companion object {
        fun decrypt(encryptedText: String, secretKey: String): String {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val keySpec = SecretKeySpec(secretKey.toByteArray(Charsets.UTF_8), "AES")
            val iv = IvParameterSpec("1234567890ABCDEF".toByteArray(Charsets.UTF_8))
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv)
            val encryptedBytes = android.util.Base64.decode(encryptedText, android.util.Base64.DEFAULT)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            return String(decryptedBytes, Charsets.UTF_8)
        }
    }

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

    private val parameter = FontParameter()

    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorKaushanScript_Regular.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(39)), GameColor.text)) }

    override fun show() {
        loadSplashAssets()
        setBackground(game.spriteUtilSplash.BACKGROUND.region)
        super.show()
        loadAssets()
        collectProgress()
    }

    override fun render(delta: Float) {
        super.render(delta)
        loadingAssets()
        isFinish()
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addProgress()
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(progressLabel)
        progressLabel.apply {
            setBounds(LS.progress)
            setAlignment(Align.center)
        }
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND.data)
            loadTexture()

            game.assetManager.finishLoading()

            initTexture()
        }
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
            loadTexture()
        }
    }

    private fun initAssets() {
        game.spriteManager.initAtlasAndTexture()
    }

    private fun loadingAssets() {
        if (isFinishLoading.not()) {
            if (game.assetManager.update(16)) {
                isFinishLoading = true
                initAssets()
            }
            progressFlow.value = game.assetManager.progress
        }
    }

    private fun collectProgress() {
        coroutine?.launch {
            var progress = 0
            progressFlow.collect { p ->
                while (progress < (p * 100)) {
                    progress += 1
                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..11).shuffled().first().toLong())
                }
            }
        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(MenuScreen::class.java.name)
            }
        }
    }


}