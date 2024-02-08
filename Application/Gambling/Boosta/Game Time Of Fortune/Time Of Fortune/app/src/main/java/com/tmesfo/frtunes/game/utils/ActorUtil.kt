package com.tmesfo.frtunes.game.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import kotlinx.coroutines.CompletableDeferred

suspend fun Actor.hideAnim(time: Float = 0f) {
    val completableAnim = CompletableDeferred<Boolean>()

    Gdx.app.postRunnable {
        addAction(Actions.sequence(
            Actions.fadeOut(time),
            Actions.run { completableAnim.complete(true) }
        ))
    }
    completableAnim.await()
}

suspend fun Actor.showAnim(time: Float = 0f) {
    val completableAnim = CompletableDeferred<Boolean>()

    Gdx.app.postRunnable {
        addAction(Actions.sequence(
            Actions.fadeIn(time),
            Actions.run { completableAnim.complete(true) }
        ))
    }
    completableAnim.await()
}

fun List<Actor>.setFillParent() {
    onEach { actor ->
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
}