package com.pharhaslo.slo7.game.manager

import com.pharhaslo.slo7.game.actors.CheckBox
import kotlinx.coroutines.*

class CheckBoxManager {
    private var currentCheckedCheckBox: CheckBox? = null



    fun manageCheckBox(checkBoxList: List<CheckBox>) {
        checkBoxList.onEach { box ->
            box.isControlCheckBoxManager = true

            CoroutineScope(Dispatchers.Default).launch {
                box.isCheckedFlow.collect { isChecked -> if (isChecked) {
                        currentCheckedCheckBox?.uncheckAndEnabled()
                        currentCheckedCheckBox = box
                } }
            }
        }

    }

}