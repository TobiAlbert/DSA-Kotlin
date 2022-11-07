package exercises

internal fun twoSum(nums: IntArray, sum: Int): Boolean {
    val complement = mutableSetOf<Int>()

    for (num in nums) {
        if (complement.contains(num)) {
            return true
        }

        complement += sum - num
    }

    return false
}