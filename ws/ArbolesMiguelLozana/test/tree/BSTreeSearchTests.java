package tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tree.BSTree;
import tree.Tree;

class BSTreeSearchTests {

	private static <T extends Comparable<T>> void testSearchForElements(T[] elementsToAdd, T[] nonExistingElements) {
		Tree<T> tree = TreeTestHelper.createBSTree(elementsToAdd);

		for (T element : elementsToAdd) {
			assertTrue(tree.search(element), "Element " + element + " should be found in the tree.");
		}

		for (T element : nonExistingElements) {
			assertFalse(tree.search(element), "Element " + element + " should NOT be found in the tree.");
		}
	}

	@Test
	void searchLeftDegenerateTree() {
		Integer[] elementsToAdd = { 7, 6, 5, 4, 3, 2, 1 };
		Integer[] nonExistingElements = { -1, 0, 8, 9, 10, 11, 12 };

		testSearchForElements(elementsToAdd, nonExistingElements);
	}

	@Test
	void searchRightDegenerateTree() {
		Integer[] elementsToAdd = { 1, 2, 3, 4, 5, 6, 7 };
		Integer[] nonExistingElements = { -1, 0, 8, 9, 10, 11, 12 };

		testSearchForElements(elementsToAdd, nonExistingElements);

	}

	@Test
	void searchBalancedTree() {
		Integer[] elementsToAdd = { 4, 2, 6, 1, 3, 5, 7 };
		Integer[] nonExistingElements = { -1, 0, 8, 9, 10, 11, 12 };

		testSearchForElements(elementsToAdd, nonExistingElements);
	}

	@Test
	void searchUnbalancedLeft() {
		Integer[] elementsToAdd = { 7, 3, 5, 1, 2, 6 };
		Integer[] nonExistingElements = { -1, 0, 8, 9, 10, 11, 12 };

		testSearchForElements(elementsToAdd, nonExistingElements);
	}

	@Test
	void searchUnbalancedRight() {
		Integer[] elementsToAdd = { 3, 7, 5, 1, 2, 6 };
		Integer[] nonExistingElements = { -1, 0, 8, 9, 10, 11, 12 };

		testSearchForElements(elementsToAdd, nonExistingElements);
	}

	@Test
	void searchCharacterTree() {
		Character[] elementsToAdd = { 'd', 'b', 'f', 'a', 'c', 'e', 'g' };
		Character[] nonExistingElements = { 'h', 'i', 'j', 'k', 'l', 'm', 'n' };

		testSearchForElements(elementsToAdd, nonExistingElements);
	}

	@Test
	void searchEmptyTree() {
		Tree<Integer> tree = new BSTree<Integer>();

		assertFalse(tree.search(5));
		assertFalse(tree.search(0));
		assertFalse(tree.search(-3));
	}

	@Test
	void searchNull() {
		Tree<Integer> tree = new BSTree<Integer>();

		assertThrows(NullPointerException.class, () -> tree.search(null));

		tree.add(5);
		assertThrows(NullPointerException.class, () -> tree.search(null));

		tree.add(3);
		assertThrows(NullPointerException.class, () -> tree.search(null));

		tree.add(7);
		assertThrows(NullPointerException.class, () -> tree.search(null));

	}

}
