package grafos;

import grafos.exceptions.ElementNotPresentException;
import grafos.exceptions.FullStructureException;

/**
 * Interface defining a graph data structure with basic operations for nodes and
 * edges and diverse traversal methods. Implementations of this interface
 * represent a directed weighted graph.
 *
 * @param <T> The type of elements stored in the graph
 */
public interface Graph<T> {

    /**
     * Adds a node to the graph.
     *
     * @param element The element to add as a node
     * @return {@code true} if the node was successfully added, {@code false} if the
     * node already exists
     * @throws NullPointerException   if the element is null
     * @throws FullStructureException if the graph has reached its maximum capacity
     */
    boolean addNode(T element);

    /**
     * Removes a node from the graph. Removing a node also removes all edges
     * connected to it.
     *
     * @param element The element to remove
     * @return {@code true} if the node was successfully removed, {@code false} if
     * the node doesn't exist
     * @throws NullPointerException if the element is null
     */
    boolean removeNode(T element);

    /**
     * Checks if a node exists in the graph.
     *
     * @param element The element to check
     * @return {@code true} if the node exists, {@code false} otherwise
     * @throws NullPointerException if the element is null
     */
    boolean existsNode(T element);

    /**
     * Adds a directed edge with a specified weight between two nodes.
     *
     * @param originElement      The origin node of the edge
     * @param destinationElement The destination node of the edge
     * @param weight             The weight of the edge
     * @return {@code true} if the edge was successfully added, {@code false} if the
     * edge already exists
     * @throws NullPointerException       if either element is null
     * @throws IllegalArgumentException   if the weight is less than or equal to 0
     * @throws ElementNotPresentException if either node doesn't exist in the graph
     */
    boolean addEdge(T originElement, T destinationElement, double weight);

    /**
     * Removes a directed edge between two nodes.
     *
     * @param originElement      The origin node of the edge
     * @param destinationElement The destination node of the edge
     * @return {@code true} if the edge was successfully removed, {@code false} if
     * the edge doesn't exist
     * @throws NullPointerException       if either element is null
     * @throws ElementNotPresentException if either node doesn't exist in the graph
     */
    boolean removeEdge(T originElement, T destinationElement);

    /**
     * Checks if a directed edge exists between two nodes.
     *
     * @param originElement      The origin node of the edge
     * @param destinationElement The destination node of the edge
     * @return {@code true} if the edge exists, {@code false} otherwise
     * @throws NullPointerException if either element is null
     */
    boolean existsEdge(T originElement, T destinationElement);

    /**
     * Gets the weight of a directed edge between two nodes.
     *
     * @param originElement      The origin node of the edge
     * @param destinationElement The destination node of the edge
     * @return The weight of the edge, or a negative value if the edge doesn't exist
     * @throws NullPointerException       if either element is null
     * @throws ElementNotPresentException if either node doesn't exist in the graph
     */
    double getWeight(T originElement, T destinationElement);

    /**
     * Gets the current number of nodes in the graph.
     *
     * @return The number of nodes
     */
    int getSize();

    /**
     * Executes Dijkstra's algorithm to find the shortest paths from a source vertex
     * to all other vertices.
     *
     * @param originElement The source vertex from which to calculate paths
     * @return A DijkstraDataClass object containing the calculated paths and costs
     * @throws NullPointerException       if startingElement is null
     * @throws ElementNotPresentException if startingElement doesn't exist in the
     *                                    graph
     */
    DijkstraDataClass dijkstra(T originElement);

    /**
     * Gets the minimum cost of the path from origin to destination using Dijkstra's
     * algorithm.
     *
     * @param originElement      The starting node
     * @param destinationElement The ending node
     * @return The minimum cost, or INFINITY if no path exists
     * @throws NullPointerException       if either element is null
     * @throws ElementNotPresentException if either node doesn't exist in the graph
     */
    double minimumCostPathDijkstra(T originElement, T destinationElement);
}
