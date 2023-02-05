package heap

interface HeapContract<T> where T: Comparable<T> {
    fun insert(value: T)

    fun remove()
}