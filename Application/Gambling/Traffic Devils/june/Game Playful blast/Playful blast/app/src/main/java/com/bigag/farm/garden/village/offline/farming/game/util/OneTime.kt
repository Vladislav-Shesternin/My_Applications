package com.bigag.farm.garden.village.offline.farming.game.util

import java.util.concurrent.atomic.AtomicBoolean

class OneTime {

    private val flag = AtomicBoolean(true)

    fun use(block: () -> Unit) {
        if (flag.getAndSet(false)) block()
    }

}