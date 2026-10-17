package grafos.propios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grafos.FixedSizeGraph;
import grafos.Graph;

class AddTest {
	
	
	/*
	 * Nodo ya anadido
	 * devuelve false pero no modifica atributos
	 */
	@Test
	void AddExistingNode() {
		FixedSizeGraph<Character> g = new FixedSizeGraph<Character>(6);
		char node =  'A';
		g.addNode(node);
        g.addNode('B');
        g.addNode('C');
        g.addNode('D');
        
        Character[]prevNodes = g.getNodes();
        int prevSize = g.getSize();
        
        g.addNode(node);
        
        assertEquals(prevNodes, g.getNodes());
        assertEquals(prevSize, g.getSize());
        
	}
	
	/*
	 * Nodo ya anadido
	 * devuelve false pero no modifica atributos
	 */
	@Test
	void test() {
		FixedSizeGraph<Character> g = new FixedSizeGraph<Character>(6);
		
		g.addNode('A');
        g.addNode('B');
        g.addNode('C');
        g.addNode('D');
        
        Character[]prevNodes = g.getNodes();
        int prevSize = g.getSize();
        
        g.addNode('A');
        
        assertEquals(prevNodes, g.getNodes());
        assertEquals(prevSize, g.getSize());
        
	}

}
