package hashtables

import common.MyArray

interface HashTableContract<K, V> {
    fun hash(key: K): Int

    fun set(key: K, value: V)

    fun get(key: K): V?

    fun keys(): MyArray<K>
}