package grafos.propios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grafos.FixedSizeGraph;

class ConstructorsTests {

	@Test
	void constructorFourParamsInvalid() {
		int prevSize = -1;
		Character[] prevNodes = null;
		boolean[][] prevEdges = null;
		double[][]prevWeight = null;
		
		FixedSizeGraph<Character> g = new FixedSizeGraph(prevSize,prevNodes,prevEdges,prevWeight);
		assertEquals(prevSize, g.getSize());
		assertEquals(prevNodes, g.getNodes());
		assertEquals(prevEdges, g.getEdges());
		assertEquals(prevWeight, g.getWeights());
		
	}
	@Test
	void constructorFourParamsValid() {
		int prevSize = 1;
		Character[] prevNodes = new Character[]{'A'};
		boolean[][] prevEdges = new boolean[][]{{false}};
		double[][]prevWeight = new double[][]{{1.2}};;
		
		FixedSizeGraph<Character> g = new FixedSizeGraph(prevSize,prevNodes,prevEdges,prevWeight);
		assertEquals(prevSize, g.getSize());
		assertEquals(prevNodes, g.getNodes());
		assertEquals(prevEdges, g.getEdges());
		assertEquals(prevWeight, g.getWeights());
		
	}
	
	@Test
	void constructorSixParamsInvalid() {
		int prevSize = -1;
		Character[] prevNodes = null;
		boolean[][] prevEdges = null;
		double[][]prevWeight = null;
		double[][]floydCostsA = null;
		int[][] floydPathsP = null;

		
		FixedSizeGraph<Character> g = new FixedSizeGraph(prevSize,prevNodes,prevEdges,prevWeight);
		assertEquals(prevSize, g.getSize());
		assertEquals(prevNodes, g.getNodes());
		assertEquals(prevEdges, g.getEdges());
		assertEquals(prevWeight, g.getWeights());
		assertEquals(floydCostsA, g.getFloydCostsA());
		assertEquals(floydPathsP, g.getFloydPathsP());
	}
	@Test
	void constructorSixParamsValid() {
		int prevSize = 1;
		Character[] prevNodes = new Character[]{'A','B','C'};
		boolean[][] prevEdges = new boolean[][]{{false,false,false},{false,false,false},{false,false,false}};
		double[][]prevWeight = new double[][]{{1.2,2,23},{1.2,2,23},{1.2,2,23}};
		
		double[][]floydCostsA = new double[][]{{1.12,112,213},{1.12,12,213},{1.211,211,2311}};
		int[][] floydPathsP = new int[][]{{-1,-1,-1},{-1,-1,-1},{-1,-1,-1}};

		
		FixedSizeGraph<Character> g = new FixedSizeGraph(prevSize,prevNodes,prevEdges,prevWeight,floydCostsA,floydPathsP);
		assertEquals(prevSize, g.getSize());
		assertEquals(prevNodes, g.getNodes());
		assertEquals(prevEdges, g.getEdges());
		assertEquals(prevWeight, g.getWeights());
		assertEquals(floydCostsA, g.getFloydCostsA());
		assertEquals(floydPathsP, g.getFloydPathsP());
		
		
	}

}
