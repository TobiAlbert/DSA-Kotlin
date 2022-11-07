package linkedlist

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class MyDoublyLinkedListTest {

    private lateinit var linkedList: MyDoublyLinkedList<Int>

    @Before
    fun setup() {
        linkedList = MyDoublyLinkedList()
    }

    @Test
    fun `initialized linked list has length of zero`() {
        val expected = 0
        assertEquals(expected, linkedList.length)
    }

    @Test
    fun `initialized linked list has heads and tails as null`() {
        val expected = ""

        assertEquals(expected, linkedList.forwardToString())
        assertEquals(expected, linkedList.backwardToString())
    }

    @Test
    fun `appending to empty linked list increases length`() {
        val item = 2

        linkedList.append(item)

        val expected = 1
        assertEquals(expected, linkedList.length)
    }

    @Test
    fun `appending item to empty linked list updates head and tail`() {
        val items = listOf(1, 2, 3)

        items.forEach(linkedList::append)

        val forwardExpected = "123"
        val backwardExpected = "321"
        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `prepending item to linked list updates head and tail`() {
        val items = listOf(1, 2, 3)

        items.forEach(linkedList::prepend)

        val forwardExpected = "321"
        val backwardExpected = "123"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `prepending item to linked list updates length`() {
        val items = listOf(1, 2, 3)

        items.forEach(linkedList::prepend)

        val expected = items.size

        assertEquals(expected, linkedList.length)
    }

    @Test
    fun `inserting item at valid index increments length of list`() {

        val items = listOf(5, 6, 7)
        items.forEach(linkedList::append)

        val item = 1
        val validIndex = 1

        linkedList.insertAt(
            index = validIndex,
            item = item
        )

        val expected = listOf(*items.toTypedArray(), item).size

        assertEquals(expected, linkedList.length)
    }

    @Test
    fun `inserting item at valid index updates head and tail`() {
        val items = listOf(5, 6, 7)
        items.forEach(linkedList::append)

        val item = 1
        val validIndex = 1

        linkedList.insertAt(
            index = validIndex,
            item = item
        )

        val forwardExpected = "5167"
        val backwardExpected = "7615"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())

    }

    @Test
    fun `popping item from empty list throws exception`() {
        assertThrows<IllegalStateException> {
            linkedList.pop()
        }
    }

    @Test
    fun `popping item from non-empty list reduces length of list`() {
        val item = 1

        linkedList.append(item)

        val expectedLength = 0

        linkedList.pop()

        assertEquals(expectedLength, linkedList.length)
    }

    @Test
    fun `popping item from non-empty list updates tail`() {
        val items = listOf(5, 6, 7, 8)

        items.forEach(linkedList::append)

        linkedList.pop()


        val forwardExpected = "567"
        val backwardExpected = "765"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `un-shifting empty list throws exception`() {
        assertThrows<IllegalStateException> {
            linkedList.unShift()
        }
    }

    @Test
    fun `un-shifting non-empty list reduces length of list`() {
        val item = 4
        linkedList.prepend(item)

        val expectedLength = 0

        linkedList.unShift()

        assertEquals(expectedLength, linkedList.length)
    }

    @Test
    fun `un-shifting item from non-empty list updates head`() {
        listOf(6, 7, 8, 9).forEach(linkedList::append)

        linkedList.unShift()

        val forwardExpected = "789"
        val backwardExpected = "987"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `calling remove on empty list throws exception`() {
        assertThrows<IllegalStateException> {
            linkedList.removeAt(index = 0)
        }
    }

    @Test
    fun `removing at invalid index throws exception`() {
        listOf(1, 2, 3).forEach(linkedList::append)

        val invalidIndex = -1
        assertThrows<IndexOutOfBoundsException> {
            linkedList.removeAt(
                index = invalidIndex
            )
        }
    }

    @Test
    fun `removing item at valid index decrements length`() {
        listOf(1, 2, 3).forEach(linkedList::append)

        linkedList.removeAt(index = 0)

        val expectedLength = 2
        assertEquals(expectedLength, linkedList.length)
    }

    @Test
    fun `removing item at start of list updates head`() {
        listOf(1, 2, 3).forEach(linkedList::append)

        linkedList.removeAt(index = 0)

        val forwardExpected = "23"
        val backwardExpected = "32"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `removing item at end of list updates tail`() {
        val items = listOf(1, 2, 3)

        items.forEach(linkedList::append)

        linkedList.removeAt(index = items.lastIndex)

        val forwardExpected = "12"
        val backwardExpected = "21"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }

    @Test
    fun `removing item anywhere between start and end of list updates properly`() {
        val items = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

        items.forEach(linkedList::append)

        linkedList.removeAt(index = 4)

        val forwardExpected = "01235678"
        val backwardExpected = "87653210"

        assertEquals(forwardExpected, linkedList.forwardToString())
        assertEquals(backwardExpected, linkedList.backwardToString())
    }
}