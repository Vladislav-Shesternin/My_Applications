package qbl.bisriymyach.QuickBall.hotvils

import com.badlogic.gdx.physics.box2d.Joint
import com.badlogic.gdx.physics.box2d.JointDef
import kotlinx.coroutines.CoroutineScope
import qbl.bisriymyach.QuickBall.sudams.Destroyable
import qbl.bisriymyach.QuickBall.Ebufren
import qbl.bisriymyach.QuickBall.tidams.cancelCoroutinesAll

class Dencherous<T : Joint, TD : JointDef>(val screenBox2d: Ebufren): Destroyable {

    var gloften: CoroutineScope? = null
        private set


    override fun dodo() {
        if (joint != null) {
            cancelCoroutinesAll(gloften)
            gloften = null
                                  jointDef  = null

            screenBox2d.tya9.tetriska.destroyJoint(joint)





                            joint = null
        }
    }



    var joint: T? = null
        private set
    var jointDef: TD? = null
        private set
}