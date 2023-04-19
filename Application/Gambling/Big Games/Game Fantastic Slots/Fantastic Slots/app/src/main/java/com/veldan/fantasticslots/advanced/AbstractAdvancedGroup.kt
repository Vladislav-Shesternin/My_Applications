package com.veldan.fantasticslots.advanced

import com.badlogic.gdx.utils.Disposable
import com.veldan.fantasticslots.utils.controller.GroupController

abstract class AbstractAdvancedGroup: AdvancedGroup() {

    protected abstract val controller: GroupController

    override fun dispose() {
        super.dispose()
        if (controller is Disposable) (controller as Disposable).dispose()
    }

}