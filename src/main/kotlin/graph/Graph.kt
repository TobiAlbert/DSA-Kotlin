package graph

class Graph<T> where T : Comparable<T> {

    var numberOfNodes: Int = 0
        private set

    var adjacentList = mutableMapOf<T, MutableList<T>>()
        private set

    fun addNode(node: T) {
        if (adjacentList.contains(node)) {
            return
        }

        adjacentList[node] = mutableListOf()
        numberOfNodes++
    }

    fun addEdge(node1: T, node2: T) {
        adjacentList[node1]?.let { connections: MutableList<T> ->
            if (connections.contains(node2)) {
                return@let
            }
            connections.add(node2)
        }

        adjacentList[node2]?.let { connections: MutableList<T> ->
            if (connections.contains(node1)) {
                return@let
            }
            connections.add(node1)
        }
    }

}