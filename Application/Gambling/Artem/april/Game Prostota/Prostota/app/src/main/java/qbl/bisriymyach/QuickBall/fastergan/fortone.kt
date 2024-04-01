package qbl.bisriymyach.QuickBall.fastergan

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class fortone(val zabuvsa: CoroutineScope) {

    val kalim = MutableStateFlow(-1L)

    init {
        zabuvsa.launch(Dispatchers.IO) {
            kalim.value = imporer.kava.get() ?: 1000L

                          kalim.collect { balance ->
                if (balance != -1L) imporer.kava.ubed { if (balance != it) balance else it }



             }
        }
    }

}