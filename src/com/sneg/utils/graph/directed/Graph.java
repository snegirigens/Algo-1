package com.sneg.utils.graph.directed;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Author:	sneg
 * Date:	27.05.14
 * Time:	22:53
 */
public class Graph {
	public static final Comparator <Node> COMPARE_BY_ID = new Comparator <Node>() {
		@Override
		public int compare (Node node1, Node node2) {
			return node2.getId().compareTo (node1.getId ());
		}
	};

	public static final Comparator <Node> COMPARE_BY_LABEL = new Comparator <Node>() {
		@Override
		public int compare (Node node1, Node node2) {
			return node2.getLabel() - node1.getLabel();
		}
	};

	private Map <String, Node> _nodes = new HashMap<>();
//	private Map <String, Edge> _edges = new HashMap<>();

	public Set <Node> getNodes() {
		return new TreeSet<> (_nodes.values());
	}

	public TreeSet <Node> getNodes (Comparator <Node> comparator) {
		TreeSet <Node> set = new TreeSet<> (comparator);
		set.addAll (_nodes.values());
		return set;
	}

	public Node getNode (String id) {
		return _nodes.get (id);
	}

	public void addNode (Node node) {
		_nodes.put (node.getId(), node);
	}

	public boolean removeNode (String id) {
		return _nodes.remove (id) != null;
	}

	public Graph reverse() {
		for (Node node : _nodes.values()) {
			node.reverse();
		}

		return this;
	}

//	public Set <Edge> getEdges() {
//		return new TreeSet<> (_edges.values());
//	}
//
//	public Edge getEdge (String id) {
//		return _edges.get (id);
//	}
//
//	public void addEdge (Edge edge) {
//		_edges.put (edge.getId(), edge);
//	}
//
//	public boolean removeEdge (String id) {
//		return _edges.remove (id) != null;
//	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append ("Graph {\n");

		for (Node node : getNodes()) {
			sb.append ("\t").append (node).append (": (");

			for (Edge edge : node.getEdges()) {
				sb.append (edge).append (", ");
			}

			sb.append (")\n");
		}

		sb.append ("}");
		return sb.toString();
	}

	public static class Node implements Comparable <Node> {
		private final String _id;
		private int _label;
		private Graph.Node _leader;

		private Set <Edge> _edges   = new TreeSet<>();
		private Set <Edge> _inEdges = new TreeSet<>();

		public Node (String id) {
			_id = id;
		}

		public String getId() {
			return _id;
		}

		public Set <Edge> getEdges() {
			return _edges;
		}

		public void addEdge (Edge edge) {
			_edges.add (edge);
			edge.getHead().addInEdge (edge);
		}

		public int getLabel() {
			return _label;
		}

		public void setLabel (int label) {
			_label = label;
		}

		public Node getLeader() {
			return _leader;
		}

		public void setLeader (Node leader) {
			_leader = leader;
		}

		private void addInEdge (Edge edge) {
			_inEdges.add (edge);
		}

		private Set <Edge> getInEdges() {
			return _inEdges;
		}

		private void reverse() {
			for (Edge edge : _edges) {
				edge.reverse();
			}

			Set <Edge> temp = _edges;
			_edges = _inEdges;
			_inEdges = temp;
		}

		public int compareTo (Node node) {
			return node.getId().compareTo (this.getId());
		}

		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Node node = (Node) o;
			return _id.equals (node._id);
		}

		public int hashCode() {
			return _id.hashCode();
		}

		public String toString() {
			return _id + "(" + _label + "," + (_leader != null ? _leader.getId() : null) + ")";
		}
	}

	public static class Edge implements Comparable <Edge> {
		private final String _id;
		private Node _tail;
		private Node _head;

		public Edge (Node tail, Node head) {
			_tail = tail;
			_head = head;
			_id = tail.getId() + "->" + head.getId();
		}

		public String getId() {
			return _id;
		}

		public Node getTail() {
			return _tail;
		}

		public Node getHead() {
			return _head;
		}

		private void reverse() {
			Node temp = _head;
			_head = _tail;
			_tail = temp;
		}

		public int compareTo (Edge edge) {
			return this.getId().compareTo (edge.getId());
		}

		public boolean equals (Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Edge edge = (Edge) o;
			return _id.equals (edge._id);
		}

		public int hashCode() {
			return _id.hashCode();
		}

		public String toString() {
			return _tail.getId() + "->" + _head.getId();
		}
	}
}
