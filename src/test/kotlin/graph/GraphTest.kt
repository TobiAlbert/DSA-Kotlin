package graph

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GraphTest {

    private lateinit var graph: Graph<Int>

    @Before
    fun setup() {
        graph = Graph()
    }

    @Test
    fun `graph is initialized with length of zero`() {
        val expected = 0
        assertEquals(expected, graph.numberOfNodes)
    }

    @Test
    fun `adding node increases number of nodes in graph`() {
        val node = 1
        graph.addNode(node)

        val expected = 1
        assertEquals(expected, graph.numberOfNodes)
    }

    @Test
    fun `adding a node that is already in a graph does not increase the number of nodes in the graph`() {
        val node = 1

        graph.addNode(node)
        graph.addNode(node)

        val expected = 1
        assertEquals(expected, graph.numberOfNodes)
    }

    @Test
    fun `adding an edge to two nodes is commutative`() {
        // given two graphs
        val graph1 = Graph<Int>()
        val graph2 = Graph<Int>()

        val node1 = 1
        val node5 = 5

        graph1.addNode(node1)
        graph1.addNode(node5)

        graph2.addNode(node1)
        graph2.addNode(node5)

        // when addEdge is called, passing the nodes
        // in different order
        graph1.addEdge(node1, node5)
        graph2.addEdge(node5, node1)

        val areTheSame = graph1.adjacentList == graph2.adjacentList

        assertTrue(areTheSame)
    }

    @Test
    fun `calling addEdge multiple times with the same node parameters does not update the connected nodes`() {
        val node1 = 1
        val node3 = 3

        graph.addNode(node1)
        graph.addNode(node3)

        graph.addEdge(node1, node3)
        graph.addEdge(node1, node3)
        graph.addEdge(node1, node3)

        val expected = mutableMapOf(
            node1 to mutableListOf(node3),
            node3 to mutableListOf(node1)
        )

        assertEquals(
            expected,
            graph.adjacentList
        )
    }
}