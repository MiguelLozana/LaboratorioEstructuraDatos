package grafos;

import java.util.Arrays;

/**
 * @author Miguel Lozana
 * @version oct-26
 */
public class DijkstraDataClass {
    final static int NO_PREDECESSOR = -1;

    int startingNodeIndex;
    double[] dijkstraCostsD;// Dijkstra's D Cost Vector
    int[] dijkstraPathsP; // Dijkstra's P Paths Vector

    public DijkstraDataClass(int nodeNumber, boolean[][] edges, double[][] weights, int startingNodeIndex) {
    	
    	this.startingNodeIndex = startingNodeIndex;
    	this.dijkstraCostsD = new double[nodeNumber];
    	this.dijkstraPathsP = new int[nodeNumber];
    	
    	for (int i = 0; i < nodeNumber; i++) {
    		if ( i == startingNodeIndex) {
    			dijkstraCostsD[i] = 0;
    			dijkstraPathsP[i] = NO_PREDECESSOR;
    		} else if(edges[startingNodeIndex][i]) {
    			dijkstraCostsD[i] = weights[startingNodeIndex][i]; 
        		dijkstraPathsP[i] = startingNodeIndex;
    		} else {
			dijkstraCostsD[i] = Double.POSITIVE_INFINITY;
			dijkstraPathsP[i] = NO_PREDECESSOR;
		}
    		
		}
        
    }

    public DijkstraDataClass(int nNodes, int index, double[] d, int[] p) {
        startingNodeIndex = index;
        dijkstraCostsD = d;
        dijkstraPathsP = p;
    }

    public int getNodeIndex() {
        return startingNodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.startingNodeIndex = nodeIndex;
    }

    public double[] getDijkstraCostsD() {
        return dijkstraCostsD;
    }

    public void setDijkstraCostsD(double[] dijkstraCostsD) {
        this.dijkstraCostsD = dijkstraCostsD;
    }

    public int[] getDijkstraPathsP() {
        return dijkstraPathsP;
    }

    public void setDijkstraPathsP(int[] dijkstraPathsP) {
        this.dijkstraPathsP = dijkstraPathsP;
    }

    public void updateDijkstraCostsD(int index, double cost) {
        dijkstraCostsD[index] = cost;
    }

    public void updateDijkstraPathsP(int index, int predecessor) {
        dijkstraPathsP[index] = predecessor;
    }

    public double getDijkstraCostsDValue(int index) {
        return dijkstraCostsD[index];
    }

    public int getDijkstraPathsPValue(int index) {
        return dijkstraPathsP[index];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DijkstraDataClass that = (DijkstraDataClass) o;
        return startingNodeIndex == that.startingNodeIndex && Arrays.equals(dijkstraCostsD, that.dijkstraCostsD) && Arrays.equals(dijkstraPathsP, that.dijkstraPathsP);
    }

    @Override
    public String toString() {

        String result = "From " + startingNodeIndex;

        result += "\nTarget node indexes:\t";
        for (int nodeIndex = 0; nodeIndex < dijkstraCostsD.length; nodeIndex++) {
            result += nodeIndex + "\t";
        }

        result += "\nDijkstra Costs D:\t";
        for (double cost : dijkstraCostsD) {
            if (cost == Double.POSITIVE_INFINITY) {
                result += "INF\t";
            } else {
                result += cost + "\t";
            }
        }

        result += "\nDijkstra Paths P:\t";
        for (int path : dijkstraPathsP) {
            if (path == NO_PREDECESSOR) {
                result += "-\t";
            } else {
                result += path + "\t";
            }

        }

        return result;
    }

}
