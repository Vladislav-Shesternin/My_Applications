package com.veldan.bigwinslots777.advanced.group

import com.badlogic.gdx.utils.Disposable
import com.veldan.bigwinslots777.utils.controller.GroupController

abstract class AbstractAdvancedGroup: AdvancedGroup() {

    abstract val controller: GroupController



    override fun dispose() {
        super.dispose()
        if (controller is Disposable) (controller as Disposable).dispose()
    }

}