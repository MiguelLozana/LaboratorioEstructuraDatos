package grafos;


public class GraphTestHelper {

    public static FixedSizeGraph<Character> createCharacterGraphWithIsolatedNodes() {
        FixedSizeGraph<Character> graph = new FixedSizeGraph<Character>(6);
        graph.addNode('A');
        graph.addNode('B');
        graph.addNode('C');
        graph.addNode('D');
        return graph;
    }

    public static FixedSizeGraph<Character> createLinearCharacterGraph() {
        FixedSizeGraph<Character> graph = createCharacterGraphWithIsolatedNodes();

        graph.addEdge('A', 'B', 1.0);
        graph.addEdge('B', 'C', 2.0);
        graph.addEdge('C', 'D', 3.0);

        return graph;
    }

    public static FixedSizeGraph<Character> createCyclicCharacterGraph() {
        FixedSizeGraph<Character> graph = createCharacterGraphWithIsolatedNodes();

        graph.addNode('E');

        graph.addEdge('A', 'B', 1.0);
        graph.addEdge('B', 'C', 2.0);
        graph.addEdge('C', 'D', 3.0);
        graph.addEdge('D', 'E', 4.0);
        graph.addEdge('E', 'A', 5.0);

        return graph;
    }

    public static FixedSizeGraph<Character> createCompleteCharacterGraph() {
        FixedSizeGraph<Character> graph = createCharacterGraphWithIsolatedNodes();
        graph.addNode('E');

        graph.addEdge('A', 'B', 20.0);
        graph.addEdge('A', 'C', 3.0);
        graph.addEdge('B', 'C', 10.0);
        graph.addEdge('B', 'A', 5.0);
        graph.addEdge('C', 'D', 1.0);
        graph.addEdge('C', 'B', 15.0);
        graph.addEdge('D', 'B', 2.0);
        graph.addEdge('E', 'D', 4.0);

        return graph;
    }

    public static FixedSizeGraph<Character> createBasicTraversalCharacterGraph() {
        FixedSizeGraph<Character> graph = createCharacterGraphWithIsolatedNodes();

        graph.addEdge('A', 'C', 1.0);
        graph.addEdge('B', 'A', 1.0);
        graph.addEdge('B', 'C', 10.0);
        graph.addEdge('D', 'B', 30.0);
        graph.addEdge('D', 'C', 50.0);

        return graph;
    }

    public static FixedSizeGraph<Character> createComplexTraversalCharacterGraph() {
        FixedSizeGraph<Character> graph = createCharacterGraphWithIsolatedNodes();
        graph.addNode('E');

        graph.addEdge('A', 'A', 7.0);
        graph.addEdge('A', 'B', 1.0);
        graph.addEdge('A', 'E', 20.0);
        graph.addEdge('B', 'C', 2.0);
        graph.addEdge('B', 'E', 10.0);
        graph.addEdge('C', 'C', 14.0);
        graph.addEdge('C', 'A', 30.0);
        graph.addEdge('C', 'D', 3.0);
        graph.addEdge('C', 'E', 6.0);
        graph.addEdge('D', 'A', 5.0);
        graph.addEdge('D', 'C', 8.0);
        graph.addEdge('D', 'E', 4.0);

        return graph;
    }

}
