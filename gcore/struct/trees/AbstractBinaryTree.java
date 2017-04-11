package gcore.struct.trees;

import java.util.AbstractSet;

public abstract class AbstractBinaryTree<E extends Comparable<E>> extends AbstractSet<E> {

	private AbstractTreeNode root = null;

	protected AbstractBinaryTree() {}

	protected AbstractBinaryTree(AbstractTreeNode root) {
		this.root = root;
	}

	protected final AbstractTreeNode getRoot() {
		return root;
	}

	protected final void setRoot(AbstractTreeNode root) {
		this.root = root;
	}

	@Override
	public boolean contains(Object o) {
		if (isEmpty())
			return false;

		// if o isn't comparable return false
		if (!(o instanceof Comparable))
			return false;

		// cast o
		@SuppressWarnings("unchecked")
		Comparable<E> c = (Comparable<E>) o;

		// if o doesn't compare to the root element return false
		try {
			c.compareTo(root.getData());
		} catch (ClassCastException e) {
			return false;
		}

		// loop through until the object is found
		AbstractTreeNode current = root;
		while (current != null) {
			int compare = c.compareTo(current.getData());
			if (compare == 0) {
				return true;
			} else if (compare < 0) {
				current = current.getLeft();
			} else if (compare > 0) {
				current = current.getRight();
			}
		}

		// if not found yet return false
		return false;
	}

	public String toString() {
		if (root == null)
			return "<>";
		return root.toString();
	}

	public int depth(){
		return getRoot().depth();
	}

	public abstract AbstractBinaryTree<E> getLeft();

	public abstract AbstractBinaryTree<E> getRight();

	protected abstract class AbstractTreeNode {

		public abstract E getData();

		public abstract AbstractTreeNode getLeft();

		public abstract AbstractTreeNode getRight();
		
		public int depth(){
			return Math.max(getRight() == null ? 0 : (getRight().depth() + 1), getLeft() == null ? 0 : (getLeft().depth() + 1));
		}

		public String toString() {
			String results = "<" + getData().toString() + ">";
			String leftString = getLeft() == null ? "" : getLeft().toString();
			String rightString = getRight() == null ? "" : getRight().toString();

			// if the left/ right string aren't empty add a new line and tab
			// them in
			if (!leftString.equals("")) {
				results += "\n" + leftString.replaceAll("(?m)^", " ");
			}
			if (!rightString.equals("")) {
				results += "\n" + rightString.replaceAll("(?m)^", " ");
			}

			// return results
			return results;
		}
	}

}
