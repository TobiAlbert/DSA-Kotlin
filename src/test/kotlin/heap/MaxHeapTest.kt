package heap

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MaxHeapTest {

    private lateinit var heap: MaxHeap<Int>

    @Before
    fun setup() {
        heap = MaxHeap()
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
        val element = 10
        heap.insert(element)

        val firstChildElement = 5
        val secondChildElement = 6

        heap.insert(firstChildElement)
        heap.insert(secondChildElement)

        val expected = listOf(10, 5, 6)
        val data = heap.data

        assertEquals(expected, data)
    }

    @Test
    fun `can add reorder self when element is inserted in wrong position`() {
        val elements = listOf(20, 19, 17, 13, 15, 8, 5)
        elements.forEach(heap::insert)

        val element = 16

        heap.insert(element)

        val expected = listOf(20, 19, 17, 16, 15, 8, 5, 13)
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can reorder root element when left child element is greater`() {
        val elements = listOf(20, 22)
        elements.forEach(heap::insert)

        val expected = listOf(22, 20)
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can reorder root element when right child element is greater`() {
        val elements = listOf(20, 22, 26)
        elements.forEach(heap::insert)

        val expected = listOf(26, 20, 22)
        val actual = heap.data

        assertEquals(expected, actual)
    }

    @Test
    fun `can remove root element`() {
        val element = 10
        heap.insert(element)

        heap.remove()

        val expected = listOf<Int>()
        val actual = heap.data

        assertEquals(expected, actual)
    }


    @Test
    fun `can remove root element from heap with children`() {
        val elements = listOf(15, 5, 7, 2, 3)
        elements.forEach(heap::insert)

        heap.remove()

        val expected = listOf(7, 5, 3, 2)
        val actual = heap.data

        assertEquals(expected, actual)
    }
}