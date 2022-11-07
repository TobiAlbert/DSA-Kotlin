package stacks

interface StackContract<T> {
    fun push(item: T)

    fun pop(): T

    fun peek(): T?

    fun isEmpty(): Boolean
}