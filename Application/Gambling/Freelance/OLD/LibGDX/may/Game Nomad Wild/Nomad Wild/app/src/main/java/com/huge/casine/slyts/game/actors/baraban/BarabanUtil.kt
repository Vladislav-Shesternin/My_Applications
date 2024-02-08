package com.huge.casine.slyts.game.actors.baraban

import com.badlogic.gdx.graphics.g2d.TextureRegion

object BarabanUtil {

    data class Data(
        // 0..5
        val listRegionIndex: List<Int>,
    )

    enum class Fail(override val data: Data): IData {
        _1(Data(
            listRegionIndex = listOf(
                0, 1, 2,
                3, 4, 5,
                0, 1, 2,
            )
        )),
        _2(Data(
            listRegionIndex = listOf(
                0, 1, 2,
                3, 3, 0,
                0, 1, 2,
            )
        )),
        _3(Data(
            listRegionIndex = listOf(
                0, 1, 2,
                3, 4, 0,
                1, 5, 2,
            )
        )),
        _4(Data(
            listRegionIndex = listOf(
                4, 1, 2,
                3, 3, 4,
                1, 1, 2,
            )
        )),
        _5(Data(
            listRegionIndex = listOf(
                5, 1, 5,
                3, 3, 5,
                0, 1, 2,
            )
        )),
        _6(Data(
            listRegionIndex = listOf(
                0, 1, 3,
                3, 5, 2,
                0, 0, 4,
            )
        )),
    }

    enum class Win(override val data: Data): IData {
        _1(Data(
            listRegionIndex = listOf(
                0, 0, 0,
                3, 4, 5,
                0, 1, 2,
            )
        )),
        _2(Data(
            listRegionIndex = listOf(
                0, 1, 2,
                3, 0, 0,
                0, 1, 2,
            )
        )),
        _3(Data(
            listRegionIndex = listOf(
                0, 1, 1,
                1, 0, 0,
                1, 1, 0,
            )
        )),
        _4(Data(
            listRegionIndex = listOf(
                1, 1, 1,
                3, 3, 3,
                1, 1, 1,
            )
        )),
        _5(Data(
            listRegionIndex = listOf(
                1, 1, 1,
                1, 3, 1,
                1, 1, 1,
            )
        )),
        _6(Data(
            listRegionIndex = listOf(
                2, 0, 1,
                2, 0, 1,
                2, 0, 1,
            )
        )),
    }


    interface IData {
        val data: Data
    }

}