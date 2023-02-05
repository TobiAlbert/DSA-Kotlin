package heap

import kotlin.math.floor

class MinHeap<T: Comparable<T>> constructor(vararg elements: T): HeapContract<T> {

    private val list: MutableList<T> = mutableListOf()
    val data: List<T>
        get() {
            if (list.isEmpty()) {
                return emptyList()
            }
            return list.subList(1, list.size)
        }

    override fun insert(value: T) {
        if (list.isEmpty()) {
            list.add(value)
            list.add(value)
            return
        }

        list.add(value)
        reorderFromBackIfNecessary()
    }

    private fun reorderFromBackIfNecessary() {
        // starting from the last element (which is the newly inserted element)
        // make sure it is in the proper position relative to it's parent
        var childIndex = list.lastIndex
        while (childIndex > 1) {
            val childElement = list[childIndex]

            val parentIndex = floor((childIndex / 2).toDouble()).toInt()
            val parentElement = list[parentIndex]

            if (childElement < parentElement) {
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
        // first thing is to remove the element at the first index
        // move the element at the last node to the first node
        // sort
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
        // 1, 10, 3, 4, 5, 6, 16, 8
        var parentIndex = 1
        while (parentIndex.rightChildIndex <= list.lastIndex) {
            val parentElement = list[parentIndex]

            val leftChildIndex = parentIndex.leftChildIndex
            val leftChildElement = list[leftChildIndex]

            val rightChildIndex = parentIndex.rightChildIndex
            val rightChildElement = list[rightChildIndex]

            if (leftChildElement < rightChildElement) {
                if (parentElement > leftChildElement) {
                    list[parentIndex] = leftChildElement
                    list[leftChildIndex] = parentElement
                    parentIndex = leftChildIndex
                    continue
                }
                break
            }

            if (parentElement > rightChildElement) {
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
