package grafos;

import grafos.exceptions.ElementNotPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DepthFirstTraversalTests {

    @Test
    void testExceptions() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        assertThrows(NullPointerException.class, () -> graph.printDepthFirstTraversal(null));
        assertThrows(ElementNotPresentException.class, () -> graph.printDepthFirstTraversal('x'));
    }

    @Test
    void testIsolatedNodesGraph() {
        // Graph with isolated nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        // All traversals should only return the starting node.
        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-", traversalD);
    }

    @Test
    void testLinearGraph() {
        // Graph with linear connections A->B->C->D
        FixedSizeGraph<Character> graph = GraphTestHelper.createLinearCharacterGraph();

        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-C-D-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-D-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-", traversalD);
    }

    @Test
    void testCyclicGraph() {
        // Graph with cyclic connections A->B->C->D->E->A
        FixedSizeGraph<Character> graph = GraphTestHelper.createCyclicCharacterGraph();

        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-E-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-C-D-E-A-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-D-E-A-B-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-E-A-B-C-", traversalD);

        String traversalE = graph.printDepthFirstTraversal('E');
        assertEquals("E-A-B-C-D-", traversalE);
    }

    @Test
    void testBasicTraversalGraph() {
        // Graph with connected nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-C-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-A-C-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-B-A-C-", traversalD);
    }

    @Test
    void testComplexTraversalGraph() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-E-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-C-A-E-D-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-A-B-E-D-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-A-B-C-E-", traversalD);

        String traversalE = graph.printDepthFirstTraversal('E');
        assertEquals("E-", traversalE);
    }

    @Test
    void testStepByStepGraphBuilding() {
        // Start with isolated nodes A, B, C, D and build the graph step by step
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        String traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-", traversalA);

        String traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-", traversalB);

        String traversalC = graph.printDepthFirstTraversal('C');
        assertEquals("C-", traversalC);

        String traversalD = graph.printDepthFirstTraversal('D');
        assertEquals("D-", traversalD);

        graph.addEdge('A', 'A', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-", traversalA);

        graph.addEdge('A', 'B', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-", traversalA);

        graph.addEdge('A', 'C', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-", traversalA);

        graph.addEdge('C', 'D', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-", traversalA);

        graph.addEdge('D', 'B', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-", traversalA);

        graph.addEdge('B', 'C', 1.0);
        traversalA = graph.printDepthFirstTraversal('A');
        assertEquals("A-B-C-D-", traversalA);

        graph.addEdge('B', 'A', 1.0);
        traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-A-C-D-", traversalB);

        graph.removeNode('A');
        traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-C-D-", traversalB);

        graph.addNode('A');
        graph.addEdge('B', 'A', 1.0);
        traversalB = graph.printDepthFirstTraversal('B');
        assertEquals("B-C-D-A-", traversalB);
    }

}
