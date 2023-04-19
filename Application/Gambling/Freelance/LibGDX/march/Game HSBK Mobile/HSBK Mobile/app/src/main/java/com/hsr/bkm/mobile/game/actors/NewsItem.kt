package com.hsr.bkm.mobile.game.actors

import android.content.Intent
import android.net.Uri
import android.opengl.GLES20
import android.opengl.GLUtils
import androidx.core.content.ContextCompat.startActivity
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.Align
import com.hsr.bkm.mobile.game.actors.button.AButton
import com.hsr.bkm.mobile.game.actors.button.AButtonStyle
import com.hsr.bkm.mobile.game.actors.label.ALabelStyle
import com.hsr.bkm.mobile.game.game
import com.hsr.bkm.mobile.game.utils.TextureEmpty
import com.hsr.bkm.mobile.game.utils.advanced.AdvancedGroup
import com.squareup.picasso.Picasso


class NewsItem(
    val headline: String?,
    val imageUrl: String?,
    val url     : String?,
    val endBlock: () -> Unit = {}
): AdvancedGroup() {

    private val infoBtn    = AButton(AButtonStyle.info)
    val favoritBtn         = AButton(AButtonStyle.favorit)
    val sendBtn            = AButton(AButtonStyle.send)
    private val image      = Image(if (imageUrl != null) {
        getTextureFromUrl(imageUrl)
    } else {
        endBlock()
        TextureEmpty
    })
    private val label      = Label(headline, ALabelStyle.style(ALabelStyle.Inter.Regular._30))
    private val scrollPane = ScrollPane(label)



    override fun sizeChanged() {
        if(width > 0 && height > 0) addActorsOnGroup()
    }

    private fun addActorsOnGroup() {
        addImage()
        addLabel()
        addInfoBtn()
        addSendBtn()
        addFavoritBtn()
    }

    // ------------------------------------------------------------------------
    // Add Actor
    // ------------------------------------------------------------------------

    private fun addImage() {
        addActor(image)
        image.setBounds(0f, 204f, 660f, 350f)
    }

    private fun addLabel() {
        addActor(scrollPane)
        scrollPane.setBounds(0f, 0f, 570f, 175f)

        label.apply {
            wrap = true
            setAlignment(Align.top, Align.center)
        }
    }

    private fun addInfoBtn() {
        addActor(infoBtn)
        infoBtn.setBounds(604f, 22f, 52f, 52f)
        infoBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url ?: "https://google.com/"))
            game.activity.startActivity(browserIntent)
//            BebViewController.bebUrlFlow.value = url ?: "https://google.com/"
//            MainActivity.isBebViewVisibleFlow.value = true
        }
    }

    var favoritBlock: () -> Unit = {}

    private fun addSendBtn() {
        addActor(sendBtn)
        sendBtn.setBounds(604f, 107f, 52f, 52f)

        sendBtn.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_SUBJECT,"Новость дня!\n")
                putExtra(Intent.EXTRA_TEXT,url ?: "https://google.com/")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Отправить новость:")
            game.activity.startActivity(shareIntent)
        }
    }

    private fun addFavoritBtn() {
        addActor(favoritBtn)
        favoritBtn.setBounds(588f, 204f, 72f, 64f)

        favoritBtn.setOnClickListener { favoritBlock() }
    }


    // ------------------------------------------------------------------------
    // Logic
    // ------------------------------------------------------------------------

    private fun getTextureFromUrl(url: String): Texture {
        return try {
            val bitmap = Picasso.get().load(url).get()

            val tex = Texture(bitmap.width, bitmap.height, Pixmap.Format.RGBA8888)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.textureObjectHandle)
            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
            bitmap.recycle()

            endBlock()
            tex
        } catch (e: Exception) {
            endBlock()
            TextureEmpty
        }
    }


}