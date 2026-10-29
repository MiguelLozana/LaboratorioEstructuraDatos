package arboles;

/**
 * Defines the contract for a generic binary search tree data structure.
 *
 * This interface provides operations for managing tree elements including
 * insertion, deletion, and search. It also supports various traversal and
 * visualization methods to display tree structure and node properties.
 *
 * All elements must be comparable and null values are not permitted in the
 * tree.
 *
 * @param <T> the type of elements stored in the tree, must implement Comparable
 */
public interface Tree<T extends Comparable<T>> {

	/**
	 * Adds an element to the tree in the appropriate position according to BST
	 * ordering.
	 *
	 * The element is inserted such that all elements in the left subtree are less
	 * than the element, and all elements in the right subtree are greater than the
	 * element.
	 *
	 * @param element the element to add to the tree
	 * @throws NullPointerException     if element is null
	 * @throws IllegalArgumentException if the element already exists in the tree
	 */
	void add(T element);

	/**
	 * Searches for an element in the tree.
	 *
	 * Performs a binary search starting from the root, comparing the target element
	 * with node values to determine the search direction.
	 *
	 * @param element the element to search for
	 * @return true if the element exists in the tree, false otherwise
	 * @throws NullPointerException if element is null
	 */
	boolean search(T element);

	/**
	 * Returns a string representation of the tree elements in preorder traversal.
	 *
	 * Preorder traversal visits nodes in the order: root, left subtree, right
	 * subtree. Null nodes are included in the representation to show the complete
	 * tree structure.
	 *
	 * @return preorder string representation including null values
	 */
	String preorderTraversal();

}
