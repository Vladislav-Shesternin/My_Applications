package com.verdevad.casinavurda.game.actors.soloka

object BarabanUtil {

    data class Data(
        // 0..5
        val listRegionIndex: List<Int>,
    )

    enum class Fail(override val data: Data): IData {
        _1(Data(
            listRegionIndex = listOf(
                1, 2,
                3, 4,
                5, 1,
            )
        )),
        _2(Data(
            listRegionIndex = listOf(
                1, 2,
                5, 4,
                5, 1,
            )
        )),
        _3(Data(
            listRegionIndex = listOf(
                1, 1,
                3, 4,
                2, 1,
            )
        )),
        _4(Data(
            listRegionIndex = listOf(
                3, 2,
                3, 4,
                4, 1,
            )
        )),
        _5(Data(
            listRegionIndex = listOf(
                1, 4,
                3, 4,
                3, 1,
            )
        )),
        _6(Data(
            listRegionIndex = listOf(
                1, 5,
                1, 4,
                5, 3,
            )
        )),
    }

    //0 1
    //2 3
    //4 5
    enum class Win(override val data: Data): IData {
        _1(Data(
            listRegionIndex = listOf(
                2, 1,
                1, 1,
                1, 1,
            )
        )),
        _2(Data(
            listRegionIndex = listOf(
                1, 3,
                1, 4,
                1, 2,
            )
        )),
        _3(Data(
            listRegionIndex = listOf(
                1, 3,
                3, 4,
                5, 3,
            )
        )),
        _4(Data(
            listRegionIndex = listOf(
                1, 3,
                3, 3,
                3, 1,
            )
        )),
        _5(Data(
            listRegionIndex = listOf(
                1, 1,
                1, 1,
                1, 1,
            )
        )),
        _6(Data(
            listRegionIndex = listOf(
                1, 2,
                1, 2,
                1, 2,
            )
        )),
    }


    interface IData {
        val data: Data
    }

}