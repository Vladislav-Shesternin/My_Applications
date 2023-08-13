package com.metanit.metrixmanager.game.actors

 import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
 import com.metanit.metrixmanager.game.actors.checkbox.ACheckBox
 import com.metanit.metrixmanager.game.actors.checkbox.ACheckBoxStyle
 import com.metanit.metrixmanager.game.utils.advanced.AdvancedGroup

class Cbghshjd: AdvancedGroup() {

    private val vg = VerticalGroup(26f, startGap = 100f)
    private val sp = ScrollPane(vg)

    private val hfdhfh = listOf(
        ACheckBox(ACheckBoxStyle.f1),
        ACheckBox(ACheckBoxStyle.f2),
        ACheckBox(ACheckBoxStyle.f3),
        ACheckBox(ACheckBoxStyle.f4),
        ACheckBox(ACheckBoxStyle.f5),
        ACheckBox(ACheckBoxStyle.f6),
        ACheckBox(ACheckBoxStyle.f7),
    )


    override fun sizeChanged() {
        if (width > 0 && height > 0) {
            addAndFillActor(sp)

            hfdhfh.shuffled().onEach { loto ->
                vg.addActor(loto.apply {
                    setSize(631f, 97f)
                })
            }

        }
    }

}