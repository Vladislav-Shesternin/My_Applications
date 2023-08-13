package com.logic.exchangewizard.game.manager

import androidx.constraintlayout.widget.ConstraintLayout
import com.logic.exchangewizard.MainActivity
import com.logic.exchangewizard.game.GameFragment
import com.logic.exchangewizard.game.util.Disposable
import com.logic.exchangewizard.game.util.Screen
import com.logic.exchangewizard.util.cancelCoroutinesAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class NavigationManager(
    private val activity: MainActivity,
    private val stage   : ConstraintLayout,
    private val game    : GameFragment,
): Disposable {

    private val coroutine = CoroutineScope(Dispatchers.Default)

    private val backStack = mutableListOf<Screen>()
    var key: Int? = null
        private set

    private val screenFlow = MutableSharedFlow<Screen>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private var screen: Screen? = null

    init {
        coroutine.launch(Dispatchers.Main) {
            screenFlow.collect { _screen ->
                screen?.hide()
                screen = _screen
                screen?.let {
                    it.game = game
                    it.show(stage)
                }
            }
        }
    }

    override fun dispose() {
        cancelCoroutinesAll(coroutine)
    }

    fun navigate(to: Screen, from: Screen? = null, key: Int? = null) {
        this.key = key

        screenFlow.tryEmit(to)

        backStack.filter { it.name == to.name }.onEach { backStack.remove(it) }
        from?.let { f ->
            backStack.filter { it.name == f.name }.onEach { backStack.remove(it) }
            backStack.add(f)
        }
    }

    fun back() {
        this.key = key

        if (backStack.isEmpty()) activity.exit() else screenFlow.tryEmit(backStack.removeLast())
    }

}