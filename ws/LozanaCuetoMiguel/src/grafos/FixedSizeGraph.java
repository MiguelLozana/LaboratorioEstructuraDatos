package grafos;

import grafos.exceptions.ElementNotPresentException;
import grafos.exceptions.FullStructureException;

public class FixedSizeGraph<T> implements Graph<T> {
	
	
	
	private T[] nodes;
	private boolean[][] edges; // Matriz de adyacencia
	private double[][] weights; // Matriz de pesos 
	private int size;
	

	// ===================================
	//
	//  CONSTRUCTOR
	//
	// ===================================
	
	@SuppressWarnings("unchecked") // le quita el warning en el casteo 
	public FixedSizeGraph( int capacity) {
		checkParam(capacity > 0, "La capacidad ha de ser >0");
		
		nodes = (T[]) new Object[capacity];
		edges = new boolean[capacity][capacity];
		weights = new double[capacity][capacity];
		size = 0;
	}
	
	
	
	// ===================================
	//
	//  ANADIR
	//
	// ===================================
		
	
	@Override
	public boolean addNode(T element) {
		checkNull(element);
		if (this.size == nodes.length) {
			throw new FullStructureException("Nodes ha llegado a su maxima capacidad, no puede anadir");
		}
		
		if (find(element) == -1) {//Si no encuetra el nodo, lo anade
			nodes[size] = element;
		
			for (int i = 0; i < size; i++) {
				weights[size][i] = 0;
				weights[i][size] = 0;
			
				edges[size][i] = false;
				edges[i][size]  = false;
			}	
			weights[size][size] = 0;
			edges[size][size] = false;
			size++;
			return true; 
		}
		return false;
	}
	
	
	@Override
	public boolean addEdge(T originElement, T destinationElement, double weight) {
		checkParam(weight >0 , "El peso ha de ser > 0 ");
		
		int originIndex = getValidatedIndexOd(originElement);
		int destinationIndex = getValidatedIndexOd(destinationElement);
		
		if (!this.edges[originIndex][destinationIndex]) {  //Si no hay arista
			
			this.edges[originIndex][destinationIndex] = true;
			this.weights[originIndex][destinationIndex] = weight;
			return true;	
		}
		return false;
	}
	
	// ===================================
	//
	//  ELIMINAR
	//
	// ===================================
	
	@Override
	public boolean removeNode(T element) {
		checkNull(element);
		int nodeIndex = find(element);
		
		if (nodeIndex == -1){
			return false;
		}
		nodes[nodeIndex] = nodes[--size];
	    for (int i = 0; i < size; i++) {        
	        edges[nodeIndex][i] = edges[size][i];
	        edges[i][nodeIndex] = edges[i][size];
	            
	        weights[nodeIndex][i] = weights[size][i];
	        weights[i][nodeIndex] = weights[i][size];
	    }
	    edges[nodeIndex][nodeIndex] = edges[size][size];
	    weights[nodeIndex][nodeIndex] = weights[size][size];
	    
		return true;
	}

	@Override
	public boolean removeEdge(T originElement, T destinationElement) {
		
		int originIndex = getValidatedIndexOd(originElement);
		int destinationIndex = getValidatedIndexOd(destinationElement);
		
		if (!edges[originIndex][destinationIndex]) {
			return false;
		}
		edges[originIndex][destinationIndex] = false;
		return true;
	}
	// ===================================
	//
	//  COMPORBAR EXISTENCIA y BUSCAR
	//
	// ===================================
	@Override
	public boolean existsNode(T element) {
		checkNull(element);
		
		return find(element) != -1;
	}

	@Override
	public boolean existsEdge(T originElement, T destinationElement) {
		checkNull(originElement);
		checkNull(destinationElement);
		
		int originIndex = find(originElement);
		int destinationIndex = find(destinationElement);
		
		if (originIndex == -1 || destinationIndex == -1){
			return false;
		}
	
		return this.edges[originIndex][destinationIndex];
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
	// ===================================
	//
	//  OBTENER DATOS
	//
	// ===================================
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
	// ===================================
	//
	//  GETTERS
	//
	// ===================================
		
	
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
	
	// ===================================
	//
	//  METODOS AUXILIARES
	//
	// ===================================
	private void checkParam(boolean neededCondition, String msg) {
		if (!neededCondition) {
			throw new IllegalArgumentException(msg);
		}
	}
	private void checkNull(T obj){
		if (obj == null){
			throw new NullPointerException("ELEMETNO NO VALIDO: NULL");
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
	
	
	
	/*
	 * Escoje el siguente pivote. Ha se ser el menor NO USADO 
	 * @param usedNodes Vector de nodos usados. El indice del vector correpode con el indice del nodo
	 * @return int el indice del siguente pivote, -1 en caso de que no halla pivote valido./
	 */
	private int getPivot( boolean[] usedNodes,double[] dijkstraCostD){
		int lowerIndex = -1;
		double lower =  Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < size; i++) {
			if (!usedNodes[i] && dijkstraCostD[i] < lower) {
				lowerIndex = i; 
				lower = dijkstraCostD[i]; 
			}
		}
		return lowerIndex;
		

	}
	// ===================================
	//
	//  METODOS TEORIA
	//
	// ===================================



	@Override
	public DijkstraDataClass dijkstra(T originElement) {
		
		int originIndex = getValidatedIndexOd(originElement);
		DijkstraDataClass dataClass = new DijkstraDataClass(size,this.edges,this.weights,originIndex);
		boolean[] visited = new boolean[size];
		visited[originIndex] = true;
		
		int pivot = getPivot(visited,dataClass.getDijkstraCostsD());
		
		while (pivot  != -1) {
			visited[pivot] = true;
			
			for (int i = 0; i < size ; i++) {
				double costByPivot = dataClass.getDijkstraCostsDValue(pivot) + weights[pivot][i];
				if (!visited[i] && edges[pivot][i] && (costByPivot < dataClass.getDijkstraCostsDValue(i))) {
					dataClass.updateDijkstraCostsD(i, costByPivot);
					dataClass.updateDijkstraPathsP(i, pivot);
				}
			}
			pivot = getPivot(visited, dataClass.getDijkstraCostsD());
		}
		return dataClass;
	}



	@Override
	public double minimumCostPathDijkstra(T originElement, T destinationElement) {
		int destination = getValidatedIndexOd(destinationElement);
		return dijkstra(originElement).getDijkstraCostsDValue(destination);
	}
	// CALCULAR COSTE MINIMIMO
}
