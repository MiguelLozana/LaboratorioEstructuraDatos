package arboles;

class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>>{
	T element;
	TreeNode<T> left;
	TreeNode<T> right;
	
	/* ================================
	 * Constructor
	 * ------------------------------*/
	public TreeNode(T element) {
		this.element = element;
	}
	
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/

	public TreeNode<T> getLeft() {
		return left;}
	
	public void setLeft(TreeNode<T> left) {
		this.left = left;}
	
	public TreeNode<T> getRight() {
		return right;}
	
	public void setRight(TreeNode<T> right) {
		this.right = right;}
	public T getElement() {
		return this.element;
	}
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/

	@Override
	public int compareTo(TreeNode<T> o) {
		return element.compareTo(o.element);
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBalanceFactor() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//prueba
}
