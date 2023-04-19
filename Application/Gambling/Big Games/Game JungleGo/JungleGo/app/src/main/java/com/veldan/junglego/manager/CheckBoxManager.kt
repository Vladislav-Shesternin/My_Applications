package com.veldan.junglego.manager

import com.badlogic.gdx.utils.Disposable
import com.veldan.junglego.actors.CheckBox
import com.veldan.junglego.utils.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckBoxManager(
    vararg val checkBox: CheckBox
) : Disposable {

    private val coroutineDefault = CoroutineScope(Dispatchers.Default)

    private var currentCheckedCheckBox: CheckBox? = null



    init {
        manageCheckBox()
    }



    override fun dispose() {
        cancelCoroutinesAll(coroutineDefault)
    }



    private fun manageCheckBox() {
        checkBox.onEach { box ->
            box.isControlCheckBoxManager = true
            coroutineDefault.launch {
                box.isCheckedFlow.collect { isChecked -> if (isChecked) {
                        currentCheckedCheckBox?.uncheckAndEnabled()
                        currentCheckedCheckBox = box
                } }
            }
        }
    }

}