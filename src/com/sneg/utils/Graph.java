package com.sneg.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:	sneg
 * Date:	27.05.14
 * Time:	22:53
 */
public class Graph {
	private Set <Node> _nodes = new HashSet<>();
	private Set <Edge> _edges = new HashSet<>();

	public Set <Node> getNodes() {
		return _nodes;
	}

	public void setNodes (Set <Node> nodes) {
		_nodes = nodes;
	}

	public void addNode (Node node) {
		_nodes.add (node);
	}

	public Set <Edge> getEdges() {
		return _edges;
	}

	public void setEdges (Set <Edge> edges) {
		this._edges = edges;
	}

	public void addEdge (Edge edge) {
		_edges.add (edge);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder().append ("Graph {\n");

		for (Node node : _nodes) {
			sb.append ("\t").append (node.getId()).append (": ");

			for (Edge edge : node.getEdges()) {
				sb.append (edge).append (", ");
			}

			sb.append ("\n");
		}

		sb.append ("}");
		return sb.toString();
	}

	public static class Node {
		private final String _id;
		private Set <Edge> _edges = new HashSet<>();

		public Node (String id) {
			_id = id;
		}

		public String getId() {
			return _id;
		}

		public Set <Edge> getEdges() {
			return _edges;
		}

		public void setEdges (Set <Edge> edges) {
			this._edges = edges;
		}

		public void addEdge (Edge edge) {
			_edges.add (edge);
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
			return _id;
		}
	}

	public static class Edge {
		private final String _id;
		private Node _node1;
		private Node _node2;

		public Edge (String id, Node node1, Node node2) {
			_id = id;
			_node1 = node1;
			_node2 = node2;
		}

		public String getId() {
			return _id;
		}

		public Node[] getNodes() {
			return new Node[] {_node1, _node2};
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
			return _id + "(" +_node1.toString() + "-" + _node2.toString() + ")";
		}
	}
}
