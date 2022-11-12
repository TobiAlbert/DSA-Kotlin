package queues

import common.Node
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

    @Test
    fun `queue first node is updated when first element is added to node`() {
        val element = 5
        queue.enqueue(element)

        val expected = Node(value = element)

        assertEquals(expected, queue.first)
    }

    @Test
    fun `queue last node is updated when first element is added to queue`() {
        val element = 5
        queue.enqueue(element)

        val expected = Node(value = element)

        assertEquals(expected, queue.last)
    }
}