package com.pharhaslo.slo7.game.actors.slot

import com.badlogic.gdx.graphics.Texture
import com.pharhaslo.slo7.game.actors.slot.Combination.*

data class GoResult(
    val fillResultList  : List<FillResult>?,
    val bonus           : Bonus?,
    val extraFactor     : Float,
)

data class SlotItem(
    val id          : Int    ,
    var price_factor: Float  ,
    val texture     : Texture,
)

data class FillResult(
    val combination: Combination,
    val slotItem   : SlotItem,
)



enum class Bonus {
    MINI_GAME,
    SUPER_GAME,
}

/** Имя:
 *  Первая цыфра - индекс слота
 *  Вторая цывра - индекс ряда
 *  Название     - направление комбинации
 *
 *  Аргументы:
 *  slotIndexList           - индексы слотов даной комбинации
 *  rowIndexList            - индексы рядов даной комбинации
 *  possibleCombinationList - возможные комбынации, комбинации которые можно применить с текущей комбинацией
 * */
enum class Combination(
     val slotIndexList: List<Int>, // 3
     val rowIndexList : List<Int>, // 3
) {
    /**HORIZONTAL*/
    _0_0_HORIZONTAL(
        slotIndexList = listOf(0, 1, 2),
        rowIndexList  = listOf(0),
    ),
    _1_0_HORIZONTAL(
        slotIndexList = listOf(1, 2, 3),
        rowIndexList  = listOf(0),
    ),
    _2_0_HORIZONTAL(
        slotIndexList = listOf(2, 3, 4),
        rowIndexList  = listOf(0),
    ),
    _0_1_HORIZONTAL(
        slotIndexList = listOf(0, 1, 2),
        rowIndexList  = listOf(1),
    ),
    _1_1_HORIZONTAL(
        slotIndexList = listOf(1, 2, 3),
        rowIndexList  = listOf(1),
    ),
    _2_1_HORIZONTAL(
        slotIndexList = listOf(2, 3, 4),
        rowIndexList  = listOf(1),
    ),
    _0_2_HORIZONTAL(
        slotIndexList = listOf(0, 1, 2),
        rowIndexList  = listOf(2),
    ),
    _1_2_HORIZONTAL(
        slotIndexList = listOf(1, 2, 3),
        rowIndexList  = listOf(2),
    ),
    _2_2_HORIZONTAL(
        slotIndexList = listOf(2, 3, 4),
        rowIndexList  = listOf(2),
    ),

    /**VERTICAL*/
    _0_0_VERTICAL(
        slotIndexList = listOf(0),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _1_0_VERTICAL(
        slotIndexList = listOf(1),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _2_0_VERTICAL(
        slotIndexList = listOf(2),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _3_0_VERTICAL(
        slotIndexList = listOf(3),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _4_0_VERTICAL(
        slotIndexList = listOf(4),
        rowIndexList  = listOf(0, 1, 2),
    ),

    /**DIAGONAL_UP*/
    _0_2_DIAGONAL_UP(
        slotIndexList = listOf(0, 1, 2),
        rowIndexList  = listOf(2, 1, 0),
    ),
    _1_2_DIAGONAL_UP(
        slotIndexList = listOf(1, 2, 3),
        rowIndexList  = listOf(2, 1, 0),
    ),
    _2_2_DIAGONAL_UP(
        slotIndexList = listOf(2, 3, 4),
        rowIndexList  = listOf(2, 1, 0),
    ),

    /**DIAGONAL_DOWN*/
    _0_0_DIAGONAL_DOWN(
        slotIndexList = listOf(0, 1, 2),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _1_0_DIAGONAL_DOWN(
        slotIndexList = listOf(1, 2, 3),
        rowIndexList  = listOf(0, 1, 2),
    ),
    _2_0_DIAGONAL_DOWN(
        slotIndexList = listOf(2, 3, 4),
        rowIndexList  = listOf(0, 1, 2),
    ),

}

/**
 * Списки Комбинаций - максимальное количество одновременно возможных комбинаций, можно использовать не все,
 * а смешивать и брать первых от 2 до всех комбинаций в списке
 * */
enum class CombinationGroup(
    val combinationList: List<Combination>
) {
    _1(listOf(_0_0_VERTICAL     , _1_0_VERTICAL     , _2_0_VERTICAL     , _3_0_VERTICAL  , _4_0_VERTICAL    )),
    _2(listOf(_0_0_HORIZONTAL   , _0_1_HORIZONTAL   , _0_2_HORIZONTAL   , _3_0_VERTICAL  , _4_0_VERTICAL    )),
    _3(listOf(_0_0_VERTICAL     , _1_0_VERTICAL     , _2_0_HORIZONTAL   , _2_1_HORIZONTAL, _2_2_HORIZONTAL  )),
    _4(listOf(_0_0_VERTICAL     , _1_0_HORIZONTAL   , _1_1_HORIZONTAL   , _1_2_HORIZONTAL, _4_0_VERTICAL    )),
    _5(listOf(_0_2_DIAGONAL_UP  , _1_2_DIAGONAL_UP  , _2_2_DIAGONAL_UP                                      )),
    _6(listOf(_0_0_DIAGONAL_DOWN, _1_0_DIAGONAL_DOWN, _2_0_DIAGONAL_DOWN                                    )),
    _7(listOf(_0_0_HORIZONTAL   , _1_1_HORIZONTAL   , _2_2_HORIZONTAL                                       )),
    _8(listOf(_2_0_HORIZONTAL   , _1_1_HORIZONTAL   , _0_2_HORIZONTAL                                       )),
    _9(listOf(_0_0_VERTICAL     , _1_0_DIAGONAL_DOWN, _4_0_VERTICAL                                         )),
    _10(listOf(_0_0_VERTICAL    , _1_2_DIAGONAL_UP  , _4_0_VERTICAL                                         )),
    _11(listOf(_0_0_HORIZONTAL  , _0_1_HORIZONTAL   , _2_2_DIAGONAL_UP                                      )),
    _12(listOf(_0_1_HORIZONTAL  , _0_2_HORIZONTAL   , _2_0_DIAGONAL_DOWN                                    )),
    _13(listOf(_2_0_HORIZONTAL  , _2_1_HORIZONTAL   , _0_0_DIAGONAL_DOWN                                    )),
    _14(listOf(_2_1_HORIZONTAL  , _2_2_HORIZONTAL   , _0_2_DIAGONAL_UP                                      )),
    _15(listOf(_0_0_VERTICAL    , _1_0_DIAGONAL_DOWN, _2_2_DIAGONAL_UP                                      )),
    _16(listOf(_4_0_VERTICAL    , _0_0_DIAGONAL_DOWN, _1_2_DIAGONAL_UP                                      )),
}



sealed class FillStrategy {
    data class MIX(val isUseSlotItemAll: Boolean = true, val isUseSlotItemBonus: Boolean = true) : FillStrategy()
    data class WIN(val isUseSlotItemAll: Boolean = false) : FillStrategy()

    object MINI : FillStrategy()
    object SUPER: FillStrategy()
}