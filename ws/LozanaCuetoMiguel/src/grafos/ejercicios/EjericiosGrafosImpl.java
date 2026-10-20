package grafos.ejercicios;

import grafos.DijkstraDataClass;
import grafos.FixedSizeGraph;

public class EjericiosGrafosImpl<T> extends FixedSizeGraph<T> implements EjerciciosGrafos<T>{

	public EjericiosGrafosImpl(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getEccentricity(T element) {
		int index = getValidatedIndexOd(element);
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
		
		int dest = getValidatedIndexOd(destinationElement); 
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
		int dest = getValidatedIndexOd(destinationElement);
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
		double balance = 0;;

		int baseIndex = getValidatedIndexOd(element);
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

}
