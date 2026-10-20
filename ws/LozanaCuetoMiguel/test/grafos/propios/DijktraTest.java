package grafos.propios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grafos.FixedSizeGraph;

class DijktraTest {
    private final double INF = Double.POSITIVE_INFINITY;
	@Test
	void unreachableMinimumCost() {
		FixedSizeGraph<Character> g = new FixedSizeGraph<Character>(6);
		g.addNode('A');
        g.addNode('B');
        g.addNode('C');
        g.addNode('D');
        assertEquals(INF, g.minimumCostPathDijkstra('A', 'D'));
	}
}
