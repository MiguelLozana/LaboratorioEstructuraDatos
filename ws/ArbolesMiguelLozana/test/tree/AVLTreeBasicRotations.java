package tree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AVLTreeBasicRotations {

	final static Integer N = null; // Just for making arrays easier to read

	private static <T extends Comparable<T>> void testPreorder(Tree<T> tree, T[] expectedPreorderArray,
			Integer[] expectedHeightsArray, Integer[] expectedBalanceFactorsArray) {
		String expectedString = TreeTestHelper.toStringPreorderArray(expectedPreorderArray, expectedHeightsArray,
				expectedBalanceFactorsArray, NodeFormat.HEIGHT_AND_BALANCE_FACTOR);
		assertEquals(expectedString, tree.preorderTraversalHeightAndBalanceFactor());
	}

	@Test
	void singleLeftRotation3Nodes() {
		// ....3
		// .../
		// ..2
		// ./
		// 1
		Tree<Integer> tree = new AVLTree<Integer>();

		tree.add(3);
		tree.add(2);
		tree.add(1); // Should trigger single left rotation

		Integer[] elements = { 2, 1, N, N, 3, N, N };
		Integer[] heights = { 1, 0, N, N, 0, N, N };
		Integer[] balances = { 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements, heights, balances);
	}

	@Test
	void singleRightRotation3Nodes() {
		// 1
		// .\
		// ..2
		// ...\
		// ....3
		Tree<Integer> tree = new AVLTree<Integer>();
		tree.add(1);
		tree.add(2);
		tree.add(3); // Should trigger single right rotation
		Integer[] elements = { 2, 1, N, N, 3, N, N };
		Integer[] heights = { 1, 0, N, N, 0, N, N };
		Integer[] balances = { 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements, heights, balances);
	}

	@Test
	void singleLeftRotation4Nodes() {
		// ....4
		// .../
		// ..2
		// ./ \
		// 1...3
		Tree<Integer> tree = TreeTestHelper.createAVLTree(new Integer[] { 4, 2, 6, 1, 7, 3, 5 });
		// Balanced tree, no rotation yet

		Integer[] elements0 = { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 7, N, N };
		Integer[] heights0 = { 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances0 = { 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements0, heights0, balances0);

		tree.remove(7);
		tree.remove(5); // Still balanced, no rotation yet.
		Integer[] elements1 = { 4, 2, 1, N, N, 3, N, N, 6, N, N };
		Integer[] heights1 = { 2, 1, 0, N, N, 0, N, N, 0, N, N };
		Integer[] balances1 = { -1, 0, 0, N, N, 0, N, N, 0, N, N };
		testPreorder(tree, elements1, heights1, balances1);

		tree.remove(6); // This should trigger single left rotation.
		// The rotated part has 4 nodes (1, 2, 3, 4)
		Integer[] elements3 = { 2, 1, N, N, 4, 3, N, N, N };
		Integer[] heights3 = { 2, 0, N, N, 1, 0, N, N, N };
		Integer[] balances3 = { 1, 0, N, N, -1, 0, N, N, N };
		testPreorder(tree, elements3, heights3, balances3);
	}

	@Test
	void singleRightRotation4Nodes() {
		// 4
		// .\
		// ..6
		// ./ \
		// 5...7
		Tree<Integer> tree = TreeTestHelper.createAVLTree(new Integer[] { 4, 2, 6, 1, 7, 3, 5 });
		// Balanced tree, no rotation yet

		Integer[] elements0 = { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 7, N, N };
		Integer[] heights0 = { 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances0 = { 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements0, heights0, balances0);

		tree.remove(1);
		tree.remove(3); // Still balanced, no rotation yet.
		Integer[] elements1 = { 4, 2, N, N, 6, 5, N, N, 7, N, N };
		Integer[] heights1 = { 2, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances1 = { 1, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements1, heights1, balances1);

		tree.remove(2); // This should trigger single right rotation.
		// The rotated part has 4 nodes (4, 5, 6, 7)
		Integer[] elements3 = { 6, 4, N, 5, N, N, 7, N, N };
		Integer[] heights3 = { 2, 1, N, 0, N, N, 0, N, N };
		Integer[] balances3 = { -1, 1, N, 0, N, N, 0, N, N };
		testPreorder(tree, elements3, heights3, balances3);

	}

	@Test
	void doubleLeftRotation() {
		// 1
		// .\
		// ..3
		// ./
		// 2
		Tree<Integer> tree = new AVLTree<Integer>();

		tree.add(1);
		tree.add(3);
		tree.add(2); // Should trigger double left-right rotation

		Integer[] elements = { 2, 1, N, N, 3, N, N };
		Integer[] heights = { 1, 0, N, N, 0, N, N };
		Integer[] balances = { 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements, heights, balances);
	}

	@Test
	void doubleRightRotation() {
		// ..3
		// ./
		// 1
		// .\
		// ..2
		Tree<Integer> tree = new AVLTree<Integer>();

		tree.add(3);
		tree.add(1);
		tree.add(2); // Should trigger double right-left rotation

		Integer[] elements = { 2, 1, N, N, 3, N, N };
		Integer[] heights = { 1, 0, N, N, 0, N, N };
		Integer[] balances = { 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements, heights, balances);
	}

}
