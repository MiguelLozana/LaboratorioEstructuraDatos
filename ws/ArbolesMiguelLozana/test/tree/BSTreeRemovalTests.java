package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tree.NodeFormat;
import tree.Tree;

class BSTreeRemovalTests {

	final static Integer N = null; // Just for making arrays easier to read

	private static <T extends Comparable<T>> void testPreorder(Tree<T> tree, T[] expectedPreorderArray) {
		String expectedString = TreeTestHelper.toStringPreorderArray(expectedPreorderArray, NodeFormat.BASIC);
		assertEquals(expectedString, tree.preorderTraversal());
	}

	private static <T extends Comparable<T>> void testRemoveElements(T[] elementsToAdd, T[] elementsToRemove) {
		Tree<T> tree = TreeTestHelper.createBSTree(elementsToAdd);

		for (T element : elementsToAdd) {
			assertTrue(tree.search(element), "Element " + element + " should be found in the tree.");
		}

		for (T element : elementsToRemove) {
			tree.remove(element);
			assertFalse(tree.search(element), "Element " + element + " should NOT be found in the tree after removal.");
		}
	}

	@Test
	void removeLeafNodes() {
		Tree<Integer> tree = TreeTestHelper.createBSTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });

		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 7, N, N });

		tree.remove(1);
		testPreorder(tree, new Integer[] { 4, 2, N, 3, N, N, 6, 5, N, N, 7, N, N });

		tree.remove(3);
		testPreorder(tree, new Integer[] { 4, 2, N, N, 6, 5, N, N, 7, N, N });

		tree.remove(5);
		testPreorder(tree, new Integer[] { 4, 2, N, N, 6, N, 7, N, N });

		tree.remove(7);
		testPreorder(tree, new Integer[] { 4, 2, N, N, 6, N, N });

		tree.remove(2);
		testPreorder(tree, new Integer[] { 4, N, 6, N, N });

		tree.remove(6);
		testPreorder(tree, new Integer[] { 4, N, N });

		tree.remove(4);
		testPreorder(tree, new Integer[] { N });
	}

	@Test
	void removeNodesWithLeftChildOnly() {
		Tree<Integer> tree = TreeTestHelper.createBSTree(new Integer[] { 7, 6, 5, 4, 3, 2, 1 });

		testPreorder(tree, new Integer[] { 7, 6, 5, 4, 3, 2, 1, N, N, N, N, N, N, N, N });

		tree.remove(2);
		testPreorder(tree, new Integer[] { 7, 6, 5, 4, 3, 1, N, N, N, N, N, N, N });

		tree.remove(4);
		testPreorder(tree, new Integer[] { 7, 6, 5, 3, 1, N, N, N, N, N, N });

		tree.remove(6);
		testPreorder(tree, new Integer[] { 7, 5, 3, 1, N, N, N, N, N });

		tree.remove(7);
		testPreorder(tree, new Integer[] { 5, 3, 1, N, N, N, N });

		tree.remove(3);
		testPreorder(tree, new Integer[] { 5, 1, N, N, N });

		tree.remove(5);
		testPreorder(tree, new Integer[] { 1, N, N });
	}

	@Test
	void removeNodesWithRightChildOnly() {
		Tree<Integer> tree = TreeTestHelper.createBSTree(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });

		testPreorder(tree, new Integer[] { 1, N, 2, N, 3, N, 4, N, 5, N, 6, N, 7, N, N });

		tree.remove(2);
		testPreorder(tree, new Integer[] { 1, N, 3, N, 4, N, 5, N, 6, N, 7, N, N });

		tree.remove(4);
		testPreorder(tree, new Integer[] { 1, N, 3, N, 5, N, 6, N, 7, N, N });

		tree.remove(6);
		testPreorder(tree, new Integer[] { 1, N, 3, N, 5, N, 7, N, N });

		tree.remove(1);
		testPreorder(tree, new Integer[] { 3, N, 5, N, 7, N, N });

		tree.remove(5);
		testPreorder(tree, new Integer[] { 3, N, 7, N, N });

		tree.remove(3);
		testPreorder(tree, new Integer[] { 7, N, N });
	}

	@Test
	void removeNodesWithTwoChildren() {
		Tree<Integer> tree = TreeTestHelper.createBSTree(new Integer[] { 4, 2, 6, 1, 3, 5, 7 });

		testPreorder(tree, new Integer[] { 4, 2, 1, N, N, 3, N, N, 6, 5, N, N, 7, N, N });

		tree.remove(4);
		testPreorder(tree, new Integer[] { 3, 2, 1, N, N, N, 6, 5, N, N, 7, N, N });

		tree.remove(6);
		testPreorder(tree, new Integer[] { 3, 2, 1, N, N, N, 5, N, 7, N, N });

		tree.remove(3);
		testPreorder(tree, new Integer[] { 2, 1, N, N, 5, N, 7, N, N });

		tree.remove(2);
		testPreorder(tree, new Integer[] { 1, N, 5, N, 7, N, N });
	}

	@Test
	void removeCharacterTree() {
		Tree<Character> tree = TreeTestHelper.createBSTree(new Character[] { 'd', 'b', 'f', 'a', 'c', 'e', 'g' });

		testPreorder(tree,
				new Character[] { 'd', 'b', 'a', null, null, 'c', null, null, 'f', 'e', null, null, 'g', null, null });

		tree.remove('a');
		testPreorder(tree, new Character[] { 'd', 'b', null, 'c', null, null, 'f', 'e', null, null, 'g', null, null });

		tree.remove('g');
		testPreorder(tree, new Character[] { 'd', 'b', null, 'c', null, null, 'f', 'e', null, null, null });

		tree.remove('c');
		testPreorder(tree, new Character[] { 'd', 'b', null, null, 'f', 'e', null, null, null });

		tree.remove('e');
		testPreorder(tree, new Character[] { 'd', 'b', null, null, 'f', null, null });
	}

	@Test
	void removeInInsertionOrder() {
		Integer[] elementsToAdd = { 4, 2, 6, 1, 3, 5, 7 };
		Integer[] elementsToRemove = { 4, 2, 6, 1, 3, 5, 7 };

		testRemoveElements(elementsToAdd, elementsToRemove);
	}

	@Test
	void removeInReverseInsertionOrder() {
		Integer[] elementsToAdd = { 4, 2, 6, 1, 3, 5, 7 };
		Integer[] elementsToRemove = { 7, 5, 3, 1, 6, 2, 4 };

		testRemoveElements(elementsToAdd, elementsToRemove);
	}

	@Test
	void removeFromEmptyTree() {
		Tree<Integer> tree = new BSTree<Integer>();

		assertThrows(IllegalArgumentException.class, () -> tree.remove(5));
		assertThrows(IllegalArgumentException.class, () -> tree.remove(0));
		assertThrows(IllegalArgumentException.class, () -> tree.remove(-3));
	}

	@Test
	void removeNonExistingElement() {
		Integer[] elementsToAdd = { 4, 2, 6, 1, 3, 5, 7 };
		Tree<Integer> tree = TreeTestHelper.createBSTree(elementsToAdd);

		assertThrows(IllegalArgumentException.class, () -> tree.remove(8));
		assertThrows(IllegalArgumentException.class, () -> tree.remove(0));
		assertThrows(IllegalArgumentException.class, () -> tree.remove(-1));
	}

	@Test
	void removeN() {
		Tree<Integer> tree = new BSTree<Integer>();

		assertThrows(NullPointerException.class, () -> tree.remove(null));

		tree.add(5);
		assertThrows(NullPointerException.class, () -> tree.remove(null));

		tree.add(3);
		assertThrows(NullPointerException.class, () -> tree.remove(null));

		tree.add(7);
		assertThrows(NullPointerException.class, () -> tree.remove(null));
	}
}
