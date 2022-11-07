package binarysearchtree

data class BstNode<T>(
    val value: T,
    var left: BstNode<T>? = null,
    var right: BstNode<T>? = null
)
