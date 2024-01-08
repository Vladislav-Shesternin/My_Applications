package atest.btest.lbjttest.game.manager

import atest.btest.lbjttest.game.LibGDXGame
import atest.btest.lbjttest.game.screens.LoadingScreen
import atest.btest.lbjttest.game.screens.MenuScreen
import atest.btest.lbjttest.game.screens.joints.DistanceJointScreen
import atest.btest.lbjttest.game.screens.joints.FrictionJointScreen
import atest.btest.lbjttest.game.screens.joints.GearJointScreen
import atest.btest.lbjttest.game.screens.joints.MotorJointScreen
import atest.btest.lbjttest.game.screens.joints.PrismaticJointScreen
import atest.btest.lbjttest.game.screens.joints.PulleyJointScreen
import atest.btest.lbjttest.game.screens.joints.RevoluteJointScreen
import atest.btest.lbjttest.game.screens.joints.RopeJointScreen
import atest.btest.lbjttest.game.screens.joints.WeldJointScreen
import atest.btest.lbjttest.game.screens.joints.WheelJointScreen
import atest.btest.lbjttest.game.utils.advanced.AdvancedScreen
import atest.btest.lbjttest.game.utils.runGDX
import com.badlogic.gdx.Gdx

class NavigationManager(val game: LibGDXGame) {

    private val backStack = mutableListOf<String>()
    var key: Int? = null
        private set

    fun navigate(toScreenName: String, fromScreenName: String? = null, key: Int? = null) = runGDX {
        this.key = key

        game.updateScreen(getScreenByName(toScreenName))
        backStack.filter { name -> name == toScreenName }.onEach { name -> backStack.remove(name) }
        fromScreenName?.let { fromName ->
            backStack.filter { name -> name == fromName }.onEach { name -> backStack.remove(name) }
            backStack.add(fromName)
        }
    }

    fun back(key: Int? = null) = runGDX {
        this.key = key

        if (isBackStackEmpty()) exit() else game.updateScreen(getScreenByName(backStack.removeLast()))
    }


    fun exit() = runGDX { Gdx.app.exit() }


    fun isBackStackEmpty() = backStack.isEmpty()

    private fun getScreenByName(name: String): AdvancedScreen = when(name) {
        LoadingScreen::class.java.name -> LoadingScreen(game)
        MenuScreen   ::class.java.name -> MenuScreen(game)

        // Joints
        DistanceJointScreen ::class.java.name -> DistanceJointScreen(game)
        RevoluteJointScreen ::class.java.name -> RevoluteJointScreen(game)
        PrismaticJointScreen::class.java.name -> PrismaticJointScreen(game)
        WheelJointScreen    ::class.java.name -> WheelJointScreen(game)
        WeldJointScreen     ::class.java.name -> WeldJointScreen(game)
        FrictionJointScreen ::class.java.name -> FrictionJointScreen(game)
        RopeJointScreen     ::class.java.name -> RopeJointScreen(game)
        PulleyJointScreen   ::class.java.name -> PulleyJointScreen(game)
        GearJointScreen     ::class.java.name -> GearJointScreen(game)
        MotorJointScreen    ::class.java.name -> MotorJointScreen(game)

        else -> LoadingScreen(game)
    }

}