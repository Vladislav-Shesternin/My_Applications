package qbl.bisriymyach.QuickBall

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import qbl.bisriymyach.QuickBall.fastergan.suchka

open class aceton: ApplicationListener {

    private var screen: suchka? = null

    // ---------------------------------------------------
    // Override
    // ---------------------------------------------------

    override fun create() {}

    override fun render() {
        screen?.render(Gdx.graphics.deltaTime)
    }

    override fun resize(width: Int, height: Int) {
        screen?.resize(width, height)
    }

    override fun pause() {
        screen?.pause()
    }

    override fun resume() {
        screen?.resume()
    }

    override fun dispose() {
        screen?.dispose()
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun updateScreen(screen: suchka) {
        this.screen?.dispose()
        this.screen = screen.apply {
            resize(Gdx.graphics.width, Gdx.graphics.height)
            show()
        }
    }

}