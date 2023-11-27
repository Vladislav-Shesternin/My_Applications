package com.veldan.lbjt.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.TextureLoader
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class SpriteManager(var assetManager: AssetManager) {

    var loadableAtlasList   = mutableListOf<AtlasData>()
    var loadableTextureList = mutableListOf<TextureData>()

    fun loadAtlas() {
        loadableAtlasList.onEach { assetManager.load(it.path, TextureAtlas::class.java) }
    }

    fun loadTexture() {
        loadableTextureList.onEach {
            assetManager.load(it.path, Texture::class.java, TextureLoader.TextureParameter().apply {
                minFilter = Texture.TextureFilter.Linear
                magFilter = Texture.TextureFilter.Linear
                genMipMaps = true
            })
        }
    }

    fun initAtlas() {
        loadableAtlasList.onEach { it.atlas = assetManager[it.path, TextureAtlas::class.java] }
        loadableAtlasList.clear()
    }

    fun initTexture() {
        loadableTextureList.onEach { it.texture = assetManager[it.path, Texture::class.java] }
        loadableTextureList.clear()
    }

    fun initAtlasAndTexture() {
        initAtlas()
        initTexture()
    }


    enum class EnumAtlas(val data: AtlasData) {
        GAME(AtlasData("atlas/game.atlas")),
    }

    enum class EnumTexture(val data: TextureData) {
        YAN_BACKGROUND(TextureData("textures/yan_background.png")),
        YIN_BACKGROUND(TextureData("textures/yin_background.png")),
        MASK_ICON(     TextureData("textures/mask_icon.png")     ),
        VELDAN_ICON(   TextureData("textures/veldan_icon.png")   ),
    }

    enum class EnumAtlas_GeneralInformation(val data: AtlasData) {
        ANIM_OBAMA(AtlasData("atlas/anim/tutorials/general_information/obama.atlas")),
    }
    enum class EnumTexture_GeneralInformation(val data: TextureData) {
        I1( TextureData("textures/tutorials/general_information/i1.png") ),
        I2( TextureData("textures/tutorials/general_information/i2.png") ),
        I3( TextureData("textures/tutorials/general_information/i3.png") ),
        I4( TextureData("textures/tutorials/general_information/i4.png") ),
        I5( TextureData("textures/tutorials/general_information/i5.png") ),
        I6( TextureData("textures/tutorials/general_information/i6.png") ),
        I7( TextureData("textures/tutorials/general_information/i7.png") ),
        I8( TextureData("textures/tutorials/general_information/i8.png") ),
        I9( TextureData("textures/tutorials/general_information/i9.png") ),
        I10(TextureData("textures/tutorials/general_information/i10.png")),
        I11(TextureData("textures/tutorials/general_information/i11.png")),
        I12(TextureData("textures/tutorials/general_information/i12.png")),
    }

    enum class EnumAtlas_JointMouse(val data: AtlasData) {
        ANIM_VIDEO_1_0(AtlasData("atlas/anim/tutorials/joint_mouse/video_1/video_1_0.atlas")),
        ANIM_VIDEO_1_1(AtlasData("atlas/anim/tutorials/joint_mouse/video_1/video_1_1.atlas")),
        ANIM_VIDEO_1_2(AtlasData("atlas/anim/tutorials/joint_mouse/video_1/video_1_2.atlas")),
        ANIM_VIDEO_1_3(AtlasData("atlas/anim/tutorials/joint_mouse/video_1/video_1_3.atlas")),

        ANIM_VIDEO_2_0(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_0.atlas")),
        ANIM_VIDEO_2_1(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_1.atlas")),
        ANIM_VIDEO_2_2(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_2.atlas")),
        ANIM_VIDEO_2_3(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_3.atlas")),
        ANIM_VIDEO_2_4(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_4.atlas")),
        ANIM_VIDEO_2_5(AtlasData("atlas/anim/tutorials/joint_mouse/video_2/video_2_5.atlas")),

        ANIM_VIDEO_3_0(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_0.atlas")),
        ANIM_VIDEO_3_1(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_1.atlas")),
        ANIM_VIDEO_3_2(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_2.atlas")),
        ANIM_VIDEO_3_3(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_3.atlas")),
        ANIM_VIDEO_3_4(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_4.atlas")),
        ANIM_VIDEO_3_5(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_5.atlas")),
        ANIM_VIDEO_3_6(AtlasData("atlas/anim/tutorials/joint_mouse/video_3/video_3_6.atlas")),

        MEM_1(AtlasData("atlas/anim/tutorials/joint_mouse/mem/mem_1.atlas")),
        MEM_2(AtlasData("atlas/anim/tutorials/joint_mouse/mem/mem_2.atlas")),
    }
    enum class EnumTexture_JointMouse(val data: TextureData) {
        I1(TextureData("textures/tutorials/joint_mouse/i1.png")),
    }

    data class AtlasData(val path: String) {
        lateinit var atlas: TextureAtlas
    }

    data class TextureData(val path: String) {
        lateinit var texture: Texture
    }

}