package tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BSTreeMaxNodeTests {

	@Test
	void testGetMax() {
		BSTree<Character> a = new BSTree<Character>();

		a.add('b');
		assertEquals('b', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('a');
		assertEquals('b', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('d');
		assertEquals('d', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('c');
		assertEquals('d', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('g');
		assertEquals('g', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('i');
		assertEquals('i', (char) a.getMaxNode(a.getRoot()).getElement());
		a.add('h');
		assertEquals('i', (char) a.getMaxNode(a.getRoot()).getElement());
	}

}
