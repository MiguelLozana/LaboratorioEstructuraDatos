package grafos.ejercicios;

import java.util.ArrayList;

import grafos.DijkstraDataClass;
import grafos.FixedSizeGraph;

public class EjericiosGrafosImpl<T> extends FixedSizeGraph<T> implements EjerciciosGrafos<T>{

	public EjericiosGrafosImpl(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getEccentricity(T element) {
		int index = getValidatedIndexOf(element);
		return(getEccentricity(index));
	}
	private double getEccentricity(int element) {
		if(this.getFloydCostsA().equals(null)) {
			floyd();
		}
		
		double max = 0;
		double[] floyCostsA = this.getFloydCostsA()[element];
		
		for ( int i = 0 ; i< this.getSize(); i++) {
			if (floyCostsA[i] > max) {
				max = floyCostsA[i];
			}
		}
		return max;
	}
	@Override
	public T getCentroid() {
		
		if(this.getFloydCostsA().equals(null)) {
			floyd();
		}
		
		double min = Double.POSITIVE_INFINITY;
		T currentCenterNode = null;
		
		
		
		for ( int i = 0 ; i< this.getSize(); i++) {
			T nodeToCheck = this.getNodes()[i];
			double excentricidad = getEccentricity(i);
			if (excentricidad > min)
				min = excentricidad;
				currentCenterNode = nodeToCheck;
				
			
		}
		return currentCenterNode;
	}

	@Override
	public String printDijkstraPath(T originElement, T destinationElement) {
		DijkstraDataClass dj =  dijkstra(originElement);
		int[] path = dj.getDijkstraPathsP();
		
		int dest = getValidatedIndexOf(destinationElement); 
		int origen = dj.getNodeIndex();
		
		return printDijkstraPathRecursive( origen,dest,path);
	}
	public String printDijkstraPathRecursive(int originElement, int destinationElement,int[] path) {
		if (originElement == destinationElement) {
			return this.getNodes()[originElement] + "";
		}
		int pivote = path[destinationElement];
		
		return printDijkstraPathRecursive(originElement,pivote,path) + "-" +this.getNodes()[destinationElement];
		
		
	}
	
	
	
	public String printDijkstraPath2(T originElement, T destinationElement) {
		int dest = getValidatedIndexOf(destinationElement);
		DijkstraDataClass dj =  dijkstra(originElement);
		int origen = dj.getNodeIndex();
		
		String camino = destinationElement.toString();
		int primerNodo = dest;
		
		int predecesor = dest;
		while(predecesor != origen) {
			predecesor = dj.getDijkstraPathsPValue(predecesor);
			
			camino = getNodes()[predecesor].toString() + getWeights()[predecesor][primerNodo] + camino + "";
			primerNodo = predecesor;

		}
		
		return null;
	}
	@Override
	public double getCostBalance(T element) {
		double balance = 0;

		int baseIndex = getValidatedIndexOf(element);
		for (int i=0; i<this.getSize(); i++) {
			if (getEdges()[i][baseIndex]) {
				balance += getWeights()[i][baseIndex];
			}
			if (getEdges()[baseIndex][i]) {
				balance -= getWeights()[baseIndex][i];
			}
		}
		return balance;
	}
	
	/** * Obtiene el coste mínimo del camino desde el origen al destino usando el algoritmo de Dijkstra. 
	 * @param originElement El nodo de inicio 
	 * @param destinationElement El nodo de destino 
	 * @return El coste mínimo, o INFINITO si no existe camino 
	 * @throws NullPointerException si alguno de los elementos es null 
	 * @throws ElementNotPresentException si alguno de los nodos no existe en el grafo 
	 */ 
	public double minimumCostPathDijkstra(T origen, T destino) {
		int dest = getValidatedIndexOf(destino); 
		DijkstraDataClass dj =  dijkstra(origen);
		
		return dj.getDijkstraCostsDValue(dest);
		
	}
	/***Devuelve la longitud del camino más corto entre un nodo origen y un nodo destino. 
	 * @param originElement El nodo de inicio 
	 * @param destinationElement El nodo de destino 
	 * @return longitud del camino más corto 
	 * @throws NullPointerException si alguno de los elementos es null 
	 * @throws ElementNotPresentException si alguno de los nodos no existe en el grafo */ 
	public double shortestPathLength (T origenElement, T destino) {
		DijkstraDataClass dj =  dijkstra(origenElement);
		int[] path = dj.getDijkstraPathsP();
		
		int dest = getValidatedIndexOf(destino); 
		int origen = dj.getNodeIndex();
		double counter = 0;
		
		return shortestPathLengthRecursive( origen,dest,path,counter);
	}
	public double shortestPathLengthRecursive(int originElement, int destinationElement,int[] path,double counter) {
		if (originElement == destinationElement) {
			return counter;
		}
		int pivote = path[destinationElement];
		
		return shortestPathLengthRecursive(originElement,pivote,path,counter+1);
		
		
	}
	private ArrayList<int[]> Prim(T node) {
		int graphSize = this.getSize();
		ArrayList<Integer> nodosUsados = new ArrayList<Integer>();
		boolean[] visitados = new boolean[this.getSize()];
		ArrayList<int[]> aristas = new ArrayList<>();
		
		
		int nodeIndex = getValidatedIndexOf(node);
		while (nodosUsados.size() != graphSize) {
			double min = Double.POSITIVE_INFINITY;
			int nodeToGo = -1;
			for(Integer nodeIndex: nodosUsados) {
				if (nodeIndex)
			}
		}
		
		
		return aristas;
	}
	
}
