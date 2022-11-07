package binarysearchtree

import org.junit.Before
import org.junit.Test
import kotlin.test.*

class BinarySearchTreeTest {
    private lateinit var bst: BinarySearchTree<Int>

    @Before
    fun setup() {
        bst = BinarySearchTree()
    }

    @Test
    fun `can be initialised with null root`() {
        assertNull(bst.root)
    }

    @Test
    fun `can be initialised with value`() {
        val root = 8
        val bst = BinarySearchTree(root)

        val expected = BstNode(value = 8)

        assertEquals(expected, bst.root)
    }

    @Test
    fun `can update null root node when insert`() {
        val value = 2
        bst.insert(value)

        val expected = BstNode(value = value)

        assertEquals(expected, bst.root)
    }

    @Test
    fun `can insert value to left of root node`() {
        val rootValue = 5
        bst.insert(rootValue)

        val leftValue = 1
        bst.insert(leftValue)

        val expected = BstNode(value = leftValue)
        val actualLeft = bst.root?.left
        val actualRight = bst.root?.right

        assertEquals(expected, actualLeft)
        assertNull(actualRight)
    }

    @Test
    fun `can insert value to the right of root node`() {
        val rootValue = 6
        bst.insert(rootValue)

        val rightValue = 8
        bst.insert(rightValue)

        val expected = BstNode(value = rightValue)
        val actualRight = bst.root?.right
        val actualLeft = bst.root?.left

        assertEquals(expected, actualRight)
        assertNull(actualLeft)
    }

    @Test
    fun `can insert into children of root node`() {
        listOf(
            9, // root value
            4, // root left
            20, // root right
            1, // left of left child
            6, // right of left child
            15, // left of right child
            170 // right of right child
        ).forEach(bst::insert)

        val llc = BstNode(value = 1)
        val rlc = BstNode(value = 6)
        val lrc = BstNode(value = 15)
        val rrc = BstNode(value = 170)

        val root = bst.root
        val leftChild = root?.left
        val rightChild = root?.right

        assertEquals(llc, leftChild?.left)
        assertEquals(rlc, leftChild?.right)
        assertEquals(lrc, rightChild?.left)
        assertEquals(rrc, rightChild?.right)
    }

    @Test
    fun `returns node if value is in tree`() {
        val value = 5
        bst.insert(value)

        assertNotNull(bst.lookup(value))
    }

    @Test
    fun `returns null if value is not in tree`() {
        val value = 5
        assertNull(bst.lookup(value))
    }

    @Test
    fun `returns node if value is in child nodes`() {
        val value = 170
//              9
//          4       20
//        1   6   16  170

        listOf(
            9, // root value
            4, // root left
            20, // root right
            1, // left of left child
            6, // right of left child
            15, // left of right child
            170 // right of right child
        ).forEach(bst::insert)

        assertNotNull(bst.lookup(value))
    }

    @Test
    fun `returns null if value is not in child nodes`() {
//              9
//          4       20
//        1   6   16  170

        listOf(
            9, // root value
            4, // root left
            20, // root right
            1, // left of left child
            6, // right of left child
            15, // left of right child
            170 // right of right child
        ).forEach(bst::insert)

        val lookupValue = 171
        assertNull(bst.lookup(lookupValue))
    }

    @Test
    fun `can delete root node that is also a leaf`() {
        val root = 4

        bst.insert(root)

        bst.delete(root)

        assertNull(bst.lookup(root))
    }

    @Test
    fun `can delete from root with just one child`() {
        val root = 4
        val childValue = 7

        bst.insert(root)
        bst.insert(childValue)

        bst.delete(root)

        assertNull(bst.lookup(root))

        val expected = BstNode(value = childValue)
        assertEquals(expected, bst.lookup(childValue))
        assertEquals(expected, bst.root)
    }

    @Test
    fun `can delete root with two children`() {
        val root = 50
        listOf(
            root,
            30,
            70,
            20,
            40,
            60,
            80
        ).forEach(bst::insert)

        bst.delete(root)

        val expected =
            BstNode(
                value = 70,
                left = BstNode(
                    value = 60,
                    left = BstNode(
                        value = 30,
                        left = BstNode(
                          value = 20
                        ),
                        right = BstNode(
                            value = 40
                        )
                    )
                ),
                right = BstNode(value = 80)
            )

        assertEquals(expected, bst.root)

    }

    @Test
    fun `can delete from leaf node`() {
        val leafNode = 60
        //      50
        //  30      70
        // 20  40 60  80
        listOf(
            50,
            30,
            70,
            20,
            40,
            leafNode,
            80
        ).forEach(bst::insert)

        bst.delete(leafNode)

        assertNull(bst.lookup(leafNode))
    }

    @Test
    fun `can delete node that has only one child`() {
        val value = 30
        val replacementValue = 40
        listOf(
            50,
            value,
            70,
            replacementValue,
            60,
            80
        ).forEach(bst::insert)

        bst.delete(value)

        assertNull(bst.lookup(value))

        val expected = BstNode(value = replacementValue)

        assertEquals(expected, bst.lookup(replacementValue))
    }


}