package com.sneg.week6;

/**
 * Author:	sneg
 * Date:	14.06.14
 * Time:	11:16
 */
public class BinaryTree <T extends Comparable <T>> {
	private Node <T> _root = null;

	public void add (T element) {
		if (_root == null) {
			_root = new Node<> (element, null);
		}
		else {
			add (element, _root);
		}
	}

	public T prev (T element) {
		return _root != null ? prev (element, _root) : null;
	}

	public T next (T element) {
		return _root != null ? next (element, _root) : null;
	}

	public T min() {
		return _root != null ? min (_root).value() : null;
	}

	public T max() {
		return _root != null ? max (_root).value() : null;
	}

	@Override
	public String toString() {
		StringBuilder sb = append (_root, new StringBuilder());
		return sb.toString();
	}

	private T prev (T element, Node <T> node) {
		if (element.compareTo (node.value()) < 0) {
			return node.left() != null ? prev (element, node.left()) : null;
		}
		else if (element.compareTo (node.value()) > 0) {
			if (node.right() == null || element.compareTo (min (node.right()).value()) <= 0) {
				return node.value();
			}
			else {
				return prev (element, node.right());
			}
		}
		else {
			return node.left() != null ? max (node.left()).value() : null;
		}
	}

	private T next (T element, Node <T> node) {
		if (element.compareTo (node.value()) > 0) {
			return node.right() != null ? next (element, node.right()) : null;
		}
		else if (element.compareTo (node.value()) < 0) {
			if (node.left() == null || element.compareTo (max (node.left()).value()) >= 0) {
				return node.value();
			}
			else {
				return next (element, node.left());
			}
		}
		else {
			return node.right() != null ? min (node.right()).value() : null;
		}
	}

	private Node <T> min (Node <T> node) {
		return node.left() != null ? min (node.left()) : node;
	}

	private Node <T> max (Node <T> node) {
		return node.right() != null ? max (node.right()) : node;
	}

	private Node <T> find (T element, Node <T> parent) {
		if (parent == null) return null;

		if (element.compareTo (parent.value()) < 0) {
			return find (element, parent.left());
		}
		else if (element.compareTo (parent.value()) > 0) {
			return find (element, parent.right());
		}
		else {
			return parent;
		}
	}

	private void add (T element, Node <T> parent) {
		if (element.compareTo (parent.value()) < 0) {
			if (parent.left() == null) {
				parent.left (new Node<> (element, parent));
			}
			else {
				add (element, parent.left());
			}
		}
		else if (element.compareTo (parent.value()) > 0) {
			if (parent.right() == null) {
				parent.right(new Node<> (element, parent));
			}
			else {
				add (element, parent.right());
			}
		}
	}

	private StringBuilder append (Node <T> node, StringBuilder sb) {
		if (node == null) return sb;

		if (node.left() != null) {
			append (node.left(), sb).append (", ");
		}

		sb.append (node);

		if (node.right() != null) {
			sb.append (", ");
			append (node.right(), sb);
		}

		return sb;
	}

	public static class Node <T> {
		private final T _value;
		private Node <T> _parent;
		private Node <T> _left;
		private Node <T> _right;

		private Node (T value, Node <T> parent) {
			this (value, parent, null, null);
		}

		private Node (T value, Node <T> parent, Node <T> left, Node <T> right) {
			_value	= value;
			_parent	= parent;
			_left	= left;
			_right	= right;
		}

		public T value() {
			return _value;
		}

		public void left (Node <T> left) {
			_left = left;
		}

		public Node <T> left() {
			return _left;
		}

		public void right (Node <T> right) {
			_right = right;
		}

		public Node <T> right() {
			return _right;
		}

		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Node <T> node = (Node <T>) o;
			return _value != null ? _value.equals (node._value) : node._value == null;
		}

		public int hashCode() {
			return _value != null ? _value.hashCode() : 0;
		}

		public String toString() {
			return _value.toString();
		}
	}
}
