package queues

import common.Node

class MyQueue<E>(): QueueContract<E> {

    var first: Node<E>? = null
        private set

    var last: Node<E>? = null
        private set

    var length = 0
        private set

    constructor(elements: List<E>): this() {
        elements.forEach(::enqueue)
    }

    override fun peek(): E? = first?.value

    override fun dequeue() {
        val canDequeue =
            first != null && last != null && length > 0

        if (!canDequeue) {
            return
        }

        if (length == 1) {
            first = null
            last = null
            length--
            return
        }

        // at this point, we're dealing with 2 or more elements in queue
        // update the first node to point to the next node in the queue
        first = first?.next

        length--
    }

    override fun enqueue(element: E) {
        val node = Node(value = element)

        if (first == null) {
            first = node
            last = node
            length++
            return
        }

        // point to the next node, and update the last node pointer
        last?.next = node
        last = node
        length++
    }
}