package queues

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MyQueueTest {

    private lateinit var queue: MyQueue<Int>

    @Before
    fun setup() {
        queue = MyQueue()
    }

    @Test
    fun `queue initialized with no elements has first node set to null`() {
        assertNull(queue.first)
    }

    @Test
    fun `queue initialized with no elements has last node set to null`() {
        assertNull(queue.last)
    }

    @Test
    fun `queue length is incremented when element is enqueued`() {
        val element = 5
        queue.enqueue(element)

        val expectedLength = 1

        assertEquals(expectedLength, queue.length)
    }
}