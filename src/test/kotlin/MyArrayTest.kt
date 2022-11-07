import common.MyArray
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.junit.function.ThrowingRunnable

class MyArrayTest {

    private lateinit var array: MyArray<Any>

    @Before
    fun setup() {
        array = MyArray()
    }

    @Test
    fun `initialized array with no parameters passed should have a size of 0`() {
        val expectedSize = 0

        assertEquals(expectedSize, array.size())
    }

    @Test
    fun `length of array initialized with parameters should match its size`() {
        val localArray = MyArray(1, 2, 3)

        val expectedSize = 3
        assertEquals(expectedSize, localArray.size())
    }

    @Test
    fun `length of array is increased when new elements are added`() {
        array.push(1)
        array.push(1)

        val expectedSize = 2
        assertEquals(expectedSize, array.size())
    }

    @Test
    fun `length of array decreases when elements are popped from it`() {
        val localArray = MyArray(1, 2, 3)

        localArray.pop()
        localArray.pop()

        val expectedSize = 1
        assertEquals(expectedSize, localArray.size())
    }

    @Test
    fun `array can retrieve inserted item at index`() {
        val firstItem = 1
        val secondItem = 10
        val thirdItem = 100

        array.push(firstItem)
        array.push(secondItem)
        array.push(thirdItem)

        assertEquals(firstItem, array.get(0))
        assertEquals(secondItem, array.get(1))
        assertEquals(thirdItem, array.get(2))
    }

    @Test
    fun `attempt to retrieve item at length throws index out of bounds exception`() {
        val firstItem = 1
        val secondItem = 10
        val thirdItem = 100

        array.push(firstItem)
        array.push(secondItem)
        array.push(thirdItem)

        assertThrows(IndexOutOfBoundsException::class.java) {
            array.get(array.size())
        }
    }

    @Test
    fun `attempt to retrieve item at length larger than array throws index out of bounds exception`() {
        val firstItem = 1

        array.push(firstItem)

        assertThrows(IndexOutOfBoundsException::class.java) {
            array.get(array.size() + 1)
        }
    }

    @Test
    fun `attempt to retrieve item at length less than zero returns index out of bounds exception`() {
        assertThrows(IndexOutOfBoundsException::class.java) {
            array.get(-1)
        }
    }

    @Test
    fun `delete item at index reduces size of array`() {
        listOf(1, 2, 3).forEach(array::push)

        array.delete(1)
        val expectedSize = 2

        assertEquals(expectedSize, array.size())
    }

    @Test
    fun `delete items at index removes item from array`() {
        val firstItem = 1
        val secondItem = 2
        val thirdItem = 3

        listOf(
            firstItem,
            secondItem,
            thirdItem
        ).forEach(array::push)

        array.delete(1)

        assertEquals(firstItem, array.get(0))
        assertEquals(thirdItem, array.get(1))
    }

    @Test
    fun `attempt to pop empty array throws IndexOutOfBoundsException`() {
        assertThrows(IndexOutOfBoundsException::class.java) {
            array.pop()
        }
    }

    @Test
    fun `attempt to delete from array at index greater than length of items throws an IndexOutOfBoundsException`() {
        val firstItem = 1
        array.push(firstItem)

        assertThrows(IndexOutOfBoundsException::class.java) {
            array.delete(1)
        }
    }

    @Test
    fun `deleting all items empties the array`() {
        val localArray = MyArray(1, 2, 3, 4, 5, 6)

        for (i in 0 until localArray.size()) {
            localArray.delete(0)
        }

        val expectedSize = 0
        assertEquals(expectedSize, localArray.size())
    }

    @Test
    fun `initializing array with secondary constructor increases size`() {
        val size = 5
        val localArray = MyArray(size) { -1 }

        assertEquals(size, localArray.size())
    }

    @Test
    fun `can insert item at index in array`() {
        val firstItem = 1
        val secondItem = 2
        val thirdItem = 3

        listOf(
            firstItem,
            secondItem,
            thirdItem
        ).forEach(array::push)

        val indexToInsert = 1
        val itemToInsert = 4

        array.insertAtIndex(index = indexToInsert, item = itemToInsert)

        val insertedItem = array.get(indexToInsert)
        assertEquals(itemToInsert, insertedItem)
    }

    @Test
    fun `inserting item at index greater than length of array throws exception`() {
        array.push(1)

        kAssertThrows<IndexOutOfBoundsException> {
            val insertionIndex = 1
            val item = 2
            array.insertAtIndex(insertionIndex, item)
        }
    }

    @Test
    fun `inserting an item an index less than zero throws an exception`() {
        kAssertThrows<IndexOutOfBoundsException> {
            val insertionIndex = -1
            val item = 2
            array.insertAtIndex(insertionIndex, item)
        }
    }

    private inline fun <reified T : Exception> kAssertThrows(crossinline assertionBlock: () -> Unit) {
        val throwable = ThrowingRunnable { assertionBlock() }
        assertThrows(T::class.java, throwable)
    }
}