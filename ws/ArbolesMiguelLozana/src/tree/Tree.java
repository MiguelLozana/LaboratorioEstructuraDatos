package tree;

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
	 * Returns the root node of the tree.
	 *
	 * @return the root node, or null if the tree is empty
	 */
	BSTNode<T> getRoot();

	/**
	 * Sets the root node of the tree.
	 *
	 * @param root the new root node to set
	 */
	void setRoot(BSTNode<T> root);

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
	 * Removes the specified element from the tree.
	 * 
	 *
	 * @param element the element to remove from the tree
	 * @throws NullPointerException     if element is null
	 * @throws IllegalArgumentException if the element is not found in the tree
	 */
	void remove(T element);

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

	/**
	 * Returns a string representation of the tree elements and their heights in
	 * preorder traversal.
	 * 
	 * Each node is displayed with its element value and height. The height
	 * represents the number of edges on the longest path from the node to a leaf.
	 * Null nodes are included to show the complete tree structure.
	 *
	 * @return preorder string representation including heights and null values
	 */
	String preorderTraversalHeight();

	/**
	 * Returns a string representation of the tree elements, their heights, and
	 * balance factors in preorder traversal.
	 * 
	 * Each node is displayed with its element value, height, and balance factor.
	 * The balance factor is calculated as right subtree height minus left subtree
	 * height. This method shows balance factors regardless of whether the tree is
	 * balanced or not. Null nodes are included to show the complete tree structure.
	 *
	 * @return preorder string representation including heights, balance factors,
	 *         and null values
	 */
	String preorderTraversalHeightAndBalanceFactor();

}
