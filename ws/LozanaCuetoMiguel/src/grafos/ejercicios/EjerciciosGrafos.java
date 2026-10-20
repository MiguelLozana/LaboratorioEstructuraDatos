package grafos.ejercicios;

import grafos.exceptions.ElementNotPresentException;

/**
 * Ejercicios de la última clase de grafos. No forman parte de la entrega.
 *
 * @param <T> Tipo de los elementos almacenados en los nodos del grafo
 */
public interface EjerciciosGrafos<T> {

	/**
	 * Devuelve la excentricidad de un nodo.
	 *
	 * @param element Nodo del que se calcula la excentricidad
	 * @return La excentricidad del nodo, 0 si es el único nodo del grafo, o
	 *         infinito si hay algún nodo al que no se puede llegar desde él
	 * @throws NullPointerException       si el elemento es null
	 * @throws ElementNotPresentException si el nodo no está en el grafo
	 */
	double getEccentricity(T element);

	/**
	 * Devuelve el centro del grafo. Si varios nodos empatan, se devuelve el de menor índice.
	 *
	 * @return El centro del grafo, o null si el grafo está vacío o ningún nodo
	 *         llega a todos los demás.
	 */
	T getCentroid();

	/**
	 * Devuelve el camino mínimo entre dos nodos calculado con Dijkstra, con el
	 * mismo formato que printFloydPath.
	 *
	 * @param originElement      Nodo origen
	 * @param destinationElement Nodo destino
	 * @return El camino mínimo del origen al destino
	 * @throws NullPointerException       si alguno de los elementos es null
	 * @throws ElementNotPresentException si alguno de los nodos no está en el
	 *                                    grafo
	 */
	String printDijkstraPath(T originElement, T destinationElement);

	/**
	 * Devuelve el balance de coste de un nodo.
	 *
	 * @param element Nodo del que se calcula el balance
	 * @return El balance de coste del nodo
	 * @throws NullPointerException       si el elemento es null
	 * @throws ElementNotPresentException si el nodo no está en el grafo
	 */
	double getCostBalance(T element);
}
