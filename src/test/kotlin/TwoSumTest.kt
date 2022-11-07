import exercises.twoSum
import org.junit.Assert.*
import org.junit.Test

internal class TwoSumTest {

    @Test internal fun `two sum should return true when nums contains two values`() {
        val nums = intArrayOf(6, 4, 3, 2, 1, 7)
        val target = 9

        val containsTwoNums = twoSum(nums, target)

        assertTrue(containsTwoNums)
    }

    @Test internal fun `two sum should return false when nums does not contain two values`() {
        val nums = intArrayOf(6, 4, 3, 2, 1)
        val target = 100

        val containsTwoNums = twoSum(nums, target)

        assertFalse(containsTwoNums)
    }
}