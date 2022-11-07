import org.junit.Before
import org.junit.Test
import stacks.MyLinkedListStack
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MyLinkedListStackTest {

    private lateinit var stack: MyLinkedListStack<Int>

    @Before
    fun setup() {
        stack = MyLinkedListStack()
    }

    @Test
    fun `length updates when item is added to stack`() {
        val items = listOf(1, 2, 3, 4, 5)

        items.forEach(stack::push)

        val expectedLength = items.size

        assertEquals(expectedLength, stack.length)
    }

    @Test
    fun `stack is empty when initialized`() {
        assertTrue(stack.isEmpty())
    }

    @Test
    fun `stack returns items in to string form`() {
        val items = listOf(1, 2, 3, 4, 5)

        items.forEach(stack::push)

        val expectedToString = items.reversed().joinToString("")

        assertEquals(expectedToString, stack.toString())
    }

    @Test
    fun `stack returns item at the top when peeked`() {
        val items = listOf(1, 2, 3, 4, 5)

        items.forEach(stack::push)

        val expectedPeek = 5
        assertEquals(expectedPeek, stack.peek())
    }
}