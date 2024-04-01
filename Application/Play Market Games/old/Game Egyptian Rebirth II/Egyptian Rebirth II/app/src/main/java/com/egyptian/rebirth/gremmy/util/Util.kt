package com.egyptian.rebirth.gremmy.util

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Widget
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
import com.egyptian.rebirth.length


fun List<Actor>.setFillParent() {
    onEach { actor ->
        when (actor) {
            is Widget      -> actor.setFillParent(true)
            is WidgetGroup -> actor.setFillParent(true)
        }
    }
}


fun Long.transformToBalanceFormat(): String {
    val balance = toString().toMutableList()

    when (length) {
        4    -> balance.add(1, ' ')
        5    -> balance.add(2, ' ')
        6    -> balance.add(3, ' ')
        7    -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
        }
        8    -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
        }
        9    -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
        }
        10   -> {
            balance.add(1, ' ')
            balance.add(5, ' ')
            balance.add(9, ' ')
        }
        11   -> {
            balance.add(2, ' ')
            balance.add(6, ' ')
            balance.add(10, ' ')
        }
        12   -> {
            balance.add(3, ' ')
            balance.add(7, ' ')
            balance.add(11, ' ')
        }
        else -> toString()
    }

    return balance.joinToString("")
}

enum class AutospinState {
    DEFAULT, GO,
}