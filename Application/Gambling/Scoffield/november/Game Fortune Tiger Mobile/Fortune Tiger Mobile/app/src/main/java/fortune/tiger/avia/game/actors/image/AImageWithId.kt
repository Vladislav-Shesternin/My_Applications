package fortune.tiger.avia.game.actors.image

import fortune.tiger.avia.game.utils.advanced.AdvancedScreen

open class AImageWithId constructor(override val screen: AdvancedScreen): AImage(screen) {
    var id = -1
}