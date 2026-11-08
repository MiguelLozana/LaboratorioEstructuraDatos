package tree;

class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>>{
	T element;
	BSTNode<T> left;
	BSTNode<T> right;
	int height;
	
	/* ================================
	 * Constructor
	 * ------------------------------*/
	public BSTNode(T element) {
		this.element = element;
	}
	
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/

	public BSTNode<T> getLeft() {
		return left;}
	
	public void setLeft(BSTNode<T> left) {
		this.left = left;}
	
	public BSTNode<T> getRight() {
		return right;}
	
	public void setRight(BSTNode<T> right) {
		this.right = right;}
	public T getElement() {
		return this.element;
	}
	/* ================================
	 * Getters y Setters
	 * ------------------------------*/

	@Override
	public int compareTo(BSTNode<T> o) {
		return element.compareTo(o.element);
	}

	public int getHeight() {
		return this.height;
	}

	
	public void updateHeight() {
		if (this.left == null && this.right== null ) {
			this.height = 0;
		}else if (this.left != null && this.right== null ) {
			this.height = this.left.getHeight()+1;
		}else if (this.left == null && this.right!= null ) {
			this.height = this.right.getHeight()+1;
		}else if (this.left != null && this.right != null ) {
			this.height = max(this.left.getHeight(),this.right.getHeight()) + 1;
		}
		
	}
	public int getBalanceFactor(){
		if (this.left == null && this.right== null ) {
			return 0;
			
		}else if (this.left != null && this.right== null ) {
			return -1 - this.left.getHeight();
			
		}else if (this.left == null && this.right!= null ) {
			return this.right.getHeight() + 1;
		}
		return this.right.getHeight() -  this.left.getHeight();
	}
	private int max(int height1, int height2) {
		if (height1 > height2) {
			return height1;
		}
		return height2;
	}
	
	
	
	//prueba
}
