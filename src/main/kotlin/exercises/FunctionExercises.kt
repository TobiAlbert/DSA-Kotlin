package exercises

fun reverse(str: String): String {
    if (str.isEmpty() || str.isBlank()) return str

    val builder = StringBuilder()

    for (index in str.length - 1 downTo 0) {
        builder.append(str[index])
    }

    return builder.toString()
}

fun mergeSortedArrays(firstArray: IntArray, secondArray: IntArray): IntArray {

    var firstPointer = 0
    var secondPointer = 0
    var resultPointer = 0

    val totalSize = firstArray.size + secondArray.size

    val resultArray = IntArray(totalSize)

    while (firstPointer < firstArray.size && secondPointer < secondArray.size) {
        val firstValue = firstArray[firstPointer]
        val secondValue = secondArray[secondPointer]

        if (firstValue <= secondValue) {
            resultArray[resultPointer] = firstValue
            firstPointer++
        } else {
            resultArray[resultPointer] = secondValue
            secondPointer++
        }

        resultPointer++
    }

    while(firstPointer < firstArray.size) {
        resultArray[resultPointer] = firstArray[firstPointer]
        firstPointer++
        resultPointer++
    }

    while (secondPointer < secondArray.size) {
        resultArray[secondPointer] = secondArray[secondPointer]
        secondPointer++
        resultPointer++
    }

    return resultArray
}

fun firstRecurringCharacter(numbers: IntArray): Int? {
    val set = mutableSetOf<Int>()

    for (number in numbers) {
        if (set.contains(number)) {
            return number
        }
        set.add(number)
    }

    return null
}

fun memoizedClosure(): (Int) -> Int {
    val cache = mutableMapOf<Int, Int>()

    return fun (number: Int): Int {
        if (cache.contains(number)) {
            return cache[number]!!
        }

        val sum = number + 80
        cache[number] = sum
        return sum
    }
}