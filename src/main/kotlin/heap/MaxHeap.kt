package heap

import kotlin.math.floor

class MaxHeap<T : Comparable<T>> constructor(vararg elements: T) : HeapContract<T> {

    private val list: MutableList<T?> = mutableListOf()
    val data: List<T>
        get() = list.filterNotNull()

    init {
        elements.forEach(::insert)
    }

    override fun insert(value: T) {
        if (list.isEmpty()) {
            list.add(null)
            list.add(value)
            return
        }

        list.add(value)
        reorderIfNecessary()
    }

    private fun reorderIfNecessary() {
        // starting from the last element (which is the newly inserted element)
        // make sure it is in the proper position relative to it's parent
        var childIndex = list.lastIndex
        while (childIndex > 1) {
            val childElement = list[childIndex]!!

            val parentIndex = floor((childIndex / 2).toDouble()).toInt()
            val parentElement = list[parentIndex]

            if (childElement > parentElement!!) {
                list[parentIndex] = childElement
                list[childIndex] = parentElement
                childIndex = parentIndex
                continue
            }

            // break out of loop if we didn't swap
            break
        }
    }

    override fun remove() {
        if (list.isEmpty()) {
            return
        }

        val firstElement = list[1]
        val lastElement = list.last()

        val lastIndex = list.lastIndex
        list[1] = lastElement
        list[lastIndex] = firstElement

        list.removeAt(lastIndex)
        reorderFromFrontIfNecessary()
    }

    private fun reorderFromFrontIfNecessary() {
        // null, 3, 5, 7, 2
        var parentIndex = 1
        while (parentIndex.rightChildIndex <= list.lastIndex) {
            val parentElement = list[parentIndex]!!

            val leftChildIndex = parentIndex.leftChildIndex
            val leftChildElement = list[leftChildIndex]!!

            val rightChildIndex = parentIndex.rightChildIndex
            val rightChildElement = list[rightChildIndex]!!

            if (leftChildElement > rightChildElement) {
                if (parentElement < leftChildElement) {
                    list[parentIndex] = leftChildElement
                    list[leftChildIndex] = parentElement
                    parentIndex = leftChildIndex
                    continue
                }
                break
            }

            if (parentElement < rightChildElement) {
                list[parentIndex] = rightChildElement
                list[rightChildIndex] = parentElement
                parentIndex = rightChildIndex
                continue
            }
            break
        }
    }

    private val Int.leftChildIndex
        get() = this * 2

    private val Int.rightChildIndex
        get() = this * 2 + 1
}