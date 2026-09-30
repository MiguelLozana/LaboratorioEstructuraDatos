package grafos;

import grafos.exceptions.ElementNotPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GraphComprehensiveTests {

    private static final double DELTA = 0.0001;

    @Test
    void testCompleteRemovalAndReconstruction() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();

        // Initial edges
        assertTrue(graph.existsEdge('A', 'B'));
        assertTrue(graph.existsEdge('B', 'C'));
        assertTrue(graph.existsEdge('C', 'D'));
        assertEquals(1.0, graph.getWeight('A', 'B'), DELTA);
        assertEquals(2.0, graph.getWeight('B', 'C'), DELTA);
        assertEquals(3.0, graph.getWeight('C', 'D'), DELTA);

        // Remove existing nodes
        assertTrue(graph.removeNode('A'));
        assertTrue(graph.removeNode('B'));
        assertTrue(graph.removeNode('C'));
        assertTrue(graph.removeNode('D'));

        // Verify complete removal
        assertEquals(0, graph.getSize());
        assertFalse(graph.existsNode('A'));
        assertFalse(graph.existsNode('B'));
        assertFalse(graph.existsNode('C'));
        assertFalse(graph.existsNode('D'));
        assertFalse(graph.existsEdge('A', 'B'));
        assertFalse(graph.existsEdge('B', 'C'));
        assertFalse(graph.existsEdge('C', 'D'));
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('A', 'B'));
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('B', 'C'));
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('C', 'D'));

        // Reconstruct parts of the graph
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('C');
        graph.addNode('D');
        assertTrue(graph.addEdge('A', 'B', 10.0));
        assertTrue(graph.addEdge('B', 'C', 20.0));

        // Verify reconstruction
        assertTrue(graph.existsEdge('A', 'B'));
        assertTrue(graph.existsEdge('B', 'C'));
        assertEquals(10.0, graph.getWeight('A', 'B'), DELTA);
        assertEquals(20.0, graph.getWeight('B', 'C'), DELTA);

        assertFalse(graph.existsEdge('C', 'D'));
        assertEquals(-1, graph.getWeight('C', 'D'), DELTA);

    }

    @Test
    void testSelfLoopOperationsAndRemoval() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCyclicCharacterGraph();
        // Add self-loops
        assertTrue(graph.addEdge('A', 'A', 100.0));
        assertTrue(graph.addEdge('B', 'B', 200.0));
        assertTrue(graph.existsEdge('A', 'A'));
        assertTrue(graph.existsEdge('B', 'C'));
        assertEquals(100.0, graph.getWeight('A', 'A'), DELTA);
        assertEquals(200.0, graph.getWeight('B', 'B'), DELTA);

        // Remove self-loop A
        assertTrue(graph.removeNode('A'));
        assertFalse(graph.existsNode('A'));
        assertThrows(ElementNotPresentException.class, () -> graph.getWeight('A', 'A'));

        // Other lops should remain unaffected
        assertTrue(graph.existsEdge('B', 'B'));
        assertEquals(200, graph.getWeight('B', 'B'), DELTA);

        // Remove self-loop B
        assertTrue(graph.removeEdge('B', 'B'));
        assertFalse(graph.existsEdge('B', 'B'));
        assertEquals(-1, graph.getWeight('B', 'B'), DELTA);

        // Re-add self-loop B
        assertTrue(graph.addEdge('B', 'B', 7.0));
        assertTrue(graph.existsEdge('B', 'B'));
        assertEquals(7.0, graph.getWeight('B', 'B'), DELTA);

        // Directly inspect internal structures
        boolean[][] edges = graph.getEdges();
        double[][] weights = graph.getWeights();
        int bIndex = graph.find('B');
        assertTrue(edges[bIndex][bIndex]);
        assertEquals(7.0, weights[bIndex][bIndex], DELTA);
    }

    @Test
    void testMixedRemovalAndAdditionCycle() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCyclicCharacterGraph();

        // Initial edges
        boolean[][] expectedEdges = { // Adjacency matrix 6x6 capacity, 5 nodes
                {false, true, false, false, false, false}, // A
                {false, false, true, false, false, false}, // B
                {false, false, false, true, false, false}, // C
                {false, false, false, false, true, false}, // D
                {true, false, false, false, false, false}, // E
                {false, false, false, false, false, false} // Empty
        };// A B C D E -

        double[][] expectedWeights = { // Weights matrix 6x6 capacity, 5 nodes
                {0.0, 1.0, 0.0, 0.0, 0.0, 0.0}, // A
                {0.0, 0.0, 2.0, 0.0, 0.0, 0.0}, // B
                {0.0, 0.0, 0.0, 3.0, 0.0, 0.0}, // C
                {0.0, 0.0, 0.0, 0.0, 4.0, 0.0}, // D
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // E
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        };// A B C D E -

        assertArrayEquals(expectedEdges, graph.getEdges());
        assertArrayEquals(expectedWeights, graph.getWeights());

        // Remove node C
        assertTrue(graph.removeNode('C'));
        expectedEdges = new boolean[][]{ // Updated adjacency matrix after removing C
                {false, true, false, false, false, false}, // A
                {false, false, false, false, false, false}, // B
                {true, false, false, false, false, false}, // E (shifted up)
                {false, false, true, false, true, false}, // D
                {true, false, false, false, false, false}, // Empty (formerly E)
                {false, false, false, false, false, false} // Empty
        };// A B E D - -

        expectedWeights = new double[][]{ // Updated weights matrix after removing C
                {0.0, 1.0, 0.0, 0.0, 0.0, 0.0}, // A
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // B
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // E (shifted up)
                {0.0, 0.0, 4.0, 0.0, 4.0, 0.0}, // D
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Empty (formerly E)
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        }; // A B E D - -

        assertArrayEquals(expectedEdges, graph.getEdges());
        assertArrayEquals(expectedWeights, graph.getWeights());

        // Add node F
        assertTrue(graph.addNode('F'));
        expectedEdges = new boolean[][]{ // Updated adjacency matrix after removing C
                {false, true, false, false, false, false}, // A
                {false, false, false, false, false, false}, // B
                {true, false, false, false, false, false}, // E (shifted up)
                {false, false, true, false, false, false}, // D
                {false, false, false, false, false, false}, // F (new)
                {false, false, false, false, false, false} // Empty
        };// A B E D F -

        expectedWeights = new double[][]{ // Updated weights matrix after removing C
                {0.0, 1.0, 0.0, 0.0, 0.0, 0.0}, // A
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // B
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // E (shifted up)
                {0.0, 0.0, 4.0, 0.0, 0.0, 0.0}, // D
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // F (new)
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        }; // A B E D F -

        boolean[][] edges = graph.getEdges();
        assertArrayEquals(expectedEdges, edges);

        double[][] weights = graph.getWeights();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) {
                    assertEquals(expectedWeights[i][j], weights[i][j]);
                }
            }
        }

        // Add edges A->F and F->D
        assertTrue(graph.addEdge('A', 'F', 8.0));
        assertTrue(graph.addEdge('F', 'D', 9.0));

        expectedEdges = new boolean[][]{ // Updated adjacency matrix after adding edges
                {false, true, false, false, true, false}, // A
                {false, false, false, false, false, false}, // B
                {true, false, false, false, false, false}, // E
                {false, false, true, false, false, false}, // D
                {false, false, false, true, false, false}, // F
                {false, false, false, false, false, false} // Empty
        };// A B E D F -

        expectedWeights = new double[][]{ // Updated weights matrix after adding edges
                {0.0, 1.0, 0.0, 0.0, 8.0, 0.0}, // A
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // B
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // E
                {0.0, 0.0, 4.0, 0.0, 0.0, 0.0}, // D
                {0.0, 0.0, 0.0, 9.0, 0.0, 0.0}, // F
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        }; // A B E D F -

        edges = graph.getEdges();
        assertArrayEquals(expectedEdges, edges);

        weights = graph.getWeights();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) {
                    assertEquals(expectedWeights[i][j], weights[i][j]);
                }
            }
        }

        // remove F
        assertTrue(graph.removeNode('F'));
        // Since it was the last node, no changes occur, only size gets decremented.
        edges = graph.getEdges();
        assertArrayEquals(expectedEdges, edges);

        weights = graph.getWeights();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) {
                    assertEquals(expectedWeights[i][j], weights[i][j]);
                }
            }
        }

        // add self-loop D->D (last node)
        assertTrue(graph.addEdge('D', 'D', 7.0));

        // Remove B and check if the self-loop is properly managed
        assertTrue(graph.removeNode('B'));
        expectedEdges = new boolean[][]{ // Updated adjacency matrix after removing B
                {false, false, false, false, true, false}, // A
                {false, true, true, false, false, false}, // D (shifted up)
                {true, false, false, false, false, false}, // E
                {false, false, true, true, false, false}, // Empty (formerly D)
                {false, false, false, true, false, false}, // Empty
                {false, false, false, false, false, false} // Empty
        };// A D E - - -

        expectedWeights = new double[][]{ // Updated weights matrix after removing B
                {0.0, 0.0, 0.0, 0.0, 8.0, 0.0}, // A
                {0.0, 7.0, 4.0, 0.0, 0.0, 0.0}, // D (shifted up)
                {5.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // E
                {0.0, 0.0, 4.0, 7.0, 0.0, 0.0}, // Empty (formerly D)
                {0.0, 0.0, 0.0, 9.0, 0.0, 0.0}, // Empty
                {0.0, 0.0, 0.0, 0.0, 0.0, 0.0} // Empty
        }; // A D E - - -

        edges = graph.getEdges();
        assertArrayEquals(expectedEdges, edges);

        weights = graph.getWeights();
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j]) {
                    assertEquals(expectedWeights[i][j], weights[i][j]);
                }
            }
        }
    }
}
