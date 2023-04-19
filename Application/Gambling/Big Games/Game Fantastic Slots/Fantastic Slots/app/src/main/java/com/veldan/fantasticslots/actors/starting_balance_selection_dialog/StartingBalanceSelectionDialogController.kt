package com.veldan.fantasticslots.actors.starting_balance_selection_dialog

import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.advanced.AbstractAdvancedGroup
import com.veldan.fantasticslots.utils.cancelCoroutinesAll
import com.veldan.fantasticslots.utils.controller.GroupController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class StartingBalanceSelectionDialogController(
    override val group: AbstractAdvancedGroup
) : GroupController, Disposable {

    val coroutineDataStore = CoroutineScope(Dispatchers.IO)

    val balanceList = mutableListOf<Long>(10_000L, 20_000L, 50_000L)



    override fun dispose() {
        cancelCoroutinesAll(coroutineDataStore)
    }

}