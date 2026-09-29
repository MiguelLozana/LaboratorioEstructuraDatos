package grafos;

import grafos.exceptions.ElementNotPresentException;

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
	@Override
	public boolean addNode(T element) {
		this.nodes[size] = element;
		
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

	@Override
	public boolean addEdge(T originElement, T destinationElement, double weight) {
		int originIndex = find(originElement);
		int destinationIndex = find(destinationElement);
		
			this.edges[originIndex][destinationIndex] = true;
			this.weights[originIndex][destinationIndex] = weight;
			return true;
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
