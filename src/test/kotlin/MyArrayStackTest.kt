import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import stacks.MyArrayStack
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MyArrayStackTest {

    private lateinit var stack: MyArrayStack<Int>

    @Before
    fun setup() {
        stack = MyArrayStack()
    }

    @Test
    fun `stack is initialized with zero length`() {
        val expectedSize = 0

        assertEquals(expectedSize, stack.length)
    }

    @Test
    fun `stack is empty when initialized`() {
        assertTrue(stack.isEmpty())
    }

    @Test
    fun `stack length is increased when items are added`() {
        val items = listOf(1, 2, 3, 4, 5)

        items.forEach(stack::push)

        val expectedLength = items.size

        assertEquals(expectedLength, stack.length)
    }

    @Test
    fun `stack length is reduced when items are removed`() {
        listOf(1, 2, 3, 4, 5, 6).forEach(stack::push)

        stack.pop()
        assertEquals(5, stack.length)

        stack.pop()
        assertEquals(4, stack.length)

        stack.pop()
        assertEquals(3, stack.length)

        stack.pop()
        assertEquals(2, stack.length)

        stack.pop()
        assertEquals(1, stack.length)

        stack.pop()
        assertEquals(0, stack.length)
    }

    @Test
    fun `peek item is null when array is initialized`() {
        assertNull(stack.peek())
    }

    @Test
    fun `peek item is null when array is empty`() {
        val items = listOf(1, 2, 3, 4, 5, 6)

        items.forEach(stack::push)

        for (index in items.size - 1 downTo 0) {
            stack.pop()
        }

        assertNull(stack.peek())
    }

    @Test
    fun `peek item is last item added to the stack`() {
        val items = listOf(1, 2, 3, 4, 5, 6)

        items.forEach(stack::push)

        val expectedItem = items.last()

        assertEquals(expectedItem, stack.peek())
    }

    @Test
    fun `popping initialized stack throws Exception`() {
        assertThrows<Exception> { stack.pop() }
    }

    @Test
    fun `popping empty stack throws Exception`() {
        stack.push(1)
        stack.pop()

        assertThrows<Exception> { stack.pop() }
    }

    @Test
    fun `popping should return the item popped`() {
        val items = listOf(1, 2, 3)
        items.forEach(stack::push)

        val expectedItem = items.last()
        val actualItem = stack.pop()

        assertEquals(expectedItem, actualItem)
    }
}