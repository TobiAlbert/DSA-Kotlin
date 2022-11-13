package queues

interface QueueContract <E> {
    fun peek(): E?

    fun enqueue(element: E)

    fun dequeue()
}