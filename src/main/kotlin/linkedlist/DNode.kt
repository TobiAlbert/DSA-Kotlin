package linkedlist

data class DNode<T>(
    val value: T,
    var next: DNode<T>? = null,
    var previous: DNode<T>? = null
)