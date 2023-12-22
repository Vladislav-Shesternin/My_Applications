package iconic.duo.hunt.game.utils

import com.badlogic.gdx.graphics.g2d.TextureRegion
import iconic.duo.hunt.game.manager.SpriteManager

class Deck {

    private val randomStartIndex = (0..7).shuffled().first()

    private val _1 = List(13) { Card(it, SpriteManager.ListRegion._1.regionList[it]) }.slice(randomStartIndex..randomStartIndex + 5)
    private val _2 = List(13) { Card(it, SpriteManager.ListRegion._2.regionList[it]) }.slice(randomStartIndex..randomStartIndex + 5)
    private val _3 = List(13) { Card(it, SpriteManager.ListRegion._3.regionList[it]) }.slice(randomStartIndex..randomStartIndex + 5)
    private val _4 = List(13) { Card(it, SpriteManager.ListRegion._4.regionList[it]) }.slice(randomStartIndex..randomStartIndex + 5)

    val cards = _1 + _2 + _3 + _4


    data class Card(
        val id: Int,
        val region: TextureRegion
    )

}