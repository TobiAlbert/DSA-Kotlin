package linkedlist

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MySinglyLinkedListTest {

    private lateinit var linkedList: MySinglyLinkedList<Int>

    @Before
    fun setup() {
        linkedList = MySinglyLinkedList()
    }

    @Test
    fun `appending item to empty list adds item`() {
        val item = 5

        linkedList.append(item)

        val expected = "5"
        val toString = linkedList.toString()

        assertEquals(expected, toString)
    }

    @Test
    fun `appending item to non-empty list updates tail`() {
        val item = 4
        val secondItem = 6
        val thirdItem = 8

        linkedList.append(item)
        linkedList.append(secondItem)
        linkedList.append(thirdItem)

        val expected = "468"
        val toString = linkedList.toString()

        assertEquals(expected, toString)
    }

    @Test
    fun `appending item to empty list updates length`() {
        val items = listOf(1, 4, 5, 6, 7, 2, 1)

        items.forEach(linkedList::append)

        val expectedSize = items.size
        val actualSize = linkedList.length

        assertEquals(expectedSize, actualSize)
    }

    @Test
    fun `prepending an item to an empty array updates the head and tail`() {
        val item = 6

        linkedList.prepend(item)

        val expectedString = "6"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `prepending an item to a non-empty array updates the head`() {
        val item = 0
        linkedList.append(item)

        val secondItem = 6

        linkedList.prepend(secondItem)

        val expectedString = "60"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `prepending an item to list updates the length`() {
        val items = listOf(1, 2, 5, 7, 32, 12, 43, 53)

        items.forEach(linkedList::prepend)

        val expectedLength = items.size
        val actualLength = linkedList.length

        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun `inserting item at position greater than 0 for empty list throws exception`() {
        val item = 4
        val position = 1

        assertThrows<IndexOutOfBoundsException> {
            linkedList.insertAtIndex(position, item)
        }
    }

    @Test
    fun `inserting item at position 0 in empty list updates head and tail`() {
        val item = 4
        val position = 0

        linkedList.insertAtIndex(position, item)

        val expectedString = "4"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `inserting item in non-empty list at position less than zero throws exception`() {

        listOf(1, 2, 3, 4).forEach(linkedList::append)

        val item = 5
        val invalidPosition = -1

        assertThrows<IndexOutOfBoundsException> {
            linkedList.insertAtIndex(
                item = item,
                index = invalidPosition
            )
        }
    }

    @Test
    fun `inserting item in non-empty list at position equal to length of array updates head and tail`() {
        val items = listOf(4, 5, 7, 8)

        items.forEach(linkedList::append)

        val item = 0
        val position = items.size

        linkedList.insertAtIndex(
            item = item,
            index = position
        )

        val expectedString = "45780"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `inserting item in non-empty list at position greater than size of list throws exception`() {
        val items = listOf(4, 5, 7, 8)

        items.forEach(linkedList::append)

        val item = 8
        val invalidPosition = items.size + 1

        assertThrows<IndexOutOfBoundsException> {
            linkedList.insertAtIndex(
                item = item,
                index = invalidPosition
            )
        }
    }

    @Test
    fun `inserting item in non-empty list updates list`() {
        val items = listOf(4, 5, 7, 6)

        items.forEach(linkedList::append)

        val item = 9
        val validPosition = 2

        linkedList.insertAtIndex(
            index = validPosition,
            item = item
        )

        val expectedString = "45976"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `inserting item in non-empty list updates length`() {
        val items = listOf(1, 2, 4, 6, 3)
        items.forEach(linkedList::append)

        val item = 8
        val validPosition = 4

        linkedList.insertAtIndex(
            item = item,
            index = validPosition
        )

        val expectedLength = items.size + 1
        val actualLength = linkedList.length

        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun `inserting an item in a list with length one updates head and tail`() {
        val firstItem = 4
        linkedList.append(firstItem)

        val secondItem = 2

        linkedList.insertAtIndex(
            index = 0,
            item = secondItem
        )

        val expectedString = "24"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `inserting an item at index 1, in a list with length 2 updates head and tail`() {
        val items = listOf(1, 2)
        items.forEach(linkedList::append)

        val thirdItem = 3
        linkedList.insertAtIndex(
            index = 1,
            item = thirdItem
        )

        val expectedString = "132"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `inserting an item at the end of a list updates the tail`() {
        val items = listOf(1, 2)
        items.forEach(linkedList::append)

        val thirdItem = 3
        linkedList.insertAtIndex(
            index = 2,
            item = thirdItem
        )

        val expectedString = "123"
        val toString = linkedList.toString()

        assertEquals(expectedString, toString)
    }

    @Test
    fun `un-shifting empty list throws IllegalState exception`() {
        assertThrows<IllegalStateException> { linkedList.unShift() }
    }

    @Test
    fun `un-shifting non-empty list reduces length of list`() {
        val items = listOf(1, 2, 3, 4)
        items.forEach(linkedList::append)

        linkedList.unShift()
        val expectedLength = items.size - 1
        val actualLength = linkedList.length

        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun `un-shifting list of length 1 updates head and tails`() {
        val item = 1
        linkedList.append(item)

        linkedList.unShift()

        val expectedString = ""
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `un-shifting list of length 2 or more updates head`() {
        val items = listOf(1, 4, 5, 6, 7, 8)
        items.forEach(linkedList::append)

        linkedList.unShift()
        val expectedString = items.subList(1, items.size).joinToString("")
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `popping list of empty list throws illegal state exception`() {
        assertThrows<IllegalStateException> { linkedList.pop() }
    }

    @Test
    fun `popping non-empty list reduces size of list `() {
        val items = listOf(1, 2, 3, 4)
        items.forEach(linkedList::append)

        linkedList.pop()
        val expectedLength = items.size - 1
        val actualLength = linkedList.length

        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun `popping list of size 1 updates both head and tail`() {
        val item = 1
        linkedList.append(item)

        linkedList.pop()

        val expectedString = ""
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `popping list of size 2 or more updates tail`() {
        val items = listOf(1, 4, 5, 6, 7, 8, 6)
        items.forEach(linkedList::append)

        linkedList.pop()
        val expectedString = items.subList(0, items.lastIndex).joinToString("")
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `removing from empty array throws exception`() {
        assertThrows<IndexOutOfBoundsException> {
            linkedList.removeAt(index = 1)
        }
    }

    @Test
    fun `removing from invalid index throws exception`() {
        val items = listOf(1, 2, 3)
        items.forEach(linkedList::append)

        assertThrows<IndexOutOfBoundsException> {
            linkedList.removeAt(index = -1)
        }

        assertThrows<IndexOutOfBoundsException> {
            linkedList.removeAt(index = items.size)
        }
    }

    @Test
    fun `removing from valid index reduces length of list`() {
        val items = listOf(1, 2, 3)
        items.forEach(linkedList::append)

        linkedList.removeAt(index = 0)

        val expectedLength = items.size - 1
        val actualLength = linkedList.length

        assertEquals(expectedLength, actualLength)
    }

    @Test
    fun `removing from valid index of single list item updates head and tail`() {
        linkedList.append(item = 2)

        linkedList.removeAt(0)

        val expectedString = ""
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }


    @Test
    fun `removing at index equal to last index of list pops list`() {
        val items = listOf(1, 4, 6, 8)

        items.forEach(linkedList::append)

        linkedList.removeAt(index = items.lastIndex)

        val expectedString = "146"
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `removing from valid index a list larger than one updates tail`() {
        val items = listOf(1, 4, 6, 8)

        items.forEach(linkedList::append)

        linkedList.removeAt(index = 2)

        val expectedString = "148"
        val actualString = linkedList.toString()

        assertEquals(expectedString, actualString)
    }


    @Test
    fun `reverse does not update head or tail when empty`() {
        val expected = ""

        linkedList.reverse()

        assertEquals(expected, linkedList.toString())
    }

    @Test
    fun `reverse does not update head or tail when size is one`() {
        val item = 1
        linkedList.append(item)

        val expected = "1"

        linkedList.reverse()

        assertEquals(expected, linkedList.toString())
    }

    @Test
    fun `reverse updates head and tail`() {
        val items = listOf(1, 2, 3, 4, 5, 6)

        items.forEach(linkedList::append)

        linkedList.reverse()

        val expected = "654321"
        assertEquals(expected, linkedList.toString())

        assertEquals(items.first(), linkedList.tailValue)
        assertEquals(items.last(), linkedList.headValue)
    }

}