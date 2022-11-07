package stacks

import common.MyArray

class MyArrayStack<T>(vararg items: T): StackContract<T> {

    private val array = MyArray<T>(*items)

    val length: Int
        get() = array.size()

    override fun push(item: T) = array.push(item)

    @Throws(IndexOutOfBoundsException::class)
    override fun pop() = array.pop()

    override fun peek(): T? =
        if (isEmpty()) null else array.get(length - 1)

    override fun isEmpty(): Boolean = length == 0
}