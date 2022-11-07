import exercises.containsCommonItems
import org.junit.Assert.*
import org.junit.Test

internal class ContainCommonItemsKtTest {

    @Test internal fun `contains common items returns true when common items are present`() {
        val list1 = charArrayOf('a', 'b', 'c', 'x')
        val list2 = charArrayOf('x', 'y', 'z')

        val hasCommonItems = containsCommonItems(list1, list2)

        assertTrue(hasCommonItems)
    }

    @Test internal fun `contains common items returns false when common items are not present`() {
        val list1 = charArrayOf('a', 'b', 'c')
        val list2 = charArrayOf('x', 'y', 'z')

        val hasCommonItems = containsCommonItems(list1, list2)

        assertFalse(hasCommonItems)
    }

    @Test internal fun `contains common items returns false when array is empty`() {
        val list1 = charArrayOf()
        val list2 = charArrayOf()

        val hasCommonItems = containsCommonItems(list1, list2)

        assertFalse(hasCommonItems)
    }

    @Test internal fun `contains common items should return true if items are the same`() {
        val sameArray = charArrayOf('a', 'b', 'c')

        val hasCommonItems = containsCommonItems(sameArray, sameArray)

        assertTrue(hasCommonItems)
    }

    @Test internal fun `contains common items returns false when first array is empty`() {
        val list1 = charArrayOf()
        val list2 = charArrayOf('a', 'b', 'c')

        val hasCommonItems = containsCommonItems(list1, list2)

        assertFalse(hasCommonItems)
    }

    @Test internal fun `contains common items returns false when second array is empty`() {
        val list1 = charArrayOf('a', 'b', 'c')
        val list2 = charArrayOf()

        val hasCommonItems = containsCommonItems(list1, list2)

        assertFalse(hasCommonItems)
    }

 }