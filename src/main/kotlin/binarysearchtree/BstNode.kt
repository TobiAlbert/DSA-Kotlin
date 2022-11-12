package binarysearchtree

data class BstNode<T>(
    var value: T,
    var left: BstNode<T>? = null,
    var right: BstNode<T>? = null
)
