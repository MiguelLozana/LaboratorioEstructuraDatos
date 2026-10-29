package arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTreeAdditionTests {
	
	final static Integer N = null; // Just for making arrays easier to read

	private static <T extends Comparable<T>> void testPreorder(Tree<T> tree, T[] expectedPreorderArray) {
		String expectedString = TreeTestHelper.toStringPreorderArray(expectedPreorderArray, NodeFormat.BASIC);
		assertEquals(expectedString, tree.preorderTraversal());
	}

	@Test
	void addLeftDegenerateTree() {

		Tree<Integer> tree = new BSTTree<Integer>();

		testPreorder(tree, new Integer[] { N });

		tree.add(7);
		testPreorder(tree, new Integer[] { 7, N, N });

		tree.add(6);
		testPreorder(tree, new Integer[] { 7, 6, N, N, N });

		tree.add(5);
		testPreorder(tree, new Integer[] { 7, 6, 5, N, N, N, N });

		tree.add(4);
		testPreorder(tree, new Integer[] { 7, 6, 5, 4, N, N, N, N, N });

		tree.add(3);
		testPreorder(tree, new Integer[] { 7, 6, 5, 4, 3, N, N, N, N, N, N });

		tree.add(2);
		testPreorder(tree, new Integer[] { 7, 6, 5, 4, 3, 2, N, N, N, N, N, N, N });

		tree.add(1);
		testPreorder(tree, new Integer[] { 7, 6, 5, 4, 3, 2, 1, N, N, N, N, N, N, N, N });

	}

	@Test
	void addRightDegenerateTree() {

		Tree<Integer> tree = new BSTTree<Integer>();

		testPreorder(tree, new Integer[] { N });

		tree.add(1);
		testPreorder(tree, new Integer[] { 1, N, N });

		tree.add(2);
		testPreorder(tree, new Integer[] { 1, N, 2, N, N });

		tree.add(3);
		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, N });

		tree.add(4);
		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, 4, N, N });

		tree.add(5);
		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, 4, N, 5, N, N });

		tree.add(6);
		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, N });

		tree.add(7);
		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 7, N, N });

	}

	@Test
	void addBalancedTree() {

		Tree<Integer> tree = new BSTTree<Integer>();

		testPreorder(tree, new Integer[] { N });

		tree.add(4);
		testPreorder(tree, new Integer[] { 4, N, N });

		tree.add(2);
		testPreorder(tree, new Integer[] { 4, 2, N, N, N });

		tree.add(6);
		testPreorder(tree, new Integer[] { 4, 2, N, N, 6, N, N });

		tree.add(1);
		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, N, 6, N, N });

		tree.add(3);
		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, 3, N, N, 6, N, N });

		tree.add(5);
		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, N });

		tree.add(7);
		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 7, N, N });

	}

	@Test
	void addUnbalancedLeft() {

		Tree<Integer> tree = new BSTTree<Integer>();

		tree.add(7);
		testPreorder(tree, new Integer[] { 7, N, N });

		tree.add(3);
		testPreorder(tree, new Integer[] { 7, 3, N, N, N });

		tree.add(5);
		testPreorder(tree, new Integer[] { 7, 3, N, 5, N, N, N });

		tree.add(1);
		testPreorder(tree, new Integer[] { 7, 3, 1, N, N, 5, N, N, N });

		tree.add(2);
		testPreorder(tree, new Integer[] { 7, 3, 1, N, 2, N, N, 5, N, N, N });

		tree.add(6);
		testPreorder(tree, new Integer[] { 7, 3, 1, N, 2, N, N, 5, N, 6, N, N, N });

	}

	@Test
	void addUnbalancedRight() {

		Tree<Integer> tree = new BSTTree<Integer>();

		tree.add(3);
		testPreorder(tree, new Integer[] { 3, N, N });

		tree.add(7);
		testPreorder(tree, new Integer[] { 3, N, 7, N, N });

		tree.add(5);
		testPreorder(tree, new Integer[] { 3, N, 7, 5, N, N, N });

		tree.add(1);
		testPreorder(tree, new Integer[] { 3, 1, N, N, 7, 5, N, N, N });

		tree.add(2);
		testPreorder(tree, new Integer[] { 3, 1, N, 2, N, N, 7, 5, N, N, N });

		tree.add(6);
		testPreorder(tree, new Integer[] { 3, 1, N, 2, N, N, 7, 5, N, 6, N, N, N });
	}

	@Test
	void addCharacterTree() {
		Tree<Character> tree = new BSTTree<Character>();

		tree.add('D');
		testPreorder(tree, new Character[] { 'D', null, null });

		tree.add('B');
		testPreorder(tree, new Character[] { 'D', 'B', null, null, null });

		tree.add('F');
		testPreorder(tree, new Character[] { 'D', 'B', null, null, 'F', null, null });

		tree.add('A');
		testPreorder(tree, new Character[] { 'D', 'B', 'A', null, null, null, 'F', null, null });

		tree.add('C');
		testPreorder(tree, new Character[] { 'D', 'B', 'A', null, null, 'C', null, null, 'F', null, null });

		tree.add('E');
		testPreorder(tree, new Character[] { 'D', 'B', 'A', null, null, 'C', null, null, 'F', 'E', null, null, null });

	}

	@Test
	void addNull() {

		Tree<Integer> tree = new BSTTree<Integer>();

		assertThrows(NullPointerException.class, () -> tree.add(null));

		tree.add(5);
		assertThrows(NullPointerException.class, () -> tree.add(null));

		tree.add(3);
		assertThrows(NullPointerException.class, () -> tree.add(null));

		tree.add(7);
		assertThrows(NullPointerException.class, () -> tree.add(null));

	}

	@Test
	void addRepeated() {

		Tree<Integer> tree = new BSTTree<Integer>();
		tree.add(5);

		assertThrows(IllegalArgumentException.class, () -> tree.add(5));

	}

}
