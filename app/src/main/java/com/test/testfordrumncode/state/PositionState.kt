package com.test.testfordrumncode.state

// Class for handle position in list of images
// I don`t want to pass full list of images to InfoFragment or make connection <FeedFragment> <--> <InfoFragment>
// So I decided to create this class for handle position in list of images
// As alternative I can use shared view model or viewPagers
interface PositionState {

    data class Position(
        val position: Int,
        val size: Int,
    )

    fun setList(list: List<String>)

    fun getId(): String

    fun nextId(): String

    fun previousId(): String
    fun getCurrentPosition(): Position
    fun setCurrentId(id: String)


    class Base : PositionState {

        private var list: List<String> = emptyList()
        private var currentPosition: Int = 0

        override fun setList(list: List<String>) {
            this.list = list
        }

        override fun getId(): String = list[currentPosition]
        override fun nextId(): String {
            if (currentPosition == list.lastIndex) return list.last()
            currentPosition++
            return list[currentPosition]
        }

        override fun previousId(): String {
            if (currentPosition == 0) return list.first()
            currentPosition--
            return list[currentPosition]
        }

        override fun getCurrentPosition(): Position = Position(currentPosition, list.size)
        override fun setCurrentId(id: String) {
            currentPosition = list.indexOf(id)
        }

    }

}