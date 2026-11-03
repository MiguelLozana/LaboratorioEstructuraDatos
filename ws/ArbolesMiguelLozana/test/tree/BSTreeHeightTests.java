package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTreeHeightTests {

	@Test
	void rootHeightBalancedBranches() {
		Tree<Integer> tree = new BSTree<Integer>();

		tree.add(4);
		assertEquals(0, tree.getRoot().getHeight());

		tree.add(2);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(6);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(1);
		assertEquals(2, tree.getRoot().getHeight());

		tree.add(3);
		assertEquals(2, tree.getRoot().getHeight());

		tree.add(7);
		assertEquals(2, tree.getRoot().getHeight());

	}

	@Test
	void rootHeightUnbalancedLeftBranches() {
		Tree<Integer> tree = new BSTree<Integer>();

		tree.add(4);
		assertEquals(0, tree.getRoot().getHeight());

		tree.add(3);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(6);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(2);
		assertEquals(2, tree.getRoot().getHeight());

		tree.add(1);
		assertEquals(3, tree.getRoot().getHeight());

		tree.add(0);
		assertEquals(4, tree.getRoot().getHeight());
	}

	@Test
	void rootHeightUnbalancedRightBranches() {
		Tree<Integer> tree = new BSTree<Integer>();

		tree.add(4);
		assertEquals(0, tree.getRoot().getHeight());

		tree.add(3);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(5);
		assertEquals(1, tree.getRoot().getHeight());

		tree.add(6);
		assertEquals(2, tree.getRoot().getHeight());

		tree.add(7);
		assertEquals(3, tree.getRoot().getHeight());

		tree.add(8);
		assertEquals(4, tree.getRoot().getHeight());
	}

	@Test
	void testrootHeightAfterAddAndRemove() {
		Tree<Integer> tree = new BSTree<Integer>();

		// Balanced tree
		tree.add(4);
		tree.add(2);
		tree.add(6);
		tree.add(1);
		tree.add(3);
		tree.add(5);
		tree.add(7);
		assertEquals(2, tree.getRoot().getHeight());

		// Remove root node, with two children
		tree.remove(4);
		assertEquals(2, tree.getRoot().getHeight());

		// Remove shorter leaf node, should not change height
		tree.remove(1);
		assertEquals(2, tree.getRoot().getHeight());

		// Remove node with two children
		tree.remove(6);
		assertEquals(2, tree.getRoot().getHeight());

		// Remove node with one child, height should decrease
		tree.remove(5);
		assertEquals(1, tree.getRoot().getHeight());

		// Remove leaf node
		tree.remove(7);
		assertEquals(1, tree.getRoot().getHeight());

		// Remove leaf node, only root will remain
		tree.remove(3);
		assertEquals(0, tree.getRoot().getHeight());

		// Remove the root node finally
		tree.remove(2);
		assertNull(tree.getRoot());
	}

}
