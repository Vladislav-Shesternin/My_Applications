package novibet.leoforos.irakloiu.office.helper.game.screens

import android.view.View
import com.badlogic.gdx.scenes.scene2d.ui.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import novibet.leoforos.irakloiu.office.helper.MainActivity
import novibet.leoforos.irakloiu.office.helper.game.LibGDXGame
import novibet.leoforos.irakloiu.office.helper.game.manager.SpriteManager
import novibet.leoforos.irakloiu.office.helper.game.utils.TIME_ANIM_SCREEN_ALPHA
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.animHide
import novibet.leoforos.irakloiu.office.helper.game.utils.actor.setBounds
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedScreen
import novibet.leoforos.irakloiu.office.helper.game.utils.advanced.AdvancedStage
import novibet.leoforos.irakloiu.office.helper.game.utils.region
import novibet.leoforos.irakloiu.office.helper.util.log
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import novibet.leoforos.irakloiu.office.helper.game.utils.Layout.Splash as LS

class LoaderScreen(override val game: LibGDXGame) : AdvancedScreen() {

    private val progressFlow     = MutableStateFlow(0f)
    private var isFinishLoading  = false
    private var isFinishProgress = false
    private var isFinishAnim     = false

//    private val parameter = FontParameter()
//
//    private val progressLabel by lazy { Label("", Label.LabelStyle(fontGeneratorKaushanScript_Regular.generateFont(parameter.setCharacters(CharType.NUMBERS.chars+"%").setSize(39)), GameColor.text)) }
    private val titleImg by lazy { Image(game.spriteUtilSplash.TITLE) }

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

    companion object {
        fun decrypt(encryptedText: String, password: String): String {
            val data = android.util.Base64.decode(encryptedText, android.util.Base64.DEFAULT)

            val salt = ByteArray(16)
            System.arraycopy(data, 0, salt, 0, salt.size)
            val iv = ByteArray(16)
            System.arraycopy(data, salt.size, iv, 0, iv.size)
            val encrypted = ByteArray(data.size - salt.size - iv.size)
            System.arraycopy(data, salt.size + iv.size, encrypted, 0, encrypted.size)

            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val spec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
            val tmp = factory.generateSecret(spec)
            val secretKey = SecretKeySpec(tmp.encoded, "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
            val decrypted = cipher.doFinal(encrypted)

            return String(decrypted, Charsets.UTF_8)
        }
    }

    override fun AdvancedStage.addActorsOnStageUI() {
        addProgress()
        isFinishAnim = true
    }

    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------

    private fun AdvancedStage.addProgress() {
        addActor(titleImg)
        titleImg.setBounds(LS.title)
    }

    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun loadSplashAssets() {
        with(game.spriteManager) {
            loadableTextureList = mutableListOf(SpriteManager.EnumTexture.BACKGROUND.data, SpriteManager.EnumTexture.TITLE.data)
            loadTexture()

            game.assetManager.finishLoading()

            initTexture()
        }
    }

    private fun loadAssets() {
        with(game.spriteManager) {
            loadableAtlasList = novibet.leoforos.irakloiu.office.helper.game.manager.SpriteManager.EnumAtlas.values().map { it.data }.toMutableList()
            loadAtlas()
            loadableTextureList = novibet.leoforos.irakloiu.office.helper.game.manager.SpriteManager.EnumTexture.values().map { it.data }.toMutableList()
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
//                    runGDX { progressLabel.setText("$progress%") }
                    if (progress % 50 == 0) log("progress = $progress%")
                    if (progress == 100) isFinishProgress = true

                    delay((7..11).shuffled().first().toLong())
                }
            }
        }
    }

    class Opener(val act: MainActivity) {
        private fun openPrivacy() {

        }
    }

    private fun isFinish() {
        if (isFinishProgress && isFinishAnim) {
            isFinishAnim = false

            stageUI.root.animHide(TIME_ANIM_SCREEN_ALPHA) {
                game.activity.lottie.hideLoader()
                game.navigationManager.navigate(RulesScreen::class.java.name)
            }
        }
    }


}