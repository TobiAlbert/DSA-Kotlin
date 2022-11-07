package hashtables

import common.MyArray

internal class MyHashTable<V>(
    private val defaultValue: V,
    private val size: Int = 5,
): HashTableContract<String, V> {

    private data class HashBucket<V>(
        val key: String,
        val value: V
    )

    val dataSize: Int
        get() = data.size()

    private val data: MyArray<MyArray<HashBucket<V>>>

    private val keys: MyArray<String>

    init {
        data = MyArray(size)  { MyArray(HashBucket(key = "$it", value = defaultValue)) }

        val emptyStrings: List<String> = (0 until size).map { "" }
        keys = MyArray(*emptyStrings.toTypedArray())
    }

    override fun hash(key: String): Int {
        var hash = 0
        for (index in key.indices) {
            hash = (hash + key[index].code * index) % size
        }
        return hash
    }

    override fun get(key: String): V? {
        val hash = hash(key)

        return try {
            val bucket = data.get(hash)
            for (index in 0 until bucket.size()) {
                val (bucketKey: String, value: V) = bucket.get(index)
                if (bucketKey == key) {
                    return value
                }
            }
            null
        } catch (e: IndexOutOfBoundsException) {
            null
        }
    }

    override fun set(key: String, value: V) {
        val hash = hash(key)
        val bucket = HashBucket(key = key, value = value)

        // by default the data object is initialized with a common.MyArray of HashBuckets
        // so buckets would always have a size of 1 or more
        val buckets = data.get(hash)

        // replace default bucket with new bucket if default bucket
        // is present, else add new bucket
        if (buckets.size() == 1) {
            val storedBucket = buckets.get(0)
            if (storedBucket.value == defaultValue) {
                data.insertAtIndex(hash, MyArray(bucket))
                keys.insertAtIndex(hash, key)
                return
            }
        }

        buckets.push(bucket)
    }

    override fun keys(): MyArray<String> {
        val validKeys = MyArray<String>()

        for (index in 0 until keys.size()) {
            val k = keys.get(index)
            if (k.isNotEmpty()) {
                validKeys.push(k)
            }
        }

        return validKeys
    }
}