package tree;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AVLTreeComplexRotations {

	final static Integer N = null; // Just for making arrays easier to read

	private static <T extends Comparable<T>> void testPreorder(Tree<T> tree, T[] expectedPreorderArray,
			Integer[] expectedHeightsArray, Integer[] expectedBalanceFactorsArray) {
		String expectedString = TreeTestHelper.toStringPreorderArray(expectedPreorderArray, expectedHeightsArray,
				expectedBalanceFactorsArray, NodeFormat.HEIGHT_AND_BALANCE_FACTOR);
		assertEquals(expectedString, tree.preorderTraversalHeightAndBalanceFactor());
	}

	@Test
	void rotationAfterRemovalWithTwoChildrenLeft() {
		Tree<Integer> tree = TreeTestHelper
				.createAVLTree(new Integer[] { 10, 5, 15, 3, 13, 7, 17, 2, 4, 6, 8, 12, 14, 16, 18 });
		// Balanced tree, no rotation yet
		Integer[] elements0 = { 10, 5, 3, 2, N, N, 4, N, N, 7, 6, N, N, 8, N, N, 15, 13, 12, N, N, 14, N, N, 17, 16, N,
				N, 18, N, N };
		Integer[] heights0 = { 3, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N,
				N };
		Integer[] balances0 = { 0, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0,
				N, N };
		testPreorder(tree, elements0, heights0, balances0);

		tree.remove(10);
		tree.remove(8); // Still no rotation
		Integer[] elements1 = { 7, 5, 3, 2, N, N, 4, N, N, 6, N, N, 15, 13, 12, N, N, 14, N, N, 17, 16, N, N, 18, N,
				N };
		Integer[] heights1 = { 3, 2, 1, 0, N, N, 0, N, N, 0, N, N, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances1 = { 0, -1, 0, 0, N, N, 0, N, N, 0, N, N, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements1, heights1, balances1);

		tree.remove(7); // Triggers rotation
		Integer[] elements2 = { 6, 3, 2, N, N, 5, 4, N, N, N, 15, 13, 12, N, N, 14, N, N, 17, 16, N, N, 18, N, N };
		Integer[] heights2 = { 3, 2, 0, N, N, 1, 0, N, N, N, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances2 = { 0, 1, 0, N, N, -1, 0, N, N, N, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements2, heights2, balances2);
	}

	@Test
	void rotationAfterRemovalWithTwoChildrenRight() {
		Tree<Integer> tree = TreeTestHelper
				.createAVLTree(new Integer[] { 10, 5, 15, 3, 13, 7, 17, 2, 4, 6, 8, 12, 14, 16, 18 });
		// Balanced tree, no rotation yet
		Integer[] elements0 = { 10, 5, 3, 2, N, N, 4, N, N, 7, 6, N, N, 8, N, N, 15, 13, 12, N, N, 14, N, N, 17, 16, N,
				N, 18, N, N };
		Integer[] heights0 = { 3, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N,
				N };
		Integer[] balances0 = { 0, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0,
				N, N };
		testPreorder(tree, elements0, heights0, balances0);

		tree.remove(15);
		tree.remove(14);

		Integer[] elements1 = { 10, 5, 3, 2, N, N, 4, N, N, 7, 6, N, N, 8, N, N, 13, 12, N, N, 17, 16, N, N, 18, N, N };
		Integer[] heights1 = { 3, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N, 2, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances1 = { 0, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N, 1, 0, N, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements1, heights1, balances1);

		tree.remove(13); // Triggers rotation

		Integer[] elements2 = { 10, 5, 3, 2, N, N, 4, N, N, 7, 6, N, N, 8, N, N, 17, 12, N, 16, N, N, 18, N, N };
		Integer[] heights2 = { 3, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N, 2, 1, N, 0, N, N, 0, N, N };
		Integer[] balances2 = { 0, 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N, -1, 1, N, 0, N, N, 0, N, N };
		testPreorder(tree, elements2, heights2, balances2);
	}

	@Test
	void avlPreorderedBalanced() {
		Tree<Integer> tree = TreeTestHelper.createAVLTree(new Integer[] { 10, 6, 15, 3, 9, 14, 20 });
		// Balanced tree

		Integer[] elements1 = { 10, 6, 3, N, N, 9, N, N, 15, 14, N, N, 20, N, N };
		Integer[] heights1 = { 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances1 = { 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements1, heights1, balances1);

		TreeTestHelper.addElementsToTree(tree, new Integer[] { 2, 4, 7, 12 });
		// Still balanced

		Integer[] elements2 = { 10, 6, 3, 2, N, N, 4, N, N, 9, 7, N, N, N, 15, 14, 12, N, N, N, 20, N, N };
		Integer[] heights2 = { 3, 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balances2 = { 0, 0, 0, 0, N, N, 0, N, N, -1, 0, N, N, N, -1, -1, 0, N, N, N, 0, N, N };

		testPreorder(tree, elements2, heights2, balances2);

		tree.add(1); // Still balanced

		Integer[] elements3 = { 10, 6, 3, 2, 1, N, N, N, 4, N, N, 9, 7, N, N, N, 15, 14, 12, N, N, N, 20, N, N };
		Integer[] heights3 = { 4, 3, 2, 1, 0, N, N, N, 0, N, N, 1, 0, N, N, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balances3 = { -1, -1, -1, -1, 0, N, N, N, 0, N, N, -1, 0, N, N, N, -1, -1, 0, N, N, N, 0, N, N };

		testPreorder(tree, elements3, heights3, balances3);

		tree.remove(20); // Triggers rotations

		Integer[] elements4 = { 6, 3, 2, 1, N, N, N, 4, N, N, 10, 9, 7, N, N, N, 14, 12, N, N, 15, N, N };
		Integer[] heights4 = { 3, 2, 1, 0, N, N, N, 0, N, N, 2, 1, 0, N, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances4 = { 0, -1, -1, 0, N, N, N, 0, N, N, 0, -1, 0, N, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements4, heights4, balances4);

		tree.remove(4); // Triggers rotations

		Integer[] elements5 = { 6, 2, 1, N, N, 3, N, N, 10, 9, 7, N, N, N, 14, 12, N, N, 15, N, N };
		Integer[] heights5 = { 3, 1, 0, N, N, 0, N, N, 2, 1, 0, N, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances5 = { 1, 0, 0, N, N, 0, N, N, 0, -1, 0, N, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements5, heights5, balances5);

		tree.remove(10); // No rotation

		Integer[] elements6 = { 6, 2, 1, N, N, 3, N, N, 9, 7, N, N, 14, 12, N, N, 15, N, N };
		Integer[] heights6 = { 3, 1, 0, N, N, 0, N, N, 2, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances6 = { 1, 0, 0, N, N, 0, N, N, 1, 0, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements6, heights6, balances6);

		tree.remove(9); // Triggers rotations

		Integer[] elements7 = { 6, 2, 1, N, N, 3, N, N, 14, 7, N, 12, N, N, 15, N, N };
		Integer[] heights7 = { 3, 1, 0, N, N, 0, N, N, 2, 1, N, 0, N, N, 0, N, N };
		Integer[] balances7 = { 1, 0, 0, N, N, 0, N, N, -1, 1, N, 0, N, N, 0, N, N };

		testPreorder(tree, elements7, heights7, balances7);

		tree.remove(6); // No rotation

		Integer[] elements8 = { 3, 2, 1, N, N, N, 14, 7, N, 12, N, N, 15, N, N };
		Integer[] heights8 = { 3, 1, 0, N, N, N, 2, 1, N, 0, N, N, 0, N, N };
		Integer[] balances8 = { 1, -1, 0, N, N, N, -1, 1, N, 0, N, N, 0, N, N };

		testPreorder(tree, elements8, heights8, balances8);

		tree.remove(3); // Triggers rotations

		Integer[] elements9 = { 7, 2, 1, N, N, N, 14, 12, N, N, 15, N, N };
		Integer[] heights9 = { 2, 1, 0, N, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances9 = { 0, -1, 0, N, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements9, heights9, balances9);
	}

	@Test
	void avlPreorderedUnbalanced() {
		Tree<Integer> tree = new AVLTree<Integer>();

		tree.add(1); // No rotation

		Integer[] elements1 = { 1, N, N };
		Integer[] heights1 = { 0, N, N };
		Integer[] balances1 = { 0, N, N };

		testPreorder(tree, elements1, heights1, balances1);

		tree.add(2);

		Integer[] elements2 = { 1, N, 2, N, N };
		Integer[] heights2 = { 1, N, 0, N, N };
		Integer[] balances2 = { 1, N, 0, N, N };

		testPreorder(tree, elements2, heights2, balances2);

		tree.add(3); // Triggers rotation

		Integer[] elements3 = { 2, 1, N, N, 3, N, N };
		Integer[] heights3 = { 1, 0, N, N, 0, N, N };
		Integer[] balances3 = { 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements3, heights3, balances3);

		tree.add(4); // No rotation

		Integer[] elements4 = { 2, 1, N, N, 3, N, 4, N, N };
		Integer[] heights4 = { 2, 0, N, N, 1, N, 0, N, N };
		Integer[] balances4 = { 1, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements4, heights4, balances4);

		tree.add(5); // Triggers rotation

		Integer[] elements5 = { 2, 1, N, N, 4, 3, N, N, 5, N, N };
		Integer[] heights5 = { 2, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances5 = { 1, 0, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements5, heights5, balances5);

		tree.add(6); // Triggers rotation

		Integer[] elements6 = { 4, 2, 1, N, N, 3, N, N, 5, N, 6, N, N };
		Integer[] heights6 = { 2, 1, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances6 = { 0, 0, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements6, heights6, balances6);

		tree.add(10); // Triggers rotation

		Integer[] elements7 = { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 10, N, N };
		Integer[] heights7 = { 2, 1, 0, N, N, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances7 = { 0, 0, 0, N, N, 0, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements7, heights7, balances7);

		tree.add(11); // No rotation

		Integer[] elements8 = { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 10, N, 11, N, N };
		Integer[] heights8 = { 3, 1, 0, N, N, 0, N, N, 2, 0, N, N, 1, N, 0, N, N };
		Integer[] balances8 = { 1, 0, 0, N, N, 0, N, N, 1, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements8, heights8, balances8);

		tree.add(8); // No rotation

		Integer[] elements9 = { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 10, 8, N, N, 11, N, N };
		Integer[] heights9 = { 3, 1, 0, N, N, 0, N, N, 2, 0, N, N, 1, 0, N, N, 0, N, N };
		Integer[] balances9 = { 1, 0, 0, N, N, 0, N, N, 1, 0, N, N, 0, 0, N, N, 0, N, N };

		testPreorder(tree, elements9, heights9, balances9);

		tree.add(7); // Triggers rotation

		Integer[] elements10 = { 4, 2, 1, N, N, 3, N, N, 8, 6, 5, N, N, 7, N, N, 10, N, 11, N, N };
		Integer[] heights10 = { 3, 1, 0, N, N, 0, N, N, 2, 1, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances10 = { 1, 0, 0, N, N, 0, N, N, 0, 0, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements10, heights10, balances10);

		tree.remove(1); // No rotation

		Integer[] elements11 = { 4, 2, N, 3, N, N, 8, 6, 5, N, N, 7, N, N, 10, N, 11, N, N };
		Integer[] heights11 = { 3, 1, N, 0, N, N, 2, 1, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances11 = { 1, 1, N, 0, N, N, 0, 0, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements11, heights11, balances11);

		tree.remove(3); // Triggers rotation

		Integer[] elements12 = { 8, 4, 2, N, N, 6, 5, N, N, 7, N, N, 10, N, 11, N, N };
		Integer[] heights12 = { 3, 2, 0, N, N, 1, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances12 = { -1, 1, 0, N, N, 0, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements12, heights12, balances12);

		tree.remove(4); // Triggers rotation

		Integer[] elements13 = { 8, 6, 2, N, 5, N, N, 7, N, N, 10, N, 11, N, N };
		Integer[] heights13 = { 3, 2, 1, N, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances13 = { -1, -1, 1, N, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements13, heights13, balances13);

		tree.remove(7); // Triggers rotation

		Integer[] elements14 = { 8, 5, 2, N, N, 6, N, N, 10, N, 11, N, N };
		Integer[] heights14 = { 2, 1, 0, N, N, 0, N, N, 1, N, 0, N, N };
		Integer[] balances14 = { 0, 0, 0, N, N, 0, N, N, 1, N, 0, N, N };

		testPreorder(tree, elements14, heights14, balances14);

		tree.remove(11); // No rotation

		Integer[] elements15 = { 8, 5, 2, N, N, 6, N, N, 10, N, N };
		Integer[] heights15 = { 2, 1, 0, N, N, 0, N, N, 0, N, N };
		Integer[] balances15 = { -1, 0, 0, N, N, 0, N, N, 0, N, N };

		testPreorder(tree, elements15, heights15, balances15);

		tree.remove(10); // Triggers rotation

		Integer[] elements16 = { 5, 2, N, N, 8, 6, N, N, N };
		Integer[] heights16 = { 2, 0, N, N, 1, 0, N, N, N };
		Integer[] balances16 = { 1, 0, N, N, -1, 0, N, N, N };

		testPreorder(tree, elements16, heights16, balances16);
	}

}
