package grafos;

import grafos.exceptions.ElementNotPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DijkstraTests {
    private static final double DELTA = 0.0001;
    private final double INF = Double.POSITIVE_INFINITY;
    private final int NO_P = -1;

    @Test
    void testExceptions() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        assertThrows(ElementNotPresentException.class, () -> graph.dijkstra('x'));
        assertThrows(NullPointerException.class, () -> graph.dijkstra(null));
    }

    @Test
    void testIsolatedNodesGraph() {
        // Graph with isolated nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        // The vectors for each node should show infinite costs to all other nodes and no predecessors.
        DijkstraDataClass dijkstra = graph.dijkstra('A');

        double[] dijkstraCostsDFromA = {0.0, INF, INF, INF};
        int[] dijkstraPathsPFromA = {NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromA, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromA, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('B');

        double[] dijkstraCostsDFromB = {INF, 0.0, INF, INF};
        int[] dijkstraPathsPFromB = {NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromB, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromB, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('C');

        double[] dijkstraCostsDFromC = {INF, INF, 0.0, INF};
        int[] dijkstraPathsPFromC = {NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromC, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromC, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('D');

        double[] dijkstraCostsDFromD = {INF, INF, INF, 0.0};
        int[] dijkstraPathsPFromD = {NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromD, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromD, dijkstra.getDijkstraPathsP());

    }

    @Test
    void testBasicTraversalGraph() {
        // Graph with connected nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

        DijkstraDataClass dijkstra = graph.dijkstra('A');

        double[] dijkstraCostsDFromA = {0.0, INF, 1.0, INF};
        int[] dijkstraPathsPFromA = {NO_P, NO_P, 0, NO_P};

        assertArrayEquals(dijkstraCostsDFromA, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromA, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('B');

        double[] dijkstraCostsDFromB = {1.0, 0.0, 2.0, INF};
        int[] dijkstraPathsPFromB = {1, NO_P, 0, NO_P};

        assertArrayEquals(dijkstraCostsDFromB, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromB, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('C');

        double[] dijkstraCostsDFromC = {INF, INF, 0.0, INF};
        int[] dijkstraPathsPFromC = {NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromC, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromC, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('D');

        double[] dijkstraCostsDFromD = {31.0, 30.0, 32.0, 0.0};
        int[] dijkstraPathsPFromD = {1, 3, 0, NO_P};
        System.out.println(dijkstra);
        assertArrayEquals(dijkstraCostsDFromD, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromD, dijkstra.getDijkstraPathsP());
    }

    @Test
    void testComplexTraversalGraph() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        DijkstraDataClass dijkstra = graph.dijkstra('A');

        double[] dijkstraCostsDFromA = {0.0, 1.0, 3.0, 6.0, 9.0};
        int[] dijkstraPathsPFromA = {NO_P, 0, 1, 2, 2};

        assertArrayEquals(dijkstraCostsDFromA, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromA, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('B');

        double[] dijkstraCostsDFromB = {10.0, 0.0, 2.0, 5.0, 8.0};
        int[] dijkstraPathsPFromB = {3, NO_P, 1, 2, 2};

        assertArrayEquals(dijkstraCostsDFromB, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromB, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('C');

        double[] dijkstraCostsDFromC = {8.0, 9.0, 0.0, 3.0, 6.0};
        int[] dijkstraPathsPFromC = {3, 0, NO_P, 2, 2};

        assertArrayEquals(dijkstraCostsDFromC, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromC, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('D');

        double[] dijkstraCostsDFromD = {5.0, 6.0, 8.0, 0.0, 4.0};
        int[] dijkstraPathsPFromD = {3, 0, 3, NO_P, 3};

        assertArrayEquals(dijkstraCostsDFromD, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromD, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('E');

        double[] dijkstraCostsDFromE = {INF, INF, INF, INF, 0.0};
        int[] dijkstraPathsPFromE = {NO_P, NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromE, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromE, dijkstra.getDijkstraPathsP());
    }

    @Test
    void testComplexTraversalGraphWithChanges() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        DijkstraDataClass dijkstra = graph.dijkstra('A');

        double[] dijkstraCostsDFromAInitial = {0.0, 1.0, 3.0, 6.0, 9.0};
        int[] dijkstraPathsPFromAInitial = {NO_P, 0, 1, 2, 2};

        assertArrayEquals(dijkstraCostsDFromAInitial, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromAInitial, dijkstra.getDijkstraPathsP());

        // Remove ane edge and recalculate
        graph.removeEdge('B', 'C');

        dijkstra = graph.dijkstra('A');

        double[] dijkstraCostsDFromA = {0.0, 1.0, INF, INF, 11.0};
        int[] dijkstraPathsPFromA = {NO_P, 0, NO_P, NO_P, 1};

        assertArrayEquals(dijkstraCostsDFromA, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromA, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('B');

        double[] dijkstraCostsDFromB = {INF, 0.0, INF, INF, 10.0};
        int[] dijkstraPathsPFromB = {NO_P, NO_P, NO_P, NO_P, 1};

        assertArrayEquals(dijkstraCostsDFromB, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromB, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('C');

        double[] dijkstraCostsDFromC = {8.0, 9.0, 0.0, 3.0, 6.0};
        int[] dijkstraPathsPFromC = {3, 0, NO_P, 2, 2};

        assertArrayEquals(dijkstraCostsDFromC, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromC, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('D');

        double[] dijkstraCostsDFromD = {5.0, 6.0, 8.0, 0.0, 4.0};
        int[] dijkstraPathsPFromD = {3, 0, 3, NO_P, 3};

        assertArrayEquals(dijkstraCostsDFromD, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromD, dijkstra.getDijkstraPathsP());

        dijkstra = graph.dijkstra('E');

        double[] dijkstraCostsDFromE = {INF, INF, INF, INF, 0.0};
        int[] dijkstraPathsPFromE = {NO_P, NO_P, NO_P, NO_P, NO_P};

        assertArrayEquals(dijkstraCostsDFromE, dijkstra.getDijkstraCostsD());
        assertArrayEquals(dijkstraPathsPFromE, dijkstra.getDijkstraPathsP());
    }

    @Test
    void testGetMinimumCostPathDijkstra() {

        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        // Test exceptions
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathDijkstra('X', 'A'));
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathDijkstra('A', 'X'));
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathDijkstra('X', 'Y'));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathDijkstra(null, 'A'));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathDijkstra('A', null));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathDijkstra(null, null));

        // Test known minimum costs
        assertEquals(6.0, graph.minimumCostPathDijkstra('A', 'D'), DELTA);
        assertEquals(5.0, graph.minimumCostPathDijkstra('B', 'D'), DELTA);
        assertEquals(3.0, graph.minimumCostPathDijkstra('C', 'D'), DELTA);
        assertEquals(0.0, graph.minimumCostPathDijkstra('D', 'D'), DELTA);

    }

}
