package com.tutotoons.app.kpopsiescuteunicornpet.game.manager.util

import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.tutotoons.app.kpopsiescuteunicornpet.game.manager.SpriteManager

class SpriteUtil {

     class Loader {
          val background_purple = SpriteManager.EnumTexture.background_purple.data.texture
     }

     class All {
          private fun getRegion(name: String): TextureRegion = SpriteManager.EnumAtlas.all.data.atlas.findRegion(name)
          private fun getNinePath(name: String): NinePatch = SpriteManager.EnumAtlas.all.data.atlas.createPatch(name)

          val back_def          = getRegion("back_def")
          val back_prs          = getRegion("back_prs")
          val background_circle = getRegion("background_circle")
          val bonusic           = getRegion("bonusic")
          val btn_def           = getRegion("btn_def")
          val btn_dis           = getRegion("btn_dis")
          val btn_prs           = getRegion("btn_prs")
          val fruit_panel       = getRegion("fruit_panel")
          val go_def            = getRegion("go_def")
          val go_prs            = getRegion("go_prs")
          val knopi             = getRegion("knopi")
          val main_circle       = getRegion("main_circle")
          val progressimo       = getRegion("progressimo")
          val quit_def          = getRegion("quit_def")
          val quit_prs          = getRegion("quit_prs")
          val records           = getRegion("records")
          val next              = getRegion("next")
          val start_game        = getRegion("start_game")
          val hand              = getRegion("hand")
          val super_game_def    = getRegion("super_game_def")
          val super_game_press  = getRegion("super_game_press")
          val big_game_def      = getRegion("big_game_def")
          val big_game_press    = getRegion("big_game_press")

          val items = List(8) { getRegion("${it.inc()}") }

          val gamedofit       = SpriteManager.EnumTexture.gamedofit.data.texture
          val music_adn_sound = SpriteManager.EnumTexture.music_adn_sound.data.texture
          val p_masaka        = SpriteManager.EnumTexture.p_masaka.data.texture
          val raeleus         = SpriteManager.EnumTexture.raeleus.data.texture
          val b_back          = SpriteManager.EnumTexture.b_back.data.texture
          val b_music         = SpriteManager.EnumTexture.b_music.data.texture
          val b_sound         = SpriteManager.EnumTexture.b_sound.data.texture
          val dialog          = SpriteManager.EnumTexture.dialog.data.texture

          val visible_area = getNinePath("visible_area")

     }

}