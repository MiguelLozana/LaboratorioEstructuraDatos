package tree;

public class BSTree<T extends Comparable<T>> implements Tree<T>{
	private BSTNode<T> root;
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/
	public BSTNode<T> getRoot() {
		return root;
		
	}
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/
	@Override
	public void add(T element) {
		checkNull(element);
		
		root = add(root,element);
	}
	BSTNode<T> add(BSTNode<T> current,T element){

		//CASO BASE
		if (current == null) {
			current = new BSTNode<>(element);
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
		current.updateHeight();
		return current;
		
		
	}

	@Override
	public boolean search(T element) {
		checkNull(element);
		
		return search(root,element);
	}
	boolean search(BSTNode<T> current,T element) {
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
		return preorderTraversal(root, NodeFormat.BASIC);
	}
	private String preorderTraversal(BSTNode<T> curreNode, NodeFormat formato) {
		if (curreNode == null)
				return formato.formatNullNode();
		
		return formato.format(curreNode) +
				preorderTraversal(curreNode.left,formato) + 
				preorderTraversal(curreNode.right,formato);
	}
	
	
	public BSTNode<T> getMaxNode(BSTNode<T> current){
		checkNull((T) current);
		if (current.getRight() == null) {
			return current;
		}
		return getMaxNode(current.getRight());
	}
	
	
	public void remove(T element) {
		checkNull(element);
		
		root = remove(root,element);
	}
	BSTNode<T> remove (BSTNode<T> currentNode, T element){
		if (currentNode == null) {
			throw new IllegalArgumentException("No esta en el arbol");
		}
		
		int comparacion = element.compareTo(currentNode.getElement());
	
		if (comparacion < 0) {
			currentNode.left = remove(currentNode.left, element);
		}else if (comparacion > 0) {
			currentNode.right = remove(currentNode.right, element);
		}else if (comparacion == 0) {
			if (currentNode.getLeft() == null && currentNode.getRight() == null) {
				return null;
			}else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
				currentNode.updateHeight();
				return currentNode.left;
			}else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
				currentNode.updateHeight();
				return currentNode.right;
			}
			else if (currentNode.getLeft() != null && currentNode.getRight() != null){
				BSTNode<T> max = getMaxNode(currentNode.left);
				T elem = max.getElement();
				currentNode.element = elem;
				currentNode.left = remove(currentNode.left,elem);
			}
		}
		currentNode.updateHeight();
		return currentNode;
	}
	
	private void checkNull(T current) {
		if (current == null) {
			throw new NullPointerException();
		}
	}
	
	
	
	
	
	
	
	@Override
	public void setRoot(BSTNode<T> root) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String preorderTraversalHeight() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String preorderTraversalHeightAndBalanceFactor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * preoder trasverlas geight
	 */
	

}
