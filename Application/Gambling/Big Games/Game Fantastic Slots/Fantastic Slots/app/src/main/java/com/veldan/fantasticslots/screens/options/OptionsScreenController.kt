package com.veldan.fantasticslots.screens.options

import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.utils.cancelCoroutinesAll
import com.veldan.fantasticslots.utils.controller.ScreenController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class OptionsScreenController(
    override val screen: OptionsScreen
): ScreenController, Disposable {

    val coroutineDataStore = CoroutineScope(Dispatchers.IO)



    override fun dispose() {
        cancelCoroutinesAll(coroutineDataStore)
    }

}