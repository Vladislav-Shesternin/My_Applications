package com.jjjj.ooo.kkk.eer.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.jjjj.ooo.kkk.eer.game.actors.checkbox.CheckBoxGroup
import com.jjjj.ooo.kkk.eer.game.actors.checkbox.CheckBoxStyle
import com.jjjj.ooo.kkk.eer.game.actors.label.LabelStyle
import com.jjjj.ooo.kkk.eer.game.manager.NavigationManager
import com.jjjj.ooo.kkk.eer.game.manager.SpriteManager
import com.jjjj.ooo.kkk.eer.game.util.advanced.AdvancedScreen
import com.jjjj.ooo.kkk.eer.game.util.advanced.AdvancedStage
import com.jjjj.ooo.kkk.eer.game.util.disable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.jjjj.ooo.kkk.eer.game.util.Layout.Game as LG

class GameScreen: AdvancedScreen() {

    private val panel     = Image(SpriteManager.GameRegion.PAN_DEF.region)
    private val panelText = Label("", LabelStyle.fantik).apply {
        wrap = true
        setAlignment(Align.center)
    }
    private val checkGroup = CheckBoxGroup()
    private

    data class Data(
        val question: String,
        val answer  : Boolean,
    )

    private val data = listOf(
        Data(
            "Появился ли Джокер в «Темном рыцаре: восстание»?",
            false,
        ),
        Data(
            "Обладает ли Джокер сверхчеловеческой силой?",
            false,
        ),
        Data(
            "Дебютировал ли Джокер в мультсериале «Бэтмен»?",
            false,
        ),
        Data(
            "Является ли Джокер частью семьи Уэйнов?",
            false,
        ),
        Data(
            "Взорвал ли Джокер Метрополис в «Возвращении Супермена»?",
            false,
        ),
        Data(
            "Появится ли Джокер в «Бэтмен возвращается»?",
            false,
        ),
        Data(
            "Джокер обычно работает один?",
            false,
        ),
        Data(
            "Хьюго Стрэндж когда-нибудь убивал Джокера?",
            false,
        ),
        Data(
            "Знает ли Киборг настоящую личность Джокера?",
            false,
        ),
        Data(
            "Был ли Джокер главным антагонистом в «Долгом Хэллоуине»?",
            false,
        ),

        Data(
            "Выберется ли Джокер из «Темного рыцаря» живым?",
            true,
        ),
        Data(
            "Помогла ли Чудо-женщина сразиться с Джокером?",
            true,
        ),
        Data(
            "Джокер делает себе макияж?",
            true,
        ),
        Data(
            "Джокер когда-либо захватывал Готэм-сити?",
            true,
        ),
        Data(
            "Был ли Джокер в Лечебнице Аркхэм?",
            true,
        ),
        Data(
            "Был ли Джокер замешан в несправедливости?",
            true,
        ),
        Data(
            "Джокер изуродован?",
            true,
        ),
        Data(
            "Принимал ли участие Джокер в ограблении банка в «Темном рыцаре»?",
            true,
        ),
        Data(
            "Джокер купил парк развлечений?",
            true,
        ),
        Data(
            "Был ли Джокер стендап-комиком?",
            true,
        ),
    ).shuffled().first()

    override fun show() {
        setBackgrounds(SpriteManager.CommonRegion.BACKGROUND.region)
        super.show()
    }

    override fun AdvancedStage.addActorsOnStage() {
        root.addAction(Actions.alpha(0f))
        addPanel()
        addCheckBox()
        root.addAction(Actions.fadeIn(1.3f))
    }


    // ------------------------------------------------------------------------
    // Add Actors
    // ------------------------------------------------------------------------
    private fun AdvancedStage.addPanel() {
        addActors(panel, panelText)
        val scroll = ScrollPane(panelText)
        addActor(scroll)

        panel.apply {
            with(LG.panel) { setBounds(x, y, w, h) }
        }
        panelText.apply {
            with(LG.text) {
                setBounds(x, y, w, h)
                scroll.setBounds(x, y, w, h)
            }
            setText(data.question)
        }
    }

    private fun AdvancedStage.addCheckBox() {
        com.jjjj.ooo.kkk.eer.game.actors.checkbox.CheckBox(CheckBoxStyle.not).apply {
            this@addCheckBox.addActor(this)
            checkBoxGroup = checkGroup
            with(LG.checkBox) {
                setBounds(x, y, w, h)
            }

            Label("Нет", LabelStyle.fantik).also { label ->
                addAndFillActor(label)
                label.setAlignment(Align.center)
                label.disable()
            }

            setOnCheckListener {
                if (it) {
                    coroutineMain.launch {
                        Gdx.app.postRunnable {
                            stage.root.disable()

                            panel.drawable = if (data.answer.not()) TextureRegionDrawable(SpriteManager.GameRegion.PAN_GREEN.region)
                            else TextureRegionDrawable(SpriteManager.GameRegion.PAN_RED.region)
                        }
                        delay(1000)
                        Gdx.app.postRunnable {
                            stage.root.addAction(Actions.sequence(
                                Actions.fadeOut(1f),
                                Actions.run { NavigationManager.navigate(GameScreen()) }
                            ))
                        }
                    }
                }
            }
        }
        com.jjjj.ooo.kkk.eer.game.actors.checkbox.CheckBox(CheckBoxStyle.yes).apply {
            this@addCheckBox.addActor(this)
            checkBoxGroup = checkGroup
            with(LG.checkBox) {
                setBounds(x + hs + w, y, w, h)
            }

            Label("Да", LabelStyle.fantik).also { label ->
                addAndFillActor(label)
                label.setAlignment(Align.center)
                label.disable()
            }

            setOnCheckListener {
                if (it) {
                    coroutineMain.launch {
                        Gdx.app.postRunnable {
                            stage.root.disable()

                            panel.drawable = if (data.answer) TextureRegionDrawable(SpriteManager.GameRegion.PAN_GREEN.region)
                            else TextureRegionDrawable(SpriteManager.GameRegion.PAN_RED.region)
                        }
                        delay(1000)

                        Gdx.app.postRunnable {
                            stage.root.addAction(Actions.sequence(
                                Actions.fadeOut(1f),
                                Actions.run { NavigationManager.navigate(GameScreen()) }
                            ))
                        }
                    }
                }
            }
        }
    }

}