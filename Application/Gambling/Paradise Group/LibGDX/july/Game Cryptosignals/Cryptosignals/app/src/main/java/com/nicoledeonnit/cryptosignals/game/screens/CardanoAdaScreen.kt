package com.nicoledeonnit.cryptosignals.game.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBox
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBoxGroup
import com.nicoledeonnit.cryptosignals.game.actors.checkbox.ACheckBoxStyle
import com.nicoledeonnit.cryptosignals.game.manager.FontTTFManager
import com.nicoledeonnit.cryptosignals.game.manager.NavigationManager
import com.nicoledeonnit.cryptosignals.game.manager.SpriteManager
import com.nicoledeonnit.cryptosignals.game.utils.Stepan
import com.nicoledeonnit.cryptosignals.game.utils.actor.animpokazat
import com.nicoledeonnit.cryptosignals.game.utils.actor.setOnClickListener
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedScreen
import com.nicoledeonnit.cryptosignals.game.utils.advanced.AdvancedStage
import com.nicoledeonnit.cryptosignals.game.utils.numStr


class CardanoAdaScreen: AdvancedScreen() {

    val fodo = listOf(
        "Uniswap",
                "Tether",
                "Bab",
                "Burst",
                "Bake",
                "Band",
                "Cardan",
    )

    private val palma = Image(SpriteManager.Palas.PALANKAGA.region)
    private val rands = Image(SpriteManager.FanariK.GRA.regionList.random())
    private val pered = Image(SpriteManager.Palas.PEREDACHA.region)
    private val imake = Image(SpriteManager.FanariK.SAF.regionList.random())
    private val namsa by lazy { Label("${fodo.random()} / ADA", Label.LabelStyle(FontTTFManager.Medium.font_30.font, Color.WHITE)).apply { addAction(Actions.alpha(0.8f)) } }
    private val cumis by lazy { Label("${numStr(1, 9, 3)},${numStr(0,9,2)}", Label.LabelStyle(FontTTFManager.Regular.font_72.font, Color.WHITE)).apply { addAction(Actions.alpha(0.8f)) } }
    private val tifli by lazy { Label("${numStr(1, 99, 1)}.${numStr(0, 9, 2)}%", Label.LabelStyle(FontTTFManager.Medium.font_23.font, Stepan.giga)) }
    private val v1 by lazy { Label("${numStr(10, 99, 1)}", Label.LabelStyle(FontTTFManager.Light.font_42.font, Color.WHITE)) }
    private val v2 by lazy { Label("${numStr(1, 99, 1)}.${numStr(0, 9, 2)}", Label.LabelStyle(FontTTFManager.Light.font_42.font, Color.WHITE)) }
    private val v3 by lazy { Label("${numStr(1, 99, 1)}.${numStr(0, 9, 2)}", Label.LabelStyle(FontTTFManager.Light.font_42.font, Color.WHITE)) }

    override fun AdvancedStage.addActorsOnStageUI() {
        guchiKakMan()
        val ba = Actor()
        addActor(ba)
        ba.apply {
            setBounds(44f, 1611f, 104f, 104f)
            setOnClickListener { NavigationManager.back() }
        }

        addActors(imake, namsa, cumis, tifli)
        imake.setBounds(68f, 1442f, 124f, 124f)
        namsa.setBounds(218f, 1535f, 313f, 36f)
        cumis.setBounds(218f, 1437f, 306f, 89f)
        tifli.setBounds(712f, 1490f, 72f, 28f)
        addActors(v1, v2, v3)
        v1.setBounds(100f, 1264f, 206f, 52f)
        v2.setBounds(406f, 1264f, 161f, 52f)
        v3.setBounds(691f, 1264f, 115f, 52f)

        val _h = ACheckBox(ACheckBoxStyle.oh)
        val _d = ACheckBox(ACheckBoxStyle.od)
        val _w = ACheckBox(ACheckBoxStyle.ow)
        val _m = ACheckBox(ACheckBoxStyle.om)
        val _y = ACheckBox(ACheckBoxStyle.oy)
        val _a = ACheckBox(ACheckBoxStyle.oa)

        addActors(_h,_d,_w,_m,_y,_a,)
        val rasik = ACheckBoxGroup()
        _h.apply {
            setBounds(68f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _d.apply {
            setBounds(219f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _w.apply {
            setBounds(339f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _m.apply {
            setBounds(460f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _y.apply {
            setBounds(576f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _a.apply {
            setBounds(697f, 1117f, 103f, 68f)
            checkBoxGroup = rasik
            setOnCheckListener { if (it) saskeNarutoUzimake() }
        }
        _h.check()

        root.animpokazat(0.8f)
    }

    var sik = 0
    private fun saskeNarutoUzimake() {
        if (sik != 0) rands.drawable = TextureRegionDrawable(SpriteManager.FanariK.GRA.regionList.random())
        sik++
    }

    // ---------------------------------------------------
    // Add Actors
    // ---------------------------------------------------

    private fun AdvancedStage.guchiKakMan() {
        addActor(palma)
        palma.setBounds(68f, 1117f,748f, 573f)
        addActor(rands)
        rands.setBounds(68f, 311f,741f, 780f)
        addActor(pered)
        pered.setBounds(68f, 68f,741f, 217f)
        pered.setOnClickListener { NavigationManager.back() }
    }

}