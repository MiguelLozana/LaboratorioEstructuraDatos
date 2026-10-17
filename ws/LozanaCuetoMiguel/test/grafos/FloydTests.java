package grafos;

import grafos.exceptions.ElementNotPresentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class FloydTests {

    private static final double DELTA = 0.0001;
    private final double INF = Double.POSITIVE_INFINITY;
    private final int EMP = -1;

    @Test
    void testEmptyGraph() {
        // A graph with no nodes
        FixedSizeGraph<Character> graph = new FixedSizeGraph<>(10);

        // Floyd should return false and the matrices should be empty.
        assertFalse(graph.floyd());
        assertNull(graph.getFloydCostsA());
        assertNull(graph.getFloydPathsP());
    }

    @Test
    void testIsolatedNodesGraph() {
        // Graph with isolated nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createCharacterGraphWithIsolatedNodes();

        // The Floyd matrices should show infinite costs between all nodes and no paths.
        // The last rows and columns should remain zero (for unused capacity).
        assertTrue(graph.floyd());

        double[][] expectedFloydCostsA = { //
                {0.0, INF, INF, INF}, // A
                {INF, 0.0, INF, INF}, // B
                {INF, INF, 0.0, INF}, // C
                {INF, INF, INF, 0.0}, // D
        };// ABCD--

        int[][] expectedFloydPathsP = { //
                {EMP, EMP, EMP, EMP}, // A
                {EMP, EMP, EMP, EMP}, // B
                {EMP, EMP, EMP, EMP}, // C
                {EMP, EMP, EMP, EMP}, // D
        };// ABCD--

        assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
        assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());

    }

    @Test
    void testBasicTraversalGraph() {
        // Graph with connected nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

        // The Floyd matrices should show the minimum costs and paths between all nodes.
        // The unused rows and columns should remain at 0.
        assertTrue(graph.floyd());

        double[][] expectedFloydCostsA = { //
                {0.0, INF, 1.0, INF}, // A
                {1.0, 0.0, 2.0, INF}, // B
                {INF, INF, 0.0, INF}, // C
                {31.0, 30.0, 32.0, 0.0}, // D
        };// ABCD--

        int[][] expectedFloydPathsP = { //
                {EMP, EMP, EMP, EMP}, // A
                {EMP, EMP, 0, EMP}, // B
                {EMP, EMP, EMP, EMP}, // C
                {1, EMP, 1, EMP}, // D
        };// ABCD--

        assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
        assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());
    }

    @Test
    void testComplexTraversalGraph() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        // The Floyd matrices should show the minimum costs and paths between all nodes.
        // The unused rows and columns should remain at 0.
        assertTrue(graph.floyd());

        double[][] expectedFloydCostsA = { //
                {0.0, 1.0, 3.0, 6.0, 9.0}, // A
                {10.0, 0.0, 2.0, 5.0, 8.0}, // B
                {8.0, 9.0, 0.0, 3.0, 6.0}, // C
                {5.0, 6.0, 8.0, 0.0, 4.0}, // D
                {INF, INF, INF, INF, 0.0}, // E
        };// ABCDE-

        int[][] expectedFloydPathsP = { //
                {EMP, EMP, 1, 2, 2}, // A
                {3, EMP, EMP, 2, 2}, // B
                {3, 3, EMP, EMP, EMP}, // C
                {EMP, 0, EMP, EMP, EMP}, // D
                {EMP, EMP, EMP, EMP, EMP}, // E
        };// ABCDE-

        assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
        assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());
    }

    @Test
    void testPrintFloydPathExceptions() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

        // It should not be needed to call floyd() before checking exceptions,
        // printFloydPath does it automatically if not done yet.
        assertThrows(NullPointerException.class, () -> graph.printFloydPath(null, 'A'));
        assertThrows(NullPointerException.class, () -> graph.printFloydPath('A', null));
        assertThrows(NullPointerException.class, () -> graph.printFloydPath(null, null));

        // Node not in graph
        assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('X', 'A'));
        assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('A', 'X'));
        assertThrows(ElementNotPresentException.class, () -> graph.printFloydPath('X', 'Y'));

    }

    @Test
    void testFloydPathBasicTraversalGraph() {
        // Graph with connected nodes A, B, C, D
        FixedSizeGraph<Character> graph = GraphTestHelper.createBasicTraversalCharacterGraph();

        // It should not be needed to call floyd() before checking exceptions,
        // printFloydPath does it automatically if not done yet.

        String pathAtoC = graph.printFloydPath('A', 'C');
        assertEquals("A\t(1.0)\tC", pathAtoC);

        String pathBtoC = graph.printFloydPath('B', 'C');
        assertEquals("B\t(1.0)\tA\t(1.0)\tC", pathBtoC);

        String pathDtoA = graph.printFloydPath('D', 'A');
        assertEquals("D\t(30.0)\tB\t(1.0)\tA", pathDtoA);

        String pathDtoC = graph.printFloydPath('D', 'C');
        assertEquals("D\t(30.0)\tB\t(1.0)\tA\t(1.0)\tC", pathDtoC);

        String pathCtoA = graph.printFloydPath('C', 'A');
        assertEquals("C\t(Infinity)\tA", pathCtoA);
    }

    @Test
    void testFloydPathComplexTraversalGraph() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        // It should not be needed to call floyd() before checking exceptions,
        // printFloydPath does it automatically if not done yet.

        String pathAtoA = graph.printFloydPath('A', 'A');
        assertEquals("A\t(7.0)\tA", pathAtoA);

        String pathAtoE = graph.printFloydPath('A', 'E');
        assertEquals("A\t(1.0)\tB\t(2.0)\tC\t(6.0)\tE", pathAtoE);

        String pathBtoE = graph.printFloydPath('B', 'E');
        assertEquals("B\t(2.0)\tC\t(6.0)\tE", pathBtoE);

        String pathBtoA = graph.printFloydPath('B', 'A');
        assertEquals("B\t(2.0)\tC\t(3.0)\tD\t(5.0)\tA", pathBtoA);

        String pathCtoE = graph.printFloydPath('C', 'E');
        assertEquals("C\t(6.0)\tE", pathCtoE);

        String pathDtoA = graph.printFloydPath('D', 'A');
        assertEquals("D\t(5.0)\tA", pathDtoA);

        String pathEtoA = graph.printFloydPath('E', 'A');
        assertEquals("E\t(Infinity)\tA", pathEtoA);
    }

    @Test
    void testFloydRemoveEdge() {

        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        // It should not be needed to call floyd() before checking exceptions,
        // printFloydPath does it automatically if not done yet.

        String pathAtoA = graph.printFloydPath('A', 'A');
        assertEquals("A\t(0.0)\tA", pathAtoA);

        String pathAtoE = graph.printFloydPath('A', 'E');
        assertEquals("A\t(1.0)\tB\t(2.0)\tC\t(6.0)\tE", pathAtoE);

        graph.removeEdge('B', 'C');

        // After the removal we shouldn't need to explicitly call floyd() again.
        // printFloydPath should detect that floyd is not up to date and call it.

        pathAtoE = graph.printFloydPath('A', 'E');
        assertEquals("A\t(1.0)\tB\t(10.0)\tE", pathAtoE);

        String pathBtoE = graph.printFloydPath('B', 'E');
        assertEquals("B\t(10.0)\tE", pathBtoE);

        String pathBtoA = graph.printFloydPath('B', 'A');
        assertEquals("B\t(Infinity)\tA", pathBtoA);

        String pathCtoE = graph.printFloydPath('C', 'E');
        assertEquals("C\t(6.0)\tE", pathCtoE);

        String pathDtoA = graph.printFloydPath('D', 'A');
        assertEquals("D\t(5.0)\tA", pathDtoA);

        String pathEtoA = graph.printFloydPath('E', 'A');
        assertEquals("E\t(Infinity)\tA", pathEtoA);

    }

    @Test
    void testFloydRemoveNode() {
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        assertTrue(graph.floyd());

        double[][] expectedFloydCostsA = { //
                {0.0, 1.0, 3.0, 6.0, 9.0}, // A
                {10.0, 0.0, 2.0, 5.0, 8.0}, // B
                {8.0, 9.0, 0.0, 3.0, 6.0}, // C
                {5.0, 6.0, 8.0, 0.0, 4.0}, // D
                {INF, INF, INF, INF, 0.0}, // E
        };// ABCDE-

        int[][] expectedFloydPathsP = { //
                {EMP, EMP, 1, 2, 2}, // A
                {3, EMP, EMP, 2, 2}, // B
                {3, 3, EMP, EMP, EMP}, // C
                {EMP, 0, EMP, EMP, EMP}, // D
                {EMP, EMP, EMP, EMP, EMP}, // E
        };// ABCDE-

        assertArrayEquals(expectedFloydCostsA, graph.getFloydCostsA());
        assertArrayEquals(expectedFloydPathsP, graph.getFloydPathsP());

        graph.removeNode('C');
        graph.floyd();

        // The new matrices should be updated
        // The last rows and columns should remain the same after removal.
        double[][] newExpectedFloydCostsA = { //
                {0.0, 1.0, 11.0, INF}, // A
                {INF, 0.0, 10.0, INF}, // B
                {INF, INF, 0.0, INF}, // E
                {5.0, 6.0, 4.0, 0.0}, // D
        };// ABED--

        int[][] newExpectedFloydPathsP = { //
                {EMP, EMP, 1, EMP}, // A
                {EMP, EMP, EMP, EMP}, // B
                {EMP, EMP, EMP, EMP}, // E
                {EMP, 0, EMP, EMP}, // D
        };// ABED--

        assertArrayEquals(newExpectedFloydCostsA, graph.getFloydCostsA());
        assertArrayEquals(newExpectedFloydPathsP, graph.getFloydPathsP());
    }

    @Test
    void testGetMinimumCostPathFloyd() {
        // Graph with complex connections A, B, C, D, E
        FixedSizeGraph<Character> graph = GraphTestHelper.createComplexTraversalCharacterGraph();

        // It should not be needed to call floyd() explicitly.
        // minimumCostPathFloyd should do it automatically if not done yet.
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('X', 'A'));
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('A', 'X'));
        assertThrows(ElementNotPresentException.class, () -> graph.minimumCostPathFloyd('X', 'Y'));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd(null, 'A'));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd('A', null));
        assertThrows(NullPointerException.class, () -> graph.minimumCostPathFloyd(null, null));

        assertEquals(6.0, graph.minimumCostPathFloyd('A', 'D'), DELTA);
        assertEquals(5.0, graph.minimumCostPathFloyd('B', 'D'), DELTA);
        assertEquals(3.0, graph.minimumCostPathFloyd('C', 'D'), DELTA);
        assertEquals(0.0, graph.minimumCostPathFloyd('D', 'D'), DELTA);
    }

}
