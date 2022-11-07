package hashtables

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MyHashTableTest {

    private lateinit var hashTable: MyHashTable<Int>

    @Before
    fun setup() {
        val defaultValue = -1
        hashTable = MyHashTable(defaultValue = defaultValue)
    }

    @Test
    fun `can save and retrieve value`() {
        val grapeKey = "grapes"
        val appleKey = "apples"

        val grapeValue = 10_000
        val appleValue = 5_000


        hashTable.apply {
            set(grapeKey, grapeValue)
            set(appleKey, appleValue)
        }

        val storedGrape = hashTable.get(grapeKey)
        val storedApple = hashTable.get(appleKey)

        assertEquals(grapeValue, storedGrape)
        assertEquals(appleValue, storedApple)
    }

    @Test
    fun `retrieving item that does not exist returns null`() {
        val invalidKey = "1"
        assertNull(hashTable.get(invalidKey))
    }

    @Test
    fun `initialised hash table has proper key size`() {
        val size = 10
        val localHashTable = MyHashTable<Int>(
            defaultValue = -1,
            size = size
        )

        assertEquals(size, localHashTable.keys().size())
    }

    @Test
    fun `initialised hash table has proper data size`() {
        val size = 10
        val localHashTable = MyHashTable<Int>(
            defaultValue = -1,
            size = size
        )

        assertEquals(size, localHashTable.dataSize)
    }

    @Test
    fun `can retrieve keys`() {
        val grapeKey = "grapes"
        val appleKey = "apples"

        val grapeValue = 10_000
        val appleValue = 5_000


        hashTable.apply {
            set(grapeKey, grapeValue)
            set(appleKey, appleValue)
        }

        val hashKeys = hashTable.keys()
        val keys = mutableListOf<String>()
        val expected = listOf(grapeKey, appleKey)

        for (index in 0 until hashKeys.size()) {
            keys.add(hashKeys.get(index))
        }

        assertEquals(expected, keys)
    }


}