package queues

import common.Node

class MyQueue<E>: QueueContract<E> {

    var first: Node<E>? = null
        private set

    var last: Node<E>? = null
        private set

    var length = 0
        private set

    override fun peek(): E? {
        TODO("Not yet implemented")
    }

    override fun dequeue() {
        TODO("Not yet implemented")
    }

    override fun enqueue(element: E) {
        TODO("Not yet implemented")
    }
}