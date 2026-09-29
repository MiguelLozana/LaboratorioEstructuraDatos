package grafos;

public class FixedSizeGraphs<T> implements Graph<T> {
	private T[] nodes;
	private boolean[][] edges;
	private double[][] weights;
	private int size;
	

	
	
	@SuppressWarnings("unchecked")
	public FixedSizeGraphs( int capacity) {
		if (capacity <= 0 )
			throw new IllegalArgumentException("No peude ser menor igual a 0");
		
		nodes = (T[]) new Object[capacity];
		edges = new boolean[capacity][capacity];
		weights = new double[capacity][capacity];
		size = 0;
	}

	@Override
	public boolean addNode(T element) {
		return false;
	}

	@Override
	public boolean removeNode(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsNode(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(T originElement, T destinationElement, double weight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(T originElement, T destinationElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsEdge(T originElement, T destinationElement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getWeight(T originElement, T destinationElement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
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
	

	
	
}
