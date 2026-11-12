package tree;

public class TreeTestHelper {

	/**
	 * Helper method to convert an array representing preorder traversal.
	 *
	 * @param elements   Array of elements in preorder traversal. Null elements are
	 *                   represented as null.
	 * @param nodeFormat Format to use for node representation.
	 * @return String representation of the preorder traversal.
	 */
	public static <T extends Comparable<T>> String toStringPreorderArray(T[] elements, NodeFormat nodeFormat) {
		return toStringPreorderArray(elements, null, null, nodeFormat);
	}

	/**
	 * Helper method to convert arrays representing preorder traversal with heights.
	 *
	 * @param elements   Array of elements in preorder traversal. Null elements are
	 *                   represented as null.
	 * @param heights    Array of heights corresponding to each element. null for
	 *                   null nodes.
	 * @param nodeFormat Format to use for node representation.
	 * @return String representation of the preorder traversal.
	 */
	public static <T extends Comparable<T>> String toStringPreorderArray(T[] elements, Integer[] heights,
			NodeFormat nodeFormat) {
		return toStringPreorderArray(elements, heights, null, nodeFormat);
	}

	/**
	 * Helper method to convert arrays representing preorder traversal with heights
	 * and balance factors.
	 *
	 * @param elements       Array of elements in preorder traversal. Null elements
	 *                       are represented as null.
	 * @param heights        Array of heights corresponding to each element. null
	 *                       for null nodes.
	 * @param balanceFactors Array of balance factors corresponding to each element.
	 *                       null for null nodes.
	 * @param nodeFormat     Format to use for node representation.
	 * @return String representation of the preorder traversal.
	 */
	public static <T extends Comparable<T>> String toStringPreorderArray(T[] elements, Integer[] heights,
			Integer[] balanceFactors, NodeFormat nodeFormat) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elements.length; i++) {
			if (elements[i] == null) {
				sb.append(nodeFormat.formatNullNode());
			} else if (balanceFactors != null) {
				sb.append(nodeFormat.formatNodeFromData(elements[i], heights[i], balanceFactors[i]));
			} else if (heights != null) {
				sb.append(nodeFormat.formatNodeFromData(elements[i], heights[i]));
			} else {
				sb.append(nodeFormat.formatNodeFromData(elements[i]));
			}
		}
		return sb.toString();
	}

	/**
	 * Helper method to create a BSTree from an array of elements.
	 * 
	 * @param elements Array of elements to add to the BSTree.
	 * @return BSTree containing the added elements.
	 * @param <T> Type of elements in the tree.
	 * 
	 */
	public static <T extends Comparable<T>> BSTree<T> createBSTree(T[] elements) {
		BSTree<T> bst = new BSTree<T>();
		addElementsToTree(bst, elements);
		return bst;
	}

	/**
	 * Helper method to create an AVLTree from an array of elements.
	 * 
	 * @param elements Array of elements to add to the AVLTree.
	 * @return AVLTree containing the added elements.
	 * @param <T> Type of elements in the tree.
	 * 
	 */
	public static <T extends Comparable<T>> AVLTree<T> createAVLTree(T[] elements) {
		AVLTree<T> avl = new AVLTree<T>();
		addElementsToTree(avl, elements);
		return avl;
	}

	/**
	 * Helper method to add elements to a tree.
	 * 
	 * @param tree     The tree to add elements to.
	 * @param elements Array of elements to add.
	 * @param <T>      Type of elements in the tree.
	 */
	public static <T extends Comparable<T>> void addElementsToTree(Tree<T> tree, T[] elements) {
		for (T element : elements) {
			tree.add(element);
		}
	}

}
