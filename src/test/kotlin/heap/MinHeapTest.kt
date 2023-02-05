package heap

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MinHeapTest {

    private lateinit var heap: MinHeap<Int>

    @Before
    fun setup() {
        heap = MinHeap()
    }

    @Test
    fun `can initialize with empty list`() {
        val expected = listOf<Int>()
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can initialize heap with values`() {
        val localHeap = MinHeap(4, 5, 8, 10, 6, 16)

        val expected = listOf(4, 5, 8, 10, 6, 16)
        val actual = localHeap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can add root element to heap`() {
        val element = 1
        heap.insert(element)

        val expected = 1
        val size = heap.data.size

        assertEquals(expected, size)
    }

    @Test
    fun `can add two child elements to root element`() {
        val element = 4
        heap.insert(element)

        val firstChildElement = 6
        val secondChildElement = 8

        heap.insert(firstChildElement)
        heap.insert(secondChildElement)

        val expected = listOf(4, 6, 8)
        val data = heap.data

        assertEquals(expected, data)
    }

    @Test
    fun `can add reorder self when element is inserted in wrong position`() {
        val elements = listOf(4, 5, 8, 10, 6, 16)
        elements.forEach(heap::insert)

        val element = 3

        heap.insert(element)

        val expected = listOf(3, 5, 4, 10, 6, 16, 8)
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can remove root element from the heap`() {
        val element = 1
        heap.insert(element)

        heap.remove()

        val expected = listOf<Int>()
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can remove element from heap`() {
        val elements = listOf(1, 3, 4, 5, 6, 16, 8, 10)
        elements.forEach(heap::insert)

        heap.remove()

        val expected = listOf(3, 5, 4, 10, 6, 16, 8)
        val actual = heap.data

        assertEquals(expected, actual)
    }

}