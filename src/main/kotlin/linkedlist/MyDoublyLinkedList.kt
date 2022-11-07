package linkedlist

import kotlin.IndexOutOfBoundsException

class MyDoublyLinkedList<T> {

    private var head: DNode<T>? = null
    private var tail: DNode<T>? = null

    var length: Int = 0
        private set

    fun append(item: T) {
        val node = DNode(value = item)

        if (length == 0) {
            head = node
            tail = node
        } else {
            tail?.next = node
            node.previous = tail
            tail = node
        }

        length++
    }

    fun prepend(item: T) {
        val node = DNode(value = item)

        if (length == 0) {
            head = node
            tail = node
        } else {
            head?.previous = node
            node.next = head
            head = node
        }

        length++
    }

    fun insertAt(index: Int, item: T) {
        val isValidIndex = index in 0 ..length

        if (!isValidIndex) {
            throw IndexOutOfBoundsException()
        }

        if (index == 0) {
            prepend(item)
            return
        }

        if (index == length) {
            append(item)
            return
        }

        val node = DNode(value = item)

        var previousNode: DNode<T>? = null
        var currentNode: DNode<T>? = head

        var startPosition = 0

        while (currentNode != null) {
            if (index == startPosition) {
                previousNode?.next = node
                node.previous = previousNode

                node.next = currentNode
                currentNode.previous = node
                length++
                break
            }
            startPosition++
            previousNode = currentNode
            currentNode = currentNode.next
        }
    }

    fun forwardToString(): String {
        val builder = StringBuilder()

        var node: DNode<T>? = head

        while (node != null) {
            builder.append(node.value)
            node = node.next
        }

        return builder.toString()
    }

    fun backwardToString(): String {
        val builder = StringBuilder()

        var node: DNode<T>? = tail

        while(node != null) {
            builder.append(node.value)
            node = node.previous
        }

        return builder.toString()
    }

    fun pop() {
        if (length == 0) {
            throw IllegalStateException("cannot call pop on empty list")
        }

        if (length == 1) {
            tail = head
        } else {
            val node = tail?.previous
            node?.next = null

            tail = node
        }

        length--
    }

    fun unShift() {
        if (length == 0) {
            throw IllegalStateException("cannot call `unShift` on empty list.")
        }

        if (length == 1) {
            tail?.previous = null
            head = tail
        } else {
            val nextNode = head?.next
            nextNode?.previous = null
            head = nextNode
        }

        length--
    }

    fun removeAt(index: Int) {
        if (length == 0) {
            throw IllegalStateException("cannot call 'removeAt' when list is empty.")
        }

        val isValidIndex = index in 0 until length

        if (!isValidIndex) {
            throw IndexOutOfBoundsException()
        }

        if (index == 0) {
            unShift()
            return
        }

        if (index == length - 1) {
            pop()
            return
        }

        var previousNode: DNode<T>? = null
        var currentNode: DNode<T>? = head
        var startPosition = 0

        while(currentNode != null) {
            if (index == startPosition) {
                val nextNode: DNode<T>? = currentNode.next

                previousNode?.next = nextNode
                nextNode?.previous = previousNode

                length--
                break
            }

            startPosition++
            previousNode = currentNode
            currentNode = currentNode.next
        }
    }
}