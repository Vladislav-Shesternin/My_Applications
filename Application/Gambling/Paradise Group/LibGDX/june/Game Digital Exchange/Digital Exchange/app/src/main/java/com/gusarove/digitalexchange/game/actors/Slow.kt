package com.gusarove.digitalexchange.game.actors

import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.gusarove.digitalexchange.game.actors.scroll.VerticalGroup
import com.gusarove.digitalexchange.game.manager.SpriteManager
import com.gusarove.digitalexchange.game.utils.actor.setOnClickListener
import com.gusarove.digitalexchange.game.utils.advanced.AdvancedGroup

class Slow: AdvancedGroup() {

    private val moreRegion = SpriteManager.GameRegion.MORE.region
    private val lessRegion = SpriteManager.GameRegion.LESS.region
    private val vertGroupe = VerticalGroup(44f)
    private val scrollPane = ScrollPane(vertGroupe)


    private val cnopka = Image(moreRegion)
    private var isShow = true

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(Image(SpriteManager.GameRegion.SLOW.region))
            addActor(cnopka)
            cnopka.apply {
                setBounds(634f, 1594f, 193f, 64f)
                setOnClickListener { if (isShow) show() else hide() }
            }

            addActor(scrollPane)
            scrollPane.setBounds(12f, 177f, 843f, 1406f)

            repeat((15..50).shuffled().first()) {
                vertGroupe.addActor(ShopItem().apply { setSize(803f, 126f) })
            }
        }
    }

    // ---------------------------------------------------
    // Logic
    // ---------------------------------------------------

    fun show() {
        isShow = false
        cnopka.drawable = TextureRegionDrawable(lessRegion)
        addAction(Actions.moveTo(0f, 14f, 0.4f))
    }

    fun hide() {
        isShow = true
        cnopka.drawable = TextureRegionDrawable(moreRegion)
        addAction(Actions.moveTo(0f, -822f, 0.4f))
    }

}