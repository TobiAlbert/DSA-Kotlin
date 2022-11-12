package queues

import org.junit.Assert.*
import org.junit.Test

class MyQueueTest {

    @Test
    fun `queue initialized with no elements has first node set to null`() {
        val queue = MyQueue<Int>()

        assertNull(queue.first)
    }

    @Test
    fun `queue initialized with no elements has last node set to null`() {
        val queue = MyQueue<Int>()

        assertNull(queue.last)
    }
}