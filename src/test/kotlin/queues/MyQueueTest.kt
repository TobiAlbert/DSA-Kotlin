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

    @Test
    fun `last node is updated when element is added to queue with one element`() {
        val firstElement = 5
        val secondElement = 8

        queue.enqueue(firstElement)
        queue.enqueue(secondElement)

        val expected = Node(value = secondElement)

        assertEquals(expected, queue.last)
    }

    @Test
    fun `first node is updated when element is added to queue with one element`() {
        val firstElement = 5
        val secondElement = 8

        queue.enqueue(firstElement)
        queue.enqueue(secondElement)

        val expected = Node(
            value = firstElement,
            next = Node(value = secondElement)
        )

        assertEquals(expected, queue.first)
    }

    @Test
    fun `peek returns null when queue contains no elements`() {
        assertNull(queue.peek())
    }

    @Test
    fun `peek returns value when queue is contains elements`() {
        val element = 5
        queue.enqueue(element)

        assertEquals(element, queue.peek())
    }

    @Test
    fun `dequeue decrements queue length`() {
        val elements = listOf(1, 3)
        elements.forEach(queue::enqueue)

        queue.dequeue()
        val expected = elements.size - 1

        assertEquals(expected, queue.length)
    }

    @Test
    fun `calling dequeue on queue with no elements does nothing`() {
        queue.dequeue()

        val expectedLength = 0
        assertEquals(expectedLength, queue.length)
        assertNull(queue.first)
        assertNull(queue.last)
    }

    @Test
    fun `calling dequeue on queue with one element updates first and last nodes to null`() {
        val element = 6
        queue.enqueue(element)

        queue.dequeue()

        assertNull(queue.first)
        assertNull(queue.last)
    }

    @Test
    fun `calling dequeue on queue with one element updates length to zero`() {
        val element = 6
        queue.enqueue(element)

        queue.dequeue()

        val expectedLength = 0
        assertEquals(expectedLength, queue.length)
    }

    @Test
    fun `calling dequeue on queue with more than one element updates first node`() {
        val elements = listOf(1, 5, -7, 3)
        elements.forEach(queue::enqueue)

        queue.dequeue()

        val expected = Node(
            value = 1,
            next = Node(
                value = 5,
                next = Node(value = -7)
            )
        )

        assertEquals(expected, queue.first)
    }

    @Test
    fun `calling dequeue on queue with more than one element updates last node`() {
        val elements = listOf(1, 5, -7, 3)
        elements.forEach(queue::enqueue)

        queue.dequeue()

        val expected = Node(value = -7)

        assertEquals(expected, queue.last)
    }
}