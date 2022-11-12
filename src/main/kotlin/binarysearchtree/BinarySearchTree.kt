package binarysearchtree

class BinarySearchTree<T>() where T: Comparable<T> {

    var root: BstNode<T>? = null
        private set

    constructor(root: T): this() {
        this.root = BstNode(value = root)
    }

    constructor(values: List<T>): this() {
        values.forEach(::insert)
    }

    fun insert(value: T) {
        val node = BstNode(value = value)
        val currentRoot = root

        if (currentRoot == null) {
            root = node
            return
        }

        insertNodeInTree(currentRoot, node)
    }

    private fun insertNodeInTree(currentNode: BstNode<T>, node: BstNode<T>) {
        val value = node.value
        val nodeValue = currentNode.value

        if (value <= nodeValue) {
            // insert at the left
            val leftNode = currentNode.left
            if (leftNode != null) {
                insertNodeInTree(leftNode, node)
            } else {
                currentNode.left = node
            }
            return
        }

        val rightNode = currentNode.right
        if (rightNode != null) {
            insertNodeInTree(rightNode, node)
        } else {
            currentNode.right = node
        }
    }

    fun lookup(value: T): BstNode<T>? {
        val tempRoot = root ?: return null
        return isValueInNode(tempRoot, value)
    }

    private fun isValueInNode(node: BstNode<T>, value: T): BstNode<T>? {
        val nodeValue = node.value

        if (nodeValue == value) {
            return node
        }

        if (value < nodeValue) {
            val leftNode = node.left ?: return null

            return isValueInNode(leftNode, value)
        }

        val rightNode = node.right ?: return null
        return isValueInNode(rightNode, value)
    }

    fun delete(value: T) {
        var currentNode: BstNode<T>? = root ?: return
        var previousNode: BstNode<T>? = null

        while(currentNode != null) {
            val nodeValue = currentNode.value

            if (value == nodeValue) {
                // if previousNode is null then we are on the root node
                if (previousNode == null) {
                    handleRootDeletion(currentNode)
                    break
                }
                deleteNode(currentNode, previousNode)
                break
            }

            previousNode = currentNode
            currentNode = if (value < nodeValue) currentNode.left else currentNode.right
        }
    }

    private fun handleRootDeletion(currentNode: BstNode<T>) {
        // if node has no children
        val leftChild = currentNode.left
        val rightChild = currentNode.right

        val rootNodeHasNoChildren = leftChild == null && rightChild == null
        if (rootNodeHasNoChildren) {
            root = null
            return
        }

        // has just one child
        val nodeHasJustOneChild = (leftChild != null && rightChild == null) ||
                (leftChild == null && rightChild != null)

        if (nodeHasJustOneChild) {
            // make child node the new root node
            if (leftChild != null) {
                root = leftChild
                return
            }

            root = rightChild
            return
        }

        // at this stage node has two children
        // point the left child to the smallest
        // value in the right child (the left-most node of the right child)
        root = rightChild
        insertNodeInTree(root!!, leftChild!!)
    }

    private fun deleteNode(currentNode: BstNode<T>, previousNode: BstNode<T>) {
        // first scenario: node to be deleted is a leaf
        val leftChild = currentNode.left
        val rightChild = currentNode.right
        val nodeHasNoChildren = leftChild == null && rightChild == null

        if (nodeHasNoChildren) {
            deleteLeafNode(currentNode, previousNode)
            return
        }

        // second scenario: node to be deleted has exactly one child
        val nodeHasJustOneChild = (leftChild != null && rightChild == null) ||
                (leftChild == null && rightChild != null)

        if (nodeHasJustOneChild) {
            deleteNodeWithOneChild(currentNode, previousNode)
            return
        }

        // at this point, it means the node has two children
        val successorNode = getInOrderSuccessorForNonLeafNode(currentNode) ?: return

        // copy the value of the successor node into the current node
        currentNode.value = successorNode.value
    }

    private fun getInOrderSuccessorForNonLeafNode(node: BstNode<T>): BstNode<T>? {
        // return null for leaf node
        if (node.left == null && node.right == null) {
            return null
        }

        // to get the successor node (which is the smallest value that is greater than the value to be removed)
        // take one right and multiple left turns
        val rightChild = node.right ?: return null

        var previousNode: BstNode<T>? = null
        var currentNode: BstNode<T>? = rightChild

        var value: T? = null

        while(currentNode != null) {
            val leftChild = currentNode.left

            if (leftChild == null) {
                // get the value of the current node
                value = currentNode.value
                // delete the successor node from its parent
                previousNode?.left = null
            }

            previousNode = currentNode
            currentNode = currentNode.left
        }

        return if (value == null) return null else BstNode(value = value)
    }

    private fun deleteLeafNode(leafNode: BstNode<T>, parentNode: BstNode<T>) {
        if (parentNode.left == leafNode) {
            parentNode.left = null
        }

        if (parentNode.right == leafNode) {
            parentNode.right = null
        }
    }

    private fun deleteNodeWithOneChild(currentNode: BstNode<T>, parentNode: BstNode<T>) {
        val leftChild = currentNode.left
        if (leftChild != null) {
            if (parentNode.left == currentNode) {
                parentNode.left = leftChild
                return
            }

            if (parentNode.right == currentNode) {
                parentNode.right = leftChild
                return
            }

            return
        }

        val rightChild = currentNode.right
        if (rightChild != null) {
            if (parentNode.left == currentNode) {
                parentNode.left = rightChild
                return
            }

            if (parentNode.right == currentNode) {
                parentNode.right = rightChild
                return
            }
        }
    }
}