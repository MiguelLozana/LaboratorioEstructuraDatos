package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tree.Tree;

class BSTreeBalanceFactorTests {

	@Test
	public void test() {
		// Its a BST so there is no balancing, BF can be any value.
		Tree<String> tree = new BSTree<String>();

		tree.add("C");
		assertEquals(0, tree.getRoot().getBalanceFactor());

		tree.add("A");
		assertEquals(-1, tree.getRoot().getBalanceFactor());

		tree.add("B");
		assertEquals(-2, tree.getRoot().getBalanceFactor());

		tree.add("E");
		assertEquals(-1, tree.getRoot().getBalanceFactor());

		tree.add("D");
		assertEquals(0, tree.getRoot().getBalanceFactor());

		tree.add("F");
		assertEquals(0, tree.getRoot().getBalanceFactor());

		tree.add("G");
		assertEquals(1, tree.getRoot().getBalanceFactor());

		tree.add("H");
		assertEquals(2, tree.getRoot().getBalanceFactor());

		tree.add("I");
		assertEquals(3, tree.getRoot().getBalanceFactor());

		tree.remove("B");
		assertEquals(4, tree.getRoot().getBalanceFactor());

		tree.remove("A");
		assertEquals(5, tree.getRoot().getBalanceFactor());

		tree.remove("E");
		assertEquals(5, tree.getRoot().getBalanceFactor());

		tree.remove("D");
		assertEquals(4, tree.getRoot().getBalanceFactor());

		tree.remove("F");
		assertEquals(3, tree.getRoot().getBalanceFactor());

		tree.remove("G");
		assertEquals(2, tree.getRoot().getBalanceFactor());
	}

}
