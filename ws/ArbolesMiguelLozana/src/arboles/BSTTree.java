package arboles;

public class BSTTree<T extends Comparable<T>> implements Tree<T>{
	private TreeNode<T> root;
	NodeFormat formato;
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/
	TreeNode<T> getRoot() {
		return root;
		
	}
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/
	@Override
	public void add(T element) {
		checkNull(element);
		if (root.equals(null)) {
			root = new TreeNode<>(element);
		}
		add(root,element);
	}
	TreeNode<T> add(TreeNode<T> current,T element){

		//CASO BASE
		if (current == null) {
			current = new TreeNode<>(element);
			return current;
		}
		int comparation = element.compareTo(current.element);
		// RECURSIVO
		if (comparation == 0)
			throw new IllegalArgumentException("No elementos duplciados");
		else if (comparation < 0) 
			current.left = add(current.left,element);
		else 
			current.right =  add(current.right,element);
		
		//devolver el node ara no hacer cambios 
		return current;
		
		
	}

	@Override
	public boolean search(T element) {
		checkNull(element);
		
		return search(root,element);
	}
	boolean search(TreeNode<T> current,T element) {
		// Caso base
		if (current == null) {
			return false;
		}
		// RECURSIVO
		int comparation = element.compareTo(current.element);
		if (comparation == 0)
			return true;
		else if (comparation < 0) 
			return search(current.left,element);
		else 
			return search(current.right,element);
	
	}

	@Override
	public String preorderTraversal() {
		// TODO Auto-generated method stub
		return preorderTraversal(root, formato);
	}
	private String preorderTraversal(TreeNode<T> curreNode, NodeFormat formato) {
		if (curreNode == null)
				return formato.formatNullNode();
		
		return formato.format(curreNode) +
				preorderTraversal(curreNode.left,formato) + 
				preorderTraversal(curreNode.right,formato);
	}
	
	private void checkNull(T elem) {
		if (elem == null) {
			throw new NullPointerException();
		}
	}

}
