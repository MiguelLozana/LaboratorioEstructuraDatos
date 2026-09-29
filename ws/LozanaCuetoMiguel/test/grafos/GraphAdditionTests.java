package grafos;

import grafos.exceptions.ElementNotPresentException;
import grafos.exceptions.FullStructureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GraphAdditionTests {

    private static final double DELTA = 0.0001;

    // Constructor and Basic Structure Tests
    @Test
    void testConstructorAndInitialization() {
        // Test invalid parameters
        assertThrows(IllegalArgumentException.class, () -> new FixedSizeGraph<Integer>(-1));
        assertThrows(IllegalArgumentException.class, () -> new FixedSizeGraph<Integer>(0));

        // Test valid initialization
        FixedSizeGraph<Integer> graph = new FixedSizeGraph<Integer>(5);
        assertEquals(5, graph.getCapacity());
        assertEquals(0, graph.getSize());
        assertEquals(5, graph.getEdges().length);
        assertEquals(5, graph.getWeights().length);
    }

    @Test
    void testMatrixStructureAndInitialValues() {
        FixedSizeGraph<Integer> graph = new FixedSizeGraph<Integer>(5);
        boolean[][] edges = graph.getEdges();
        double[][] weights = graph.getWeights();

        // Verify structure
        assertNotNull(edges);
        assertNotNull(weights);
        assertEquals(edges.length, edges[0].length);
        assertEquals(weights.length, weights[0].length);

        // Verify initial values
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                assertFalse(edges[row][column]);
                assertEquals(0.0, weights[row][column], DELTA);
            }
        }
    }

    // Node Operations Tests
    @Test
    void testNodeExistenceAndIndexing() {
        FixedSizeGraph<Integer> graph = new FixedSizeGraph<Integer>(5);

        // Test null parameter
        assertThrows(NullPointerException.class, () -> graph.existsNode(null));

        // Test initial state
        assertFalse(graph.existsNode(1));
        assertEquals(-1, ((FixedSizeGraph<Integer>) graph).find(1));

        // Test after adding nodes
        graph.addNode(10);
        graph.addNode(20);
        assertTrue(graph.existsNode(10));
        assertEquals(0, ((FixedSizeGraph<Integer>) graph).find(10));
        assertEquals(1, ((FixedSizeGraph<Integer>) graph).find(20));
        assertEquals(2, graph.getSize());
    }

    @Test
    void testAddNodeValidationAndLimits() {
        FixedSizeGraph<Integer> graph = new FixedSizeGraph<Integer>(2);

        // Test null parameter
        assertThrows(NullPointerException.class, () -> graph.addNode(null));

        // Test successful additions and duplicates
        assertTrue(graph.addNode(1));
        assertEquals(1, graph.getSize());
        assertFalse(graph.addNode(1)); // duplicate

        // Test capacity limits
        graph.addNode(2);
        assertThrows(FullStructureException.class, () -> graph.addNode(2)); // duplicate at capacity
        assertThrows(FullStructureException.class, () -> graph.addNode(3));
    }

    // Edge Operations Tests
    @Test
    void testExistsEdgeValidation() {
        Graph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        // Test null parameters
        assertThrows(NullPointerException.class, () -> graph.existsEdge(null, 'A'));
        assertThrows(NullPointerException.class, () -> graph.existsEdge('A', null));

        // Test non-existent scenarios
        assertFalse(graph.existsEdge('B', 'z')); // non-existent node
        assertFalse(graph.existsEdge('B', 'C')); // valid nodes, no edge
        assertFalse(graph.existsEdge('z', 'y')); // both nodes non-existent
    }

    @Test
    void testAddEdgeValidationAndOperations() {
        Graph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        // Test null parameters
        assertThrows(NullPointerException.class, () -> graph.addEdge(null, 'B', 1.0));
        assertThrows(NullPointerException.class, () -> graph.addEdge('A', null, 1.0));

        // Test invalid weight
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge('A', 'B', -5.0));
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge('A', 'B', 0));

        // Test non-existent nodes
        assertThrows(ElementNotPresentException.class, () -> graph.addEdge('x', 'A', 2));

        // Test successful operations
        assertTrue(graph.addEdge('A', 'B', 2.0));
        assertTrue(graph.existsEdge('A', 'B'));

        // duplicate edge
        assertFalse(graph.addEdge('A', 'B', 3.0));
        // weight should remain unchanged after duplicate attempt
        assertEquals(2.0, graph.getWeight('A', 'B'), DELTA);

        // Test multiple edges
        assertTrue(graph.addEdge('A', 'C', 1.0));
        assertTrue(graph.addEdge('C', 'A', 3.0));
        assertTrue(graph.existsEdge('A', 'C'));
        assertTrue(graph.existsEdge('C', 'A'));
    }

    // Weight Operations Tests
    @Test
    void testGetWeightOperations() {
        Graph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();
        graph.addEdge('A', 'B', 2.0);
        graph.addEdge('B', 'C', 5.0);

        // Test existing edges
        assertEquals(2.0, graph.getWeight('A', 'B'), DELTA);
        assertEquals(5.0, graph.getWeight('B', 'C'), DELTA);

        // Test non-existent edges between valid nodes
        assertEquals(-1, graph.getWeight('A', 'C'), DELTA);

        // Test non-existent nodes
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('A', 'z'));
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('z', 'B'));
    }

    @Test
    void testCompleteWorkflowAndMatrixState() {
        FixedSizeGraph<Character> graph = new FixedSizeGraph<Character>(5);

        // Test complete workflow from empty to populated
        assertEquals(0, graph.getSize());
        assertTrue(graph.addNode('A'));
        assertEquals(1, graph.getSize());
        assertEquals(0, graph.find('A'));

        // Add more nodes and test indexing
        assertFalse(graph.addNode('A')); // duplicate
        assertTrue(graph.addNode('B'));
        assertTrue(graph.addNode('C'));
        assertEquals(3, graph.getSize());
        assertEquals(0, graph.find('A'));
        assertEquals(1, graph.find('B'));
        assertEquals(2, graph.find('C'));
    }

    @Test
    void testFinalMatrixStateVerification() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCompleteCharacterGraph();

        boolean[][] expectedEdges = { // 6x6 matrix because it has capacity 6, but only 5 nodes used
                {false, true, true, false, false, false}, // A
                {true, false, true, false, false, false}, // B
                {false, true, false, true, false, false}, // C
                {false, true, false, false, false, false}, // D
                {false, false, false, true, false, false}, // E
                {false, false, false, false, false, false} // Empty
        };

        double[][] expectedWeights = { // 6x6 matrix because it has capacity 6, but only 5 nodes used
                {0.0, 20.0, 3.0, 0.0, 0.0, 0.0}, // A
                {5.0, 0.0, 10.0, 0.0, 0.0, 0.0}, // B
                {0.0, 15.0, 0.0, 1.0, 0.0, 0.0}, // C
                {0.0, 2.0, 0.0, 0.0, 0.0, 0.0}, // D
                {0.0, 0.0, 0.0, 4.0, 0.0, 0.0}, // E
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        };

        assertArrayEquals(expectedEdges, graph.getEdges());
        assertArrayEquals(expectedWeights, graph.getWeights());
    }
}
