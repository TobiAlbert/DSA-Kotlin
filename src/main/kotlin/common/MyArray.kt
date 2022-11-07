package common

class MyArray<T> private constructor(){

    private var length: Int = 0
    private var data: MutableMap<Int, T> = mutableMapOf()

    constructor(vararg arguments: T): this() {
        arguments.forEach(::push)
    }

    constructor(size: Int, defaultValue: (Int) -> T): this() {
        for (index in 0 until size) {
            push(defaultValue(index))
        }
    }

    fun size(): Int = length

    fun get(index: Int): T =
        data[index] ?: throw IndexOutOfBoundsException()

    fun push(item: T) {
        data[length] = item
        length++
    }

    fun insertAtIndex(index: Int, item: T) {
        val isValidIndex = index in 0 until length

        if (!isValidIndex) {
            throw IndexOutOfBoundsException()
        }

        data[index] = item
    }

    @Throws(IndexOutOfBoundsException::class)
    fun pop(): T {

        if (length <= 0) {
            throw IndexOutOfBoundsException()
        }

        val itemToPop = data[length - 1]

        data.remove(length)

        length--

        return itemToPop!!
    }

    fun delete(index: Int) {
        if (index >= length) {
            throw IndexOutOfBoundsException()
        }

        val tempData = data

        for ((key, value) in tempData) {
            if (key > index) {
                data[key - 1] = value
            }
        }

        pop()
    }
}