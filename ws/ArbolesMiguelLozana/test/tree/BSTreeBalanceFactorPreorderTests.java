package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tree.NodeFormat;
import tree.Tree;

class BSTreeBalanceFactorPreorderTests {

	final static Integer N = null; // Just for making arrays easier to read

	private static <T extends Comparable<T>> void testPreorder(Tree<T> tree, T[] expectedPreorderArray,
			Integer[] expectedHeightsArray, Integer[] expectedBalanceFactorsArray) {
		String expectedString = TreeTestHelper.toStringPreorderArray(expectedPreorderArray, expectedHeightsArray,
				expectedBalanceFactorsArray, NodeFormat.HEIGHT_AND_BALANCE_FACTOR);
		assertEquals(expectedString, tree.preorderTraversalHeightAndBalanceFactor());
	}

	@Test
	void preorderBalanceFactorsBalanced() {
		Tree<Integer> tree = new BSTree<Integer>();

		tree.add(6);
		Integer[] elements0 = { 6, N, N };
		Integer[] heights0 = { 0, N, N };
		Integer[] balanceFactors0 = { 0, N, N };
		testPreorder(tree, elements0, heights0, balanceFactors0);

		tree.add(15);
		tree.add(3);
		tree.add(9);
		tree.add(14);
		tree.add(20);
		Integer[] elements1 = { 6, 3, N, N, 15, 9, N, 14, N, N, 20, N, N };
		Integer[] heights1 = { 3, 0, N, N, 2, 1, N, 0, N, N, 0, N, N };
		Integer[] balanceFactors1 = { 2, 0, N, N, -1, 1, N, 0, N, N, 0, N, N };
		testPreorder(tree, elements1, heights1, balanceFactors1);

		tree.add(2);
		tree.add(4);
		tree.add(7);
		tree.add(12);
		Integer[] elements2 = { 6, 3, 2, N, N, 4, N, N, 15, 9, 7, N, N, 14, 12, N, N, N, 20, N, N };
		Integer[] heights2 = { 4, 1, 0, N, N, 0, N, N, 3, 2, 0, N, N, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors2 = { 2, 0, 0, N, N, 0, N, N, -2, 1, 0, N, N, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements2, heights2, balanceFactors2);

		tree.add(1);
		Integer[] elements3 = { 6, 3, 2, 1, N, N, N, 4, N, N, 15, 9, 7, N, N, 14, 12, N, N, N, 20, N, N };
		Integer[] heights3 = { 4, 2, 1, 0, N, N, N, 0, N, N, 3, 2, 0, N, N, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors3 = { 1, -1, -1, 0, N, N, N, 0, N, N, -2, 1, 0, N, N, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements3, heights3, balanceFactors3);

		tree.remove(20);
		Integer[] elements4 = { 6, 3, 2, 1, N, N, N, 4, N, N, 15, 9, 7, N, N, 14, 12, N, N, N, N };
		Integer[] heights4 = { 4, 2, 1, 0, N, N, N, 0, N, N, 3, 2, 0, N, N, 1, 0, N, N, N, N };
		Integer[] balanceFactors4 = { 1, -1, -1, 0, N, N, N, 0, N, N, -3, 1, 0, N, N, -1, 0, N, N, N, N };
		testPreorder(tree, elements4, heights4, balanceFactors4);

		tree.remove(4);
		Integer[] elements5 = { 6, 3, 2, 1, N, N, N, N, 15, 9, 7, N, N, 14, 12, N, N, N, N };
		Integer[] heights5 = { 4, 2, 1, 0, N, N, N, N, 3, 2, 0, N, N, 1, 0, N, N, N, N };
		Integer[] balanceFactors5 = { 1, -2, -1, 0, N, N, N, N, -3, 1, 0, N, N, -1, 0, N, N, N, N };
		testPreorder(tree, elements5, heights5, balanceFactors5);

		tree.remove(9);
		Integer[] elements6 = { 6, 3, 2, 1, N, N, N, N, 15, 7, N, 14, 12, N, N, N, N };
		Integer[] heights6 = { 4, 2, 1, 0, N, N, N, N, 3, 2, N, 1, 0, N, N, N, N };
		Integer[] balanceFactors6 = { 1, -2, -1, 0, N, N, N, N, -3, 2, N, -1, 0, N, N, N, N };
		testPreorder(tree, elements6, heights6, balanceFactors6);

		tree.remove(6);
		Integer[] elements7 = { 3, 2, 1, N, N, N, 15, 7, N, 14, 12, N, N, N, N };
		Integer[] heights7 = { 4, 1, 0, N, N, N, 3, 2, N, 1, 0, N, N, N, N };
		Integer[] balanceFactors7 = { 2, -1, 0, N, N, N, -3, 2, N, -1, 0, N, N, N, N };
		testPreorder(tree, elements7, heights7, balanceFactors7);

		tree.remove(3);
		Integer[] elements8 = { 2, 1, N, N, 15, 7, N, 14, 12, N, N, N, N };
		Integer[] heights8 = { 4, 0, N, N, 3, 2, N, 1, 0, N, N, N, N };
		Integer[] balanceFactors8 = { 3, 0, N, N, -3, 2, N, -1, 0, N, N, N, N };
		testPreorder(tree, elements8, heights8, balanceFactors8);
	}

	@Test
	void preorderBalanceFactorsUnbalanced() {
		Tree<Integer> tree = new BSTree<Integer>();

		tree.add(1);
		Integer[] elements1 = { 1, N, N };
		Integer[] heights1 = { 0, N, N };
		Integer[] balanceFactors1 = { 0, N, N };
		testPreorder(tree, elements1, heights1, balanceFactors1);

		tree.add(2);
		Integer[] elements2 = { 1, N, 2, N, N };
		Integer[] heights2 = { 1, N, 0, N, N };
		Integer[] balanceFactors2 = { 1, N, 0, N, N };
		testPreorder(tree, elements2, heights2, balanceFactors2);

		tree.add(3);
		Integer[] elements3 = { 1, N, 2, N, 3, N, N };
		Integer[] heights3 = { 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors3 = { 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements3, heights3, balanceFactors3);

		tree.add(4);
		Integer[] elements4 = { 1, N, 2, N, 3, N, 4, N, N };
		Integer[] heights4 = { 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors4 = { 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements4, heights4, balanceFactors4);

		tree.add(5);
		Integer[] elements5 = { 1, N, 2, N, 3, N, 4, N, 5, N, N };
		Integer[] heights5 = { 4, N, 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors5 = { 4, N, 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements5, heights5, balanceFactors5);

		tree.add(6);
		Integer[] elements6 = { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, N };
		Integer[] heights6 = { 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors6 = { 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements6, heights6, balanceFactors6);

		tree.add(10);
		Integer[] elements7 = { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 10, N, N };
		Integer[] heights7 = { 6, N, 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors7 = { 6, N, 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements7, heights7, balanceFactors7);

		tree.add(11);
		Integer[] elements8 = { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 10, N, 11, N, N };
		Integer[] heights8 = { 7, N, 6, N, 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors8 = { 7, N, 6, N, 5, N, 4, N, 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements8, heights8, balanceFactors8);

		tree.add(8);
		Integer[] elements9 = { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 10, 8, N, N, 11, N, N };
		Integer[] heights9 = { 7, N, 6, N, 5, N, 4, N, 3, N, 2, N, 1, 0, N, N, 0, N, N };
		Integer[] balanceFactors9 = { 7, N, 6, N, 5, N, 4, N, 3, N, 2, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements9, heights9, balanceFactors9);

		tree.add(7);
		Integer[] elements10 = { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 10, 8, 7, N, N, N, 11, N, N };
		Integer[] heights10 = { 8, N, 7, N, 6, N, 5, N, 4, N, 3, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors10 = { 8, N, 7, N, 6, N, 5, N, 4, N, 3, N, -1, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements10, heights10, balanceFactors10);

		tree.remove(1);
		Integer[] elements11 = { 2, N, 3, N, 4, N, 5, N, 6, N, 10, 8, 7, N, N, N, 11, N, N };
		Integer[] heights11 = { 7, N, 6, N, 5, N, 4, N, 3, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors11 = { 7, N, 6, N, 5, N, 4, N, 3, N, -1, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements11, heights11, balanceFactors11);

		tree.remove(3);
		Integer[] elements12 = { 2, N, 4, N, 5, N, 6, N, 10, 8, 7, N, N, N, 11, N, N };
		Integer[] heights12 = { 6, N, 5, N, 4, N, 3, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors12 = { 6, N, 5, N, 4, N, 3, N, -1, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements12, heights12, balanceFactors12);

		tree.remove(4);
		Integer[] elements13 = { 2, N, 5, N, 6, N, 10, 8, 7, N, N, N, 11, N, N };
		Integer[] heights13 = { 5, N, 4, N, 3, N, 2, 1, 0, N, N, N, 0, N, N };
		Integer[] balanceFactors13 = { 5, N, 4, N, 3, N, -1, -1, 0, N, N, N, 0, N, N };
		testPreorder(tree, elements13, heights13, balanceFactors13);

		tree.remove(7);
		Integer[] elements14 = { 2, N, 5, N, 6, N, 10, 8, N, N, 11, N, N };
		Integer[] heights14 = { 4, N, 3, N, 2, N, 1, 0, N, N, 0, N, N };
		Integer[] balanceFactors14 = { 4, N, 3, N, 2, N, 0, 0, N, N, 0, N, N };
		testPreorder(tree, elements14, heights14, balanceFactors14);

		tree.remove(11);
		Integer[] elements15 = { 2, N, 5, N, 6, N, 10, 8, N, N, N };
		Integer[] heights15 = { 4, N, 3, N, 2, N, 1, 0, N, N, N };
		Integer[] balanceFactors15 = { 4, N, 3, N, 2, N, -1, 0, N, N, N };
		testPreorder(tree, elements15, heights15, balanceFactors15);

		tree.remove(10);
		Integer[] elements16 = { 2, N, 5, N, 6, N, 8, N, N };
		Integer[] heights16 = { 3, N, 2, N, 1, N, 0, N, N };
		Integer[] balanceFactors16 = { 3, N, 2, N, 1, N, 0, N, N };
		testPreorder(tree, elements16, heights16, balanceFactors16);
	}

}
