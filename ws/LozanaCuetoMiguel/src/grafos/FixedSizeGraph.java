package grafos;

import grafos.exceptions.ElementNotPresentException;
import grafos.exceptions.FullStructureException;

public class FixedSizeGraph<T> implements Graph<T> {
	private T[] nodes;
	private boolean[][] edges;
	private double[][] weights;
	private int size;
	

	
	
	@SuppressWarnings("unchecked")
	public FixedSizeGraph( int capacity) {
		if (capacity <= 0 )
			throw new IllegalArgumentException("No peude ser menor igual a 0");
		
		nodes = (T[]) new Object[capacity];
		edges = new boolean[capacity][capacity];
		weights = new double[capacity][capacity];
		size = 0;
	}
	
	/*
	 * Recorre el vector de nodos mediante el indice hasta que encuetra y devuevle su indice
	 * @return int indice del elemento o -1 en caso de que no lo encuentre
	 */
	public int find(T element) {
		for (int index = 0; index< this.size; index++) {
			if (nodes[index].equals(element)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
     * Adds a node to the graph.
     *
     * @param element The element to add as a node
     * @return {@code true} if the node was successfully added, {@code false} if the
     * node already exists
     * @throws NullPointerException   if the element is null
     * @throws FullStructureException if the graph has reached its maximum capacity
     */
	@Override
	public boolean addNode(T element) {
		checkNull(element);
		if (this.size == nodes.length) {
			throw new FullStructureException("Nodes ha llegado a su maxima capacidad, no puede anadir");
		}
		
		if (find(element) == -1) {
			this.nodes[size] = element;
			for (int i=0; i>size; i++) {
				edges[i][size] = false;
				edges[size][i] = false;
			}
			size++;
			return true;
		}
		return false;
	}
	@Override
	public boolean removeNode(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsNode(T element) {
		checkNull(element);
		
		return find(element) != -1;
	}
	
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
	@Override
	public boolean addEdge(T originElement, T destinationElement, double weight) {
		if (weight <= 0) {
			throw new IllegalArgumentException("El peso ha de ser positivo");
		}
		
		
		int originIndex = getValidatedIndexOd(originElement);
		int destinationIndex = getValidatedIndexOd(destinationElement);
		
		if (!this.edges[originIndex][destinationIndex]) {
			this.edges[originIndex][destinationIndex] = true;
			this.weights[originIndex][destinationIndex] = weight;
			return true;	
		}
		return false;
	}

	@Override
	public boolean removeEdge(T originElement, T destinationElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsEdge(T originElement, T destinationElement) {
		checkNull(destinationElement);
		checkNull(originElement);
		
		int originIndex = find(originElement);
		int destinationIndex = find(destinationElement);
		
		if (originIndex == -1 || destinationIndex == -1){
			return false;
		}
	
		return this.edges[originIndex][destinationIndex];
	}

	@Override
	public double getWeight(T originElement, T destinationElement) {
		
		int originIndex = getValidatedIndexOd(originElement);
		int destinationIndex = getValidatedIndexOd(destinationElement);
		
		
		if (originIndex == -1 || destinationIndex == -1){
			throw new ElementNotPresentException("ELEMETNO NO ENCONTADO");
		
		}	
			
		if (existsEdge(originElement,destinationElement)) {
			return this.weights[originIndex][destinationIndex];
		}
		
		return -1.0;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	public T[] getNodes() {
		return nodes;
	}

	public boolean[][] getEdges() {
		return edges;
	}

	public double[][] getWeights() {
		return weights;
	}
	public int getCapacity() {
		return nodes.length;
	}
	
	public void checkNull(T obj){
		if (obj == null){
			int a = 1;
		}
	}
	
	public int getValidatedIndexOd(T element) {
		checkNull(element);
		int index = find(element);
		if(index == -1){
			throw new ElementNotPresentException("ELEMETNO NO ENCONTRADO");
		}
		return index;
	}
	
	
}
