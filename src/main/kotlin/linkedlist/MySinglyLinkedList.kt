package linkedlist

import common.Node

class MySinglyLinkedList<T> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    val headValue: T?
        get() = head?.value

    val tailValue: T?
        get() = tail?.value

    var length = 0
        private set

    fun append(item: T) {
        val node = Node(value = item)
        if (head == null || tail == null) {
            head = node
            tail = head
        } else {
            tail?.next = node
            tail = node
        }

        length++
    }

    fun prepend(item: T) {
        val node = Node(item)

        if (head == null || tail == null) {
            head = node
            tail = node
        } else {
            node.next = head
            head = node
        }

        length++
    }

    fun insertAtIndex(index: Int, item: T) {
        val isValidIndex = index in 0..length

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

        val node = Node(value = item)

        var previousNode: Node<T>? = null
        var currentNode: Node<T>? = head

        var startPosition = 0

        while (currentNode != null) {
            if (index == startPosition) {
                previousNode?.next = node
                node.next = currentNode
                length++
                break
            }
            startPosition++
            previousNode = currentNode
            currentNode = currentNode.next
        }
    }

    fun unShift() {
        if (length == 0) {
            throw IllegalStateException("Cannot un-shift empty list")
        }

        if (length == 1) {
            // head and tail are pointing to the same node
            head = null
            tail = null
        } else {
            val nextNode = head?.next
            head = nextNode
        }

        length--
    }

    fun pop() {
        if (length <= 0) {
            throw IllegalStateException("Cannot pop empty list")
        }

        if (length == 1) {
            head = null
            tail = null
        } else {
            var previousNode: Node<T>? = null
            var currentNode: Node<T>? = head

            var startPosition = 0

            while (true) {
                if (startPosition + 1 == length) {
                    tail = previousNode
                    tail?.next = null
                    break
                }
                startPosition++
                previousNode = currentNode
                currentNode = currentNode?.next
            }
        }

        length--
    }

    fun removeAt(index: Int) {
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

        var previousNode: Node<T>? = null
        var currentNode: Node<T>? = head
        var startPosition = 0

        while (true) {
            if (index == startPosition) {
                tail = currentNode?.next
                previousNode?.next = tail
                break
            }
            startPosition++
            previousNode = currentNode
            currentNode = currentNode?.next
        }

        length--
    }

    fun reverse() {
        if (length < 2) {
            return
        }

        var previousNode: Node<T>? = null
        var currentNode: Node<T>? = head

        while (currentNode != null) {
            val node = Node(value = currentNode.value)
            node.next = previousNode

            if (previousNode == null) {
                tail = node
                tail?.next = null
            }

            previousNode = node
            currentNode = currentNode.next
        }

        // update head
        head = previousNode
    }

    /**
     * traverses the linked list from head -> tail
     * concatenating the toString representation of the `value` in the [Node]
     * returns [String]
     */
    override fun toString(): String {
        val builder = StringBuilder()

        var node: Node<T>? = head

        while (node != null) {
            builder.append(node.value)
            node = node.next
        }

        return builder.toString()
    }

}