package exercises

internal fun containsCommonItems(listA: CharArray, listB: CharArray): Boolean = listA.any(listB::contains)