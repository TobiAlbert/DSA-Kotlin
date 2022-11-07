package exercises

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class FunctionExercisesTest {

    @Test
    fun `merged arrays should return proper size`() {
        val firstArray = intArrayOf(0, 2, 4, 31)
        val secondArray = intArrayOf(4, 6, 30)

        val expectedSize = firstArray.size + secondArray.size
        val mergedArrays = mergeSortedArrays(firstArray, secondArray)

        assertEquals(expectedSize, mergedArrays.size)
    }

    @Test
    fun `merged arrays should return properly sorted arrays`() {
        val firstArray = intArrayOf(0, 3, 4, 31)
        val secondArray = intArrayOf(4, 6, 30)

        val expectedArray = intArrayOf(0, 3, 4, 4, 6, 30, 31)
        val mergedArrays = mergeSortedArrays(firstArray, secondArray)

        assertTrue(expectedArray contentEquals mergedArrays)
    }

    @Test
    fun `merge arrays should return properly sorted arrays when second array is empty`() {
        val firstArray = intArrayOf(0, 3, 4, 31)
        val secondArray = intArrayOf()

        val mergedArrays = mergeSortedArrays(firstArray, secondArray)

        assertTrue(firstArray contentEquals mergedArrays)
    }

    @Test
    fun `merge arrays should return properly sorted arrays when first array is empty`() {
        val firstArray = intArrayOf()
        val secondArray = intArrayOf(0, 3, 4, 31)

        val mergedArrays = mergeSortedArrays(firstArray, secondArray)

        assertTrue(secondArray  contentEquals mergedArrays)
    }

    @Test
    fun `should return first recurring character when present`() {
        val numbers = intArrayOf(2, 5, 1, 2, 3, 5, 1, 2, 4)

        val expected = 2
        val firstRecurringNumber = firstRecurringCharacter(numbers)

        assertEquals(expected, firstRecurringNumber)
    }

    @Test
    fun `should return null when recurring character is not present`() {
        val numbers = intArrayOf(2, 3, 4, 5)

        val firstRecurringNumber = firstRecurringCharacter(numbers)

        assertNull(firstRecurringNumber)
    }
}