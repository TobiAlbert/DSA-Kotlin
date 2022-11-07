package stacks

import common.Node

class MyLinkedListStack<T> : StackContract<T> {

    private var top: Node<T>? = null
    private var bottom: Node<T>? = null
    var length: Int = 0
        private set

    override fun push(item: T) {
        val newNode = Node(value = item)

        if (isEmpty()) {
            top = newNode.copy()
            bottom = newNode.copy()
        } else {
            val currentTop = top
            top = newNode.copy()
            top?.next = currentTop
        }
        length++
    }

    override fun pop(): T {
        if (isEmpty()) {
            throw Exception("Stack is empty")
        }

        if (top == bottom) {
            bottom = null
        }

        top = top?.next
        length--

        return top?.value!!
    }

    override fun peek(): T? =
        if (isEmpty()) null else top?.value

    override fun isEmpty(): Boolean = length == 0

    override fun toString(): String {
        val builder = StringBuilder("")
        var node = top

        while(node != null) {
            builder.append("${node.value}")
            node = node.next
        }

        return builder.toString()
    }
}