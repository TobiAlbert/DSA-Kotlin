package queues

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QueueStackTest {

    private lateinit var queue: QueueStack<Int>

    @Before
    fun setup() {
        queue = QueueStack()
    }

    @Test
    fun `calling peek on an empty queue should return null`() {
        assertNull(queue.peek())
    }

    @Test
    fun `calling peek on queue with at least one item should return the first item`() {
        val items = listOf(1, 2, 4, 6)
        items.forEach(queue::enqueue)

        val expected = 1
        val actual = queue.peek()

        assertEquals(expected, actual)
    }

    @Test
    fun `calling isEmpty on an empty queue should return true`() {
        assertTrue(queue.isEmpty())
    }

    @Test
    fun `calling isEmpty on a queue with at least one item returns false`() {
        val item = 1
        queue.enqueue(item)

        assertFalse(queue.isEmpty())
    }

    @Test
    fun `calling enqueue increases length of queue`() {
        val item = 1
        queue.enqueue(item)

        val actualLength = queue.length
        val expected = 1

        assertEquals(expected, actualLength)
    }

    @Test
    fun `can enqueue more than one item to queue`() {
        val items = listOf(1, 2, 3, 4)
        items.forEach(queue::enqueue)


        val expected = listOf(1, 2, 3, 4)
        val actual = queue.head
        assertEquals(expected, actual)
    }

    @Test
    fun `calling dequeue reduces the length of the queue`() {
        val items = listOf(1, 2, 3, 4)
        items.forEach(queue::enqueue)

        queue.dequeue()

        val expected = items.size - 1
        val actual = queue.length

        assertEquals(expected, actual)
    }

    @Test
    fun `calling dequeue removes the first element of the queue`() {
        val items = listOf(1, 2, 3, 4)
        items.forEach(queue::enqueue)

        queue.dequeue()

        val expected = listOf(2, 3, 4)
        val actual = queue.head

        assertEquals(expected, actual)
    }

}