package grafos.propios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import grafos.FixedSizeGraph;
import grafos.exceptions.ElementNotPresentException;

class TrasversalTest {

	
	@Test
	void noExistDestination() {
		FixedSizeGraph<Character> g = new FixedSizeGraph<Character>(6);
		g.addNode('A');
        g.addNode('B');
        g.addNode('C');
        g.addNode('D');
        assertThrows(ElementNotPresentException.class,() -> g.printDepthFirstTraversal('Z'));
        
	}
}
