package gcore.struct.trees;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class BalancedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E> {

	private int size = 0;

	public BalancedBinaryTree() {}

	private BalancedBinaryTree(TreeNode newRoot) {
		super(newRoot);
	}

	@Override
	public boolean add(E e) {
		// check if e is already in the tree, if so return false
		if (contains(e))
			return false;

		@SuppressWarnings("unchecked")
		TreeNode root = (TreeNode) getRoot();

		if (root == null) {
			root = new TreeNode();
			root.setData(e);
			setRoot(root);
			size = 1;
			return true;
		}
		TreeNode greatGrandParent = null;
		TreeNode grandParent = null;
		TreeNode parent = null;
		TreeNode current = root;
		while (current != null) {
			int comparison = current.getData().compareTo(e);
			if (comparison == 0)
				return false;
			else if (comparison < 0) {
				greatGrandParent = grandParent;
				grandParent = parent;
				parent = current;
				current = current.getRight();
			} else {
				greatGrandParent = grandParent;
				grandParent = parent;
				parent = current;
				current = current.getLeft();
			}
		}

		TreeNode node = new TreeNode();
		node.setData(e);

		int comparison = parent.getData().compareTo(e);

		// if the grand parent is null or it has two children add like normal
		if (grandParent == null || (grandParent.getLeft() != null && grandParent.getRight() != null)) {
			if (comparison < 0) {
				parent.setRight(node);
			} else {
				parent.setLeft(node);
			}
			size++;
			return true;
		}

		// get the comparisons for the grand parent and the great grand parent
		int grandComparison = grandParent.getData().compareTo(e);
		int greatGrandComparison = greatGrandParent == null ? 0 : greatGrandParent.getData().compareTo(e);

		// use this function to set the new root for the sub tree when it is
		// created
		Consumer<TreeNode> rootSetter = null;

		if (greatGrandComparison == 0) {
			rootSetter = n -> setRoot(n);
		} else if (greatGrandComparison < 0) {
			TreeNode copy = greatGrandParent;
			rootSetter = n -> copy.setRight(n);
		} else {
			TreeNode copy = greatGrandParent;
			rootSetter = n -> copy.setLeft(n);
		}

		// compare the data elements to each other, the middle one goes in the
		// center and the other two go on the edges
		TreeNode left = null;
		TreeNode right = null;
		TreeNode center = null;

		if (grandComparison > 0) {
			right = grandParent;
			if (comparison > 0) {
				left = node;
				center = parent;
			} else {
				left = parent;
				center = node;
			}
		} else {
			left = grandParent;
			if (comparison < 0) {
				right = node;
				center = parent;
			} else {
				right = parent;
				center = node;
			}
		}

		// set the left and right of the center and clear the children for the
		// other two
		center.setLeft(left);
		center.setRight(right);

		left.setLeft(null);
		left.setRight(null);
		right.setLeft(null);
		right.setRight(null);

		// set the new root for the sub section
		rootSetter.accept(center);

		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		if (!contains(o))
			return false;

		@SuppressWarnings("unchecked")
		Comparable<E> c = (Comparable<E>) o;

		@SuppressWarnings("unchecked")
		TreeNode root = (TreeNode) getRoot();

		// find the node
		TreeNode parent = null;
		TreeNode current = root;
		while (true) {
			int comparison = c.compareTo(current.getData());
			if (comparison == 0)
				break;
			else if (comparison > 0) {
				parent = current;
				current = current.getRight();
			} else {
				parent = current;
				current = current.getLeft();
			}
		}

		int comparison = parent == null ? 0 : -c.compareTo(parent.getData());

		// use this function to set the new root for the sub tree when it is
		// created
		Consumer<TreeNode> rootSetter = null;

		if (comparison == 0) {
			rootSetter = n -> setRoot(n);
		} else if (comparison < 0) {
			TreeNode copy = parent;
			rootSetter = n -> copy.setRight(n);
		} else {
			TreeNode copy = parent;
			rootSetter = n -> copy.setLeft(n);
		}

		// check if the current node doesn't have one of the branches
		if (current.getLeft() == null) {
			rootSetter.accept(current.getRight());
			size--;
			return true;
		}

		if (current.getRight() == null) {
			rootSetter.accept(current.getLeft());
			size--;
			return true;
		}

		// choose randomly if the left or right child should be used as the
		// replacement
		Random r = new Random();

		// this is for the right child
		if (r.nextInt(2) == 0) {
			// get the replacement node
			TreeNode node = current.getRight().replaceLeftMost();
			// set the left branch to the left branch of the current
			if (node != current.getRight())
				node.setRight(current.getRight());
			node.setLeft(current.getLeft());

			// set it to the root
			rootSetter.accept(node);

			size--;
			return true;
		} else {
			// get the replacement node
			TreeNode node = current.getLeft().replaceRightMost();
			// set the right branch to the right branch of the current
			node.setRight(current.getRight());
			if (node != current.getLeft())
				node.setLeft(current.getLeft());

			// set it to the root
			rootSetter.accept(node);

			size--;
			return true;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public AbstractBinaryTree<E> getLeft() {
		return new BalancedBinaryTree<>((TreeNode)getRoot().getLeft());
	}

	@Override
	@SuppressWarnings("unchecked")
	public AbstractBinaryTree<E> getRight() {
		return new BalancedBinaryTree<>((TreeNode)getRoot().getRight());
	}

	@Override
	public void clear() {
		setRoot(null);
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	private class TreeNode extends AbstractTreeNode {

		private E data;

		private TreeNode left;
		private TreeNode right;

		public TreeNode() {
			data = null;
			left = null;
			right = null;
		}

		public E getData() {
			return data;
		}

		public void setData(E data) {
			this.data = data;
		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public TreeNode replaceLeftMost() {
			if (left == null)
				return this;

			TreeNode parent = null;
			TreeNode current = this;
			while (current.left != null) {
				parent = current;
				current = current.left;
			}

			// clear out the node from the parent
			parent.setLeft(current.getRight());

			// return the node
			return current;

		}

		public TreeNode replaceRightMost() {
			if (right == null)
				return this;

			TreeNode parent = null;
			TreeNode current = this;
			while (current.right != null) {
				parent = current;
				current = current.right;
			}

			// clear out the node from the parent
			parent.setRight(current.getLeft());

			// return the node
			return current;

		}

	}

}
