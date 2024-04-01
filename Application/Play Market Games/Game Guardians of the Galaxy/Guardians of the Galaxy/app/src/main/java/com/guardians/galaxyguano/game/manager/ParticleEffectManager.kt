package com.guardians.galaxyguano.game.manager

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.graphics.g2d.ParticleEffect

class ParticleEffectManager(var assetManager: AssetManager) {

    private val params         = ParticleEffectLoader.ParticleEffectParameter()
    private val particleLoader = ParticleEffectLoader(InternalFileHandleResolver())

    var loadableParticleEffectList = mutableListOf<ParticleEffectData>()

    fun load() {
        assetManager.setLoader(ParticleEffect::class.java, ".p", particleLoader)
        loadableParticleEffectList.onEach { assetManager.load(it.path, ParticleEffect::class.java, params.apply {
            atlasFile = "particle/particle.atlas"
        }) }
    }

    fun init() {
        loadableParticleEffectList.onEach { it.effect = assetManager[it.path, ParticleEffect::class.java] }
        loadableParticleEffectList.clear()
    }

    enum class EnumParticleEffect(val data: ParticleEffectData) {
        ASTEROID(ParticleEffectData("particle/asteroid.p")),
    }

    data class ParticleEffectData(val path: String) {
        lateinit var effect: ParticleEffect
    }

}