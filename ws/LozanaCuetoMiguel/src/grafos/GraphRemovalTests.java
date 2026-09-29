package grafos;

import grafos.exceptions.ElementNotPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GraphRemovalTests {

    private static final double DELTA = 0.0001;

    // RemoveEdge Tests - Basic Functionality
    @Test
    void testRemoveEdgeNullParameters() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
        assertThrows(NullPointerException.class, () -> graph.removeEdge(null, 'A'));
        assertThrows(NullPointerException.class, () -> graph.removeEdge('A', null));
    }

    @Test
    void testRemoveEdgeNonExistentNodes() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
        assertThrows(ElementNotPresentException.class, () -> graph.removeEdge('z', 'A'));
        assertThrows(ElementNotPresentException.class, () -> graph.removeEdge('A', 'z'));
    }

    @Test
    void testRemoveEdgeSuccessfulRemoval() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertTrue(graph.existsEdge('A', 'B'));
        assertTrue(graph.removeEdge('A', 'B'));
        assertFalse(graph.existsEdge('A', 'B'));
    }

    @Test
    void testRemoveEdgeNonExistentEdge() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertFalse(graph.removeEdge('D', 'A')); // No edge between these nodes
    }

    @Test
    void testRemoveEdgeWeightClearing() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertEquals(1.0, graph.getWeight('A', 'B'), DELTA);
        graph.removeEdge('A', 'B');
        assertEquals(-1, graph.getWeight('A', 'B'), DELTA);
    }

    @Test
    void testRemoveEdgeMultipleOperations() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertTrue(graph.existsEdge('B', 'C'));
        assertTrue(graph.removeEdge('B', 'C'));
        assertFalse(graph.existsEdge('B', 'C'));
        assertFalse(graph.removeEdge('B', 'C')); // Second removal should return false
    }

    // RemoveNode Tests - Basic Functionality
    @Test
    void testRemoveNodeNullParameter() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertThrows(NullPointerException.class, () -> graph.removeNode(null));
    }

    @Test
    void testRemoveNodeNonExistent() {
        Graph<Character> graph = GraphTestHelper.createLinearCharacterGraph();
        assertFalse(graph.removeNode('z'));
    }

    @Test
    void testRemoveNodeSingleNode() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
        assertEquals(4, graph.getSize());
        assertTrue(graph.removeNode('A'));
        assertEquals(3, graph.getSize());
        assertFalse(graph.existsNode('A'));
    }

    @Test
    void testRemoveNodeWithEdgeCleanup() {
        Graph<Character> graph = GraphTestHelper.createCyclicCharacterGraph();
        assertEquals(5, graph.getSize());
        assertTrue(graph.removeNode('A'));
        assertEquals(4, graph.getSize());
        assertFalse(graph.existsNode('A'));
        // Former edges that should have been removed
        assertFalse(graph.existsEdge('A', 'B'));
        assertFalse(graph.existsEdge('E', 'A'));
    }

    @Test
    void testRemoveNodeIndexManagement() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCyclicCharacterGraph();

        assertEquals(5, graph.getSize());

        // E (last node) should move to A's former position
        assertEquals(4, graph.find('E'));
        assertTrue(graph.removeNode('A'));
        assertEquals(0, graph.find('E'));
        assertEquals(4, graph.getSize());

        // D (last node) should move to C's former position
        assertEquals(3, graph.find('D'));
        assertTrue(graph.removeNode('C'));
        assertEquals(2, graph.find('D'));
        assertEquals(3, graph.getSize());

        // D (last node) should move to E's former position
        assertEquals(2, graph.find('D'));
        assertTrue(graph.removeNode('E'));
        assertEquals(0, graph.find('D'));
        assertEquals(2, graph.getSize());

        // B (last node) should not change any other index when deleted
        assertEquals(0, graph.find('D'));
        assertTrue(graph.removeNode('B'));
        assertEquals(0, graph.find('D'));
        assertEquals(1, graph.getSize());

    }

    @Test
    void testRemoveNodeComprehensiveEdgeCleanup() {
        Graph<Character> edgeTestGraph = GraphTestHelper.createCompleteCharacterGraph();

        // B is connected to A, C and D
        // Edges involving B should be removed
        assertTrue(edgeTestGraph.existsEdge('A', 'B'));
        assertTrue(edgeTestGraph.existsEdge('B', 'A'));
        assertTrue(edgeTestGraph.existsEdge('B', 'C'));
        assertTrue(edgeTestGraph.existsEdge('C', 'B'));
        assertTrue(edgeTestGraph.existsEdge('D', 'B'));

        // Remove B
        assertTrue(edgeTestGraph.removeNode('B'));

        // Edges not involving B should remain
        assertTrue(edgeTestGraph.existsEdge('A', 'C'));
        assertTrue(edgeTestGraph.existsEdge('C', 'D'));
        assertTrue(edgeTestGraph.existsEdge('E', 'D'));

        // Edges involving B should be removed
        assertFalse(edgeTestGraph.existsEdge('A', 'B'));
        assertFalse(edgeTestGraph.existsEdge('B', 'A'));
        assertFalse(edgeTestGraph.existsEdge('B', 'C'));
        assertFalse(edgeTestGraph.existsEdge('C', 'B'));
        assertFalse(edgeTestGraph.existsEdge('D', 'B'));
    }
}
