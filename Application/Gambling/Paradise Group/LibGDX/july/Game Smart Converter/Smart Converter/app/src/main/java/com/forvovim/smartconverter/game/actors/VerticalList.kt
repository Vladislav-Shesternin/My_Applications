package com.forvovim.smartconverter.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.forvovim.smartconverter.game.utils.actor.disable
 import com.forvovim.smartconverter.game.utils.actor.setOnClickListener
 import com.forvovim.smartconverter.game.utils.advanced.AdvancedGroup

class VerticalList: AdvancedGroup() {

    private val vg = VerticalGroup(25f)
    private val sp = ScrollPane(vg)

    var block: (Int) -> Unit = {}

    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            repeat(21) { idiNaX ->
                vg.addActor(Artem(idiNaX).apply {
                    setSize(547f, 66f)
                    setOnClickListener {
                        this@VerticalList.disable()
                        block(idiNaX)
                    }
                })
            }

        }
    }

    fun update() {
        vg.clearChildren()

        (0..20).shuffled().onEach { idiNaX ->
            vg.addActor(Artem(idiNaX).apply {
                setSize(547f, 66f)
                setOnClickListener {
                    this@VerticalList.disable()
                    block(idiNaX)
                }
            })
        }
    }

}