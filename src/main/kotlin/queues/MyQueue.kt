package queues

import common.Node

class MyQueue<E>: QueueContract<E> {

    var first: Node<E>? = null
        private set

    var last: Node<E>? = null
        private set

    var length = 0
        private set

    override fun peek(): E? = first?.value

    override fun dequeue() {
        TODO("Not yet implemented")
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