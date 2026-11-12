package tree;

public class AVLTree<T extends Comparable<T>> extends BSTree<T>{
	 /* ==============================
	  * 
	  *    SOBREESCRIBIR
	  *    
	    ==============================*/
	@Override
	BSTNode<T> add(BSTNode<T> current,T element){
		return updateBalanceFactor(super.add(current, element));
	}
	@Override
	BSTNode<T> remove (BSTNode<T> currentNode, T element){
		return updateBalanceFactor(super.remove(currentNode, element));
	}
	 /* ==============================
	  * 
	  *    ROTACIONES
	  *    
	    ==============================*/
	
	BSTNode<T> singleLeftRotation(BSTNode<T> currentNode){
		BSTNode<T> newParent = currentNode.getLeft();
		currentNode.setLeft(newParent.getRight());
		newParent.setRight(currentNode);
		
		currentNode.updateHeight();
		newParent.updateHeight();
		
		return newParent;
	}
	BSTNode<T> singleRightRotation(BSTNode<T> currentNode){
		BSTNode<T> newParent = currentNode.getRight();
		currentNode.setRight(newParent.getLeft());
		
		
		newParent.setLeft(currentNode);
		currentNode.updateHeight();
		newParent.updateHeight();
		
		
		
		return newParent;
	}
	BSTNode<T> doubleLeftRotation(BSTNode<T> currentNode){
		currentNode.setLeft(singleRightRotation(currentNode.getLeft())); 
		return singleLeftRotation(currentNode);
	}
	
	
	BSTNode<T> doubleRightRotation(BSTNode<T> currentNode){	
		currentNode.setRight(singleLeftRotation(currentNode.getRight())); 
		return singleRightRotation(currentNode);
	}
	
	
	 /* ==============================
	  * 
	  *    GESTION DE BALANCE
	  *    
	    ==============================*/
	
	BSTNode<T> updateBalanceFactor(BSTNode<T> currentNode){
		if (currentNode == null ) {
			return null;
		}
		int factorBalanceActual = currentNode.getBalanceFactor();

		if (factorBalanceActual >= 2) {
			int factorBalanceHijo = currentNode.getRight().getBalanceFactor();
			if (factorBalanceHijo >= 0) {
				return singleRightRotation(currentNode);
			} else {
				return doubleRightRotation(currentNode);
			}
		}else if (factorBalanceActual <= -2) {
			
			int factorBalanceHijo = currentNode.getLeft().getBalanceFactor();
			if (factorBalanceHijo > 0) {
				return doubleLeftRotation(currentNode);
			} else {
				return singleLeftRotation(currentNode);
			}
		}
		return currentNode;
	}
}